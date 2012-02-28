import ch.ralscha.embeddedtc.EmbeddedTomcat;

public class StartTomcat {
	public static void main(String[] args) throws Exception {
		System.setProperty("spring.profiles.active", "development");
		
		EmbeddedTomcat et = new EmbeddedTomcat();
		et.startAndWait();
	}

}