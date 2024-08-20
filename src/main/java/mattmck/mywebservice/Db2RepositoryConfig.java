package mattmck.mywebservice;

import javax.sql.DataSource;

import org.postgresql.xa.PGXADataSource;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.atomikos.jdbc.AtomikosDataSourceBean;

@Configuration
@EnableJpaRepositories(basePackages = {"mattmck.mywebservice.persistence.db2"},
	//transactionManagerRef = "db2TransactionManager",	
	entityManagerFactoryRef = "db2EntityManagerFactory")
@EnableTransactionManagement
public class Db2RepositoryConfig {


	@Bean
    @ConfigurationProperties("spring.datasource.db2")
    public DataSourceProperties db2DataSourceProperties() {
        return new DataSourceProperties();
    }

    @Bean
    //@Primary
    public DataSource db2DataSource() {

    	PGXADataSource ds = new PGXADataSource(); 
    	ds.setURL(db2DataSourceProperties().getUrl());
    	ds.setUser(db2DataSourceProperties().getUsername());
    	ds.setPassword(db2DataSourceProperties().getPassword());
    	
    	AtomikosDataSourceBean xaDS = new AtomikosDataSourceBean();
        xaDS.setXaDataSourceClassName("org.postgresql.Driver");
        xaDS.setXaDataSource(ds);       
        xaDS.setUniqueResourceName("xa_ds2");
        //xaDS.setMaxPoolSize(3);
        return xaDS;

//		DriverManagerDataSource driver = new DriverManagerDataSource();
//		driver.setDriverClassName("org.postgresql.Driver");
//		driver.setUrl("jdbc:postgresql://localhost:5432/db2");
//		driver.setUsername("postgres");
//		driver.setPassword("password");
//		return driver;
        
//        return db2DataSourceProperties()
//          .initializeDataSourceBuilder()
//          .build();
    }
    
    @Bean
    public LocalContainerEntityManagerFactoryBean db2EntityManagerFactory(
      @Qualifier("db2DataSource") DataSource dataSource,
      EntityManagerFactoryBuilder builder) {
        return builder
          .dataSource(dataSource)
          .packages("mattmck.mywebservice.persistence.db2")
          .persistenceUnit("db2PersistenceUnit")
          .jta(true)
          .build();
    }


//	@Bean
//	public EntityManagerFactory db2EntityManagerFactory(@Qualifier("db2") DataSource dataSource) {
//
//		HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
//		vendorAdapter.setGenerateDdl(true);
//
//		LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
//		factory.setJpaVendorAdapter(vendorAdapter);
//		factory.setPackagesToScan("mattmck.mywebservice.persistence.db2");
//		factory.setDataSource(dataSource);
//		factory.setPersistenceUnitName("db2PersistenceUnit");
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
    
//    @Bean
//    public PlatformTransactionManager db2TransactionManager(
//      @Qualifier("db2EntityManagerFactory") LocalContainerEntityManagerFactoryBean db2EntityManagerFactory) {
//        return new JpaTransactionManager(Objects.requireNonNull(db2EntityManagerFactory.getObject()));
//    }

//	@Bean
//	public PlatformTransactionManager db2TransactionManager() {
//
//		JpaTransactionManager txManager = new JpaTransactionManager();
//		txManager.setEntityManagerFactory(db2EntityManagerFactory());
//		return txManager;
//	}
}
