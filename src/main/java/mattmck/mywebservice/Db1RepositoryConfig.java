package mattmck.mywebservice;

import java.util.Properties;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import jakarta.persistence.EntityManagerFactory;

@Configuration
@EnableJpaRepositories(basePackages = {"mattmck.mywebservice.persistence.db1"}, entityManagerFactoryRef = "db1EntityManagerFactory", transactionManagerRef = "db1TransactionManager")
@EnableTransactionManagement
public class Db1RepositoryConfig {

	@Bean
	public DataSource db1DataSource() {

		DriverManagerDataSource driver = new DriverManagerDataSource();
		driver.setDriverClassName("org.postgresql.Driver");
		driver.setUrl("jdbc:postgresql://localhost:5432/db1");
		driver.setUsername("db1user");
		driver.setPassword("password");
		return driver;

//	    EmbeddedDatabaseBuilder builder = new EmbeddedDatabaseBuilder();
//	    return builder.setType(EmbeddedDatabaseType.HSQL).build();
	}

	@Bean
	public EntityManagerFactory db1EntityManagerFactory() {

		HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
		vendorAdapter.setGenerateDdl(true);

		LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
		factory.setJpaVendorAdapter(vendorAdapter);
		factory.setPackagesToScan("mattmck.mywebservice.persistence.db1");
		factory.setDataSource(db1DataSource());

		Properties props = new Properties();
		props.put("hibernate.dialect", "org.hibernate.dialect.PostgreSQLDialect");
		//props.put("hibernate.hbm2ddl.auto", "create");
		factory.setJpaProperties(props);
		
		factory.afterPropertiesSet();

		return factory.getObject();
	}

	@Bean
	public PlatformTransactionManager db1TransactionManager() {

		JpaTransactionManager txManager = new JpaTransactionManager();
		txManager.setEntityManagerFactory(db1EntityManagerFactory());
		return txManager;
	}
	
}
