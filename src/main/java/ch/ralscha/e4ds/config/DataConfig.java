package ch.ralscha.e4ds.config;

import javax.sql.DataSource;

import liquibase.integration.spring.SpringLiquibase;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.support.TransactionTemplate;

import com.jolbox.bonecp.BoneCPDataSource;

@Configuration
@EnableTransactionManagement
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

		return ds;
	}

	@Bean
	public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
		LocalContainerEntityManagerFactoryBean emf = new LocalContainerEntityManagerFactoryBean();
		emf.setDataSource(dataSource());
		emf.setPersistenceProvider(new org.hibernate.ejb.HibernatePersistence());
		emf.setPackagesToScan("ch.ralscha.e4ds.entity");
		return emf;
	}
	
	@Bean
	public PlatformTransactionManager transactionManager() {		
		return new JpaTransactionManager(entityManagerFactory().getObject());
	}
	
	@Bean
	public TransactionTemplate readWriteTransactionTemplate() {
		return new TransactionTemplate(transactionManager());
	}
	
	@Bean
	public SpringLiquibase liquibaseBean() {
		SpringLiquibase bean = new SpringLiquibase();
		bean.setDataSource(dataSource());
		bean.setChangeLog("classpath:db/changelog.xml");
		return bean;
	}

}
