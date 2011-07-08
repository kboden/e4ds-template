package ch.ralscha.e4ds.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
public class DataConfig {

	
	
	
//	@Inject
//	private DataSource dataSource;
//	
//	/**
//	 * Allows repositories to access RDBMS data using the JDBC API.
//	 */
//	@Bean
//	public JdbcTemplate jdbcTemplate() {
//		return new JdbcTemplate(dataSource);
//	}
//	
//	/**
//	 * Allows transactions to be managed against the RDBMS using the JDBC API.
//	 */
//	@Bean
//	public PlatformTransactionManager transactionManager() {
//		return new DataSourceTransactionManager(dataSource);
//	}
//
//	/**
//	 * Embedded Data configuration.
//	 * @author Keith Donald
//	 */
//	@Configuration
//	@Profile("embedded")
//	static class Embedded {
//
//		@Inject
//		private Environment environment;
//
//		@Inject
//		private TextEncryptor textEncryptor;
//
//		@Bean(destroyMethod="shutdown")
//		public DataSource dataSource() {
//			EmbeddedDatabaseFactory factory = new EmbeddedDatabaseFactory();
//			factory.setDatabaseName("greenhouse");
//			factory.setDatabaseType(EmbeddedDatabaseType.H2);
//			return populateDatabase(factory.getDatabase());		
//		}
//
//		// internal helpers
//		
//		private EmbeddedDatabase populateDatabase(EmbeddedDatabase database) {
//			new DatabaseUpgrader(database, environment, textEncryptor) {
//				protected void addInstallChanges(DatabaseChangeSet changeSet) {
//					changeSet.add(SqlDatabaseChange.inResource(new ClassPathResource("test-data.sql", getClass())));
//				}
//			}.run();
//			return database;
//		}
//
//	}
//	
//	/**
//	 * Standard Data configuration.
//	 * @author Keith Donald
//	 */
//	@Configuration
//	@Profile("standard")
//	static class Standard {
//
//		@Inject
//		private Environment environment;
//
//		@Inject
//		private TextEncryptor textEncryptor;
//
//		@Bean(destroyMethod="dispose")
//		public DataSource dataSource() {
//			JdbcConnectionPool dataSource = JdbcConnectionPool.create(environment.getProperty("database.url"),
//					environment.getProperty("database.username"), environment.getProperty("database.password"));
//			new DatabaseUpgrader(dataSource, environment, textEncryptor).run();
//			return dataSource;
//		}
//		
//	}
}
