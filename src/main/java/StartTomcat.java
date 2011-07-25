import java.io.File;

import javax.servlet.ServletException;

import org.apache.catalina.Context;
import org.apache.catalina.LifecycleException;
import org.apache.catalina.startup.Tomcat;
import org.apache.commons.lang.SystemUtils;

public class StartTomcat {

	public static void main(String[] args) throws ServletException, LifecycleException {

		System.setProperty("spring.profiles.active", "development");

		Tomcat tomcat = new Tomcat();
		//tomcat.setSilent(true);
		tomcat.setPort(8080);
		tomcat.setBaseDir(SystemUtils.getJavaIoTmpDir().getPath());
		tomcat.getConnector().setURIEncoding("UTF-8");

		File currentDir = new File(".");
		@SuppressWarnings("unused")
		Context ctx = tomcat.addWebapp("/", currentDir.getAbsolutePath() + "/src/main/webapp");

		/* Overwrites default.properties
		tomcat.enableNaming();
		System.setProperty("jndiPropertySourceEnabled", "true");
		
		ContextEnvironment environment = new ContextEnvironment();
		environment.setType("java.lang.String");
		environment.setName("database.url");
		environment.setValue("jdbc:h2:~/e4ds");
		ctx.getNamingResources().addEnvironment(environment);
		
		environment = new ContextEnvironment();
		environment.setType("java.lang.String");
		environment.setName("database.driver");
		environment.setValue("org.h2.Driver");
		ctx.getNamingResources().addEnvironment(environment);
		
		environment = new ContextEnvironment();
		environment.setType("java.lang.String");
		environment.setName("database.username");
		environment.setValue("sa");
		ctx.getNamingResources().addEnvironment(environment);
		
		environment = new ContextEnvironment();
		environment.setType("java.lang.String");
		environment.setName("database.password");
		environment.setValue("");
		ctx.getNamingResources().addEnvironment(environment);
		*/

		tomcat.start();
		tomcat.getServer().await();
	}
}
