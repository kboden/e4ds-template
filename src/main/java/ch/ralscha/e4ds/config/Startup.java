package ch.ralscha.e4ds.config;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.classic.PatternLayout;
import ch.qos.logback.classic.db.DBAppender;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.ConsoleAppender;
import ch.qos.logback.core.db.DataSourceConnectionSource;
import ch.qos.logback.core.util.StatusPrinter;

@Component
public class Startup {

	@Autowired
	private Environment environment;
	
	@Autowired
	private DataSource dataSource;
	
	@PostConstruct
	public void setupLog() {

		System.out.println("ASIMPLETEST: " + environment.getProperty("aSimpleTest"));
		System.out.println("USER       : " + environment.getProperty("user"));
		
		LoggerContext lc = (LoggerContext) LoggerFactory.getILoggerFactory();
		lc.reset();

		PatternLayout patternLayout = new PatternLayout();
		patternLayout.setContext(lc);
		patternLayout.setPattern("%d{HH:mm:ss.SSS} [%thread] %-5level %logger{36} - %msg%n");
		patternLayout.start();

		ConsoleAppender<ILoggingEvent> appender = new ConsoleAppender<ILoggingEvent>();
		appender.setContext(lc);
		appender.setLayout(patternLayout);		
		appender.start();
		
		DataSourceConnectionSource source = new DataSourceConnectionSource();
		source.setContext(lc);
		source.setDataSource(dataSource);
		source.start();
		
		DBAppender dbAppender = new DBAppender();
		dbAppender.setContext(lc);
		dbAppender.setConnectionSource(source);
		dbAppender.start();
		
		Logger rootLogger = lc.getLogger("root");
		rootLogger.setLevel(Level.INFO);
		rootLogger.addAppender(appender);
		rootLogger.addAppender(dbAppender);
		lc.start();

		StatusPrinter.printInCaseOfErrorsOrWarnings(lc);

	}

}
