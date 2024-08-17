package mattmck.mywebservice;

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
@EnableJpaRepositories
@EnableTransactionManagement
public class ApplicationConfig {

	
//	spring.datasource.url=jdbc:postgresql://localhost:5432/postgres
//		spring.datasource.username=postgres
//		spring.datasource.password=password
//		spring.jpa.hibernate.ddl-auto=<create | create-drop | update | validate | none>
//		spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.PostgreSQLDialect

	
	@Bean
	  public DataSource dataSource() {

		DriverManagerDataSource driver = new DriverManagerDataSource();
	    driver.setDriverClassName("org.postgresql.Driver");
	    driver.setUrl("jdbc:postgresql://localhost:5432/postgres");
	    driver.setUsername("postgres");
	    driver.setPassword("password");
	    return driver;
		
//	    EmbeddedDatabaseBuilder builder = new EmbeddedDatabaseBuilder();
//	    return builder.setType(EmbeddedDatabaseType.HSQL).build();
	  }
	
	
	
	@Bean
	public EntityManagerFactory entityManagerFactory() {

		HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
		vendorAdapter.setGenerateDdl(true);

		LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
		factory.setJpaVendorAdapter(vendorAdapter);
		factory.setPackagesToScan("mattmck.mywebservice.persistence");
		factory.setDataSource(dataSource());
//		factory.setJpaDialect(
		factory.afterPropertiesSet();

		return factory.getObject();
	}

	@Bean
	public PlatformTransactionManager transactionManager() {

		JpaTransactionManager txManager = new JpaTransactionManager();
		txManager.setEntityManagerFactory(entityManagerFactory());
		return txManager;
	}

}
