package ch.ralscha.e4ds.config;

import javax.sql.DataSource;

import liquibase.integration.spring.SpringLiquibase;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.core.env.Environment;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.classic.PatternLayout;
import ch.qos.logback.classic.db.DBAppender;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.ConsoleAppender;
import ch.qos.logback.core.db.DataSourceConnectionSource;
import ch.qos.logback.core.util.StatusPrinter;

import com.jolbox.bonecp.BoneCPDataSource;

@Configuration
@EnableTransactionManagement
@ImportResource("classpath:spring/data.xml")
public class DataConfig {

	@Autowired
	private Environment environment;

	@Bean(destroyMethod = "close")
	public DataSource dataSource() {

		BoneCPDataSource ds = new BoneCPDataSource();
		ds.setDriverClass(environment.getProperty("database.driver"));
		ds.setJdbcUrl(environment.getProperty("database.url"));
		ds.setUsername(environment.getProperty("database.username"));
		ds.setPassword(environment.getProperty("database.password"));

		ds.setIdleConnectionTestPeriodInMinutes(240);
		ds.setIdleMaxAgeInMinutes(60);
		ds.setMaxConnectionsPerPartition(5);
		ds.setMinConnectionsPerPartition(1);
		ds.setPartitionCount(3);
		ds.setAcquireIncrement(5);
		ds.setStatementsCacheSize(200);
		ds.setReleaseHelperThreads(1);
		
		//Debugging
		//ds.setCloseConnectionWatch(true);

		setupLog(ds);
		return ds;
	}

	private void setupLog(DataSource dataSource) {

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
		rootLogger.setLevel(Level.WARN);
		rootLogger.addAppender(appender);
		rootLogger.addAppender(dbAppender);
		lc.start();

		StatusPrinter.printInCaseOfErrorsOrWarnings(lc);

	}

	@Bean
	public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
		LocalContainerEntityManagerFactoryBean emf = new LocalContainerEntityManagerFactoryBean();
		emf.setDataSource(dataSource());
		emf.setPersistenceProvider(new org.hibernate.ejb.HibernatePersistence());
		emf.setPackagesToScan("ch.ralscha.e4ds.entity");

		//Map<String,String> properties = Maps.newHashMap();
		//properties.put("hibernate.show_sql", "true");
		//emf.setJpaPropertyMap(properties);

		return emf;
	}

	@Bean
	public PlatformTransactionManager transactionManager() {
		return new JpaTransactionManager(entityManagerFactory().getObject());
	}

	@Bean
	public SpringLiquibase liquibaseBean() {
		SpringLiquibase bean = new SpringLiquibase();
		bean.setDataSource(dataSource());
		bean.setChangeLog("classpath:db/changelog.xml");
		return bean;
	}

}
