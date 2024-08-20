package mattmck.mywebservice;

import javax.sql.DataSource;

import org.postgresql.xa.PGXADataSource;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.atomikos.jdbc.AtomikosDataSourceBean;

@Configuration
@EnableJpaRepositories(basePackages = {"mattmck.mywebservice.persistence.db1"},
	//transactionManagerRef = "db1TransactionManager",	
	entityManagerFactoryRef = "db1EntityManagerFactory")
@EnableTransactionManagement
public class Db1RepositoryConfig {

	
	// examples
	// https://github.com/jejo/atomikos-spring-boot/blob/master/src/main/java/com/test/atomikos/config/Db1DataSourceConfig.java
	// https://github.com/kloia/atomikos-spring/blob/main/src/main/java/com/kloia/atomikos/configuration/CustomerDataSourceConfiguration.java
	
	//Caused by: org.postgresql.util.PSQLException: ERROR: prepared transactions are disabled
	//Hint: Set max_prepared_transactions to a nonzero value.
	//Run following Query e.g. ALTER SYSTEM SET max_prepared_transactions = 100;
	//Restart Service
	//Validate by running following Query :- SHOW max_prepared_transactions;
	
	
	
	@Bean
    @ConfigurationProperties("spring.datasource.db1")
    public DataSourceProperties db1DataSourceProperties() {
        return new DataSourceProperties();
    }

    @Bean
    @Primary
    public DataSource db1DataSource() {

    	PGXADataSource ds = new PGXADataSource(); 
    	ds.setURL(db1DataSourceProperties().getUrl());
    	ds.setUser(db1DataSourceProperties().getUsername());
    	ds.setPassword(db1DataSourceProperties().getPassword());
    	
    	AtomikosDataSourceBean xaDS = new AtomikosDataSourceBean();
        xaDS.setXaDataSourceClassName("org.postgresql.Driver");
        xaDS.setXaDataSource(ds);       
        xaDS.setUniqueResourceName("xa_ds1");
        //xaDS.setMaxPoolSize(3);
        return xaDS;

//		DriverManagerDataSource driver = new DriverManagerDataSource();
//		driver.setDriverClassName("org.postgresql.Driver");
//		driver.setUrl("jdbc:postgresql://localhost:5432/db1");
//		driver.setUsername("postgres");
//		driver.setPassword("password");
//		return driver;
        
//        return db1DataSourceProperties()
//          .initializeDataSourceBuilder()
//          .build();
    }

    @Bean
    @Primary
    public LocalContainerEntityManagerFactoryBean db1EntityManagerFactory(
      @Qualifier("db1DataSource") DataSource dataSource,
      EntityManagerFactoryBuilder builder) {
    	
        return builder
          .dataSource(dataSource)
          .packages("mattmck.mywebservice.persistence.db1")
          .persistenceUnit("db1PersistenceUnit")
          .jta(true)
          .build();
    }
    
//    @Bean
//    public PlatformTransactionManager db1TransactionManager(
//      @Qualifier("db1EntityManagerFactory") LocalContainerEntityManagerFactoryBean db1EntityManagerFactory) {
//        return new JpaTransactionManager(Objects.requireNonNull(db1EntityManagerFactory.getObject()));
//    }
    
    
//	@Bean
//	public EntityManagerFactory db1EntityManagerFactory(@Qualifier("db1") DataSource dataSource) {
//
//		HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
//		vendorAdapter.setGenerateDdl(true);
//
//		LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
//		factory.setJpaVendorAdapter(vendorAdapter);
//		factory.setPackagesToScan("mattmck.mywebservice.persistence.db1");
//		factory.setDataSource(dataSource);
//		factory.setPersistenceUnitName("db1PersistenceUnit");;
//
//		Properties props = new Properties();
//		props.put("hibernate.dialect", "org.hibernate.dialect.PostgreSQLDialect");
//		//props.put("hibernate.hbm2ddl.auto", "create");
//		factory.setJpaProperties(props);
//		
//		factory.afterPropertiesSet();
//
//		return factory.getObject();
//	}

//	@Bean
//	public PlatformTransactionManager db1TransactionManager() {
//
//		JpaTransactionManager txManager = new JpaTransactionManager();
//		txManager.setEntityManagerFactory(db1EntityManagerFactory());
//		return txManager;
//	}
}
