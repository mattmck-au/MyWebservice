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
@EnableJpaRepositories(basePackages = {"mattmck.mywebservice.persistence.db2"}, entityManagerFactoryRef = "db2EntityManagerFactory", transactionManagerRef = "db2TransactionManager")
@EnableTransactionManagement
public class Db2RepositoryConfig {

	@Bean
	public DataSource db2DataSource() {

		DriverManagerDataSource driver = new DriverManagerDataSource();
		driver.setDriverClassName("org.postgresql.Driver");
		driver.setUrl("jdbc:postgresql://localhost:5432/db2");
		driver.setUsername("db2user");
		driver.setPassword("password");
		return driver;

//	    EmbeddedDatabaseBuilder builder = new EmbeddedDatabaseBuilder();
//	    return builder.setType(EmbeddedDatabaseType.HSQL).build();
	}

	@Bean
	public EntityManagerFactory db2EntityManagerFactory() {

		HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
		vendorAdapter.setGenerateDdl(true);

		LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
		factory.setJpaVendorAdapter(vendorAdapter);
		factory.setPackagesToScan("mattmck.mywebservice.persistence.db2");
		factory.setDataSource(db2DataSource());
		
		Properties props = new Properties();
		props.put("hibernate.dialect", "org.hibernate.dialect.PostgreSQLDialect");
		//props.put("hibernate.hbm2ddl.auto", "create");
		factory.setJpaProperties(props);
		
		factory.afterPropertiesSet();
		
		return factory.getObject();
	}

	@Bean
	public PlatformTransactionManager db2TransactionManager() {

		JpaTransactionManager txManager = new JpaTransactionManager();
		txManager.setEntityManagerFactory(db2EntityManagerFactory());
		return txManager;
	}
}
