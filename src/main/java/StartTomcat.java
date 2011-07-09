import java.io.File;

import javax.servlet.ServletException;

import org.apache.catalina.LifecycleException;
import org.apache.catalina.startup.Tomcat;
import org.apache.commons.lang.SystemUtils;

public class StartTomcat {

	public static void main(String[] args) throws ServletException, LifecycleException {
		
		Tomcat tomcat = new Tomcat();
		//tomcat.setSilent(true);
		tomcat.setPort(8080);
		tomcat.setBaseDir(SystemUtils.getJavaIoTmpDir().getPath());
		tomcat.getConnector().setURIEncoding("UTF-8");

		File currentDir = new File(".");
		tomcat.addWebapp("/", currentDir.getAbsolutePath() + "/src/main/webapp");		
		
		
		tomcat.start();
		tomcat.getServer().await();
	}
}
