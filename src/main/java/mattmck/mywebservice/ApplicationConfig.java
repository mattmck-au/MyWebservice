package mattmck.mywebservice;

import org.springframework.context.annotation.Configuration;

@Configuration
//@EnableJpaRepositories
//@EnableTransactionManagement
public class ApplicationConfig {

//	spring.datasource.url=jdbc:postgresql://localhost:5432/postgres
//		spring.datasource.username=postgres
//		spring.datasource.password=password
//		spring.jpa.hibernate.ddl-auto=<create | create-drop | update | validate | none>
//		spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.PostgreSQLDialect

//	@Bean
//	public DataSource dataSource() {
//
//		DriverManagerDataSource driver = new DriverManagerDataSource();
//		driver.setDriverClassName("org.postgresql.Driver");
//		driver.setUrl("jdbc:postgresql://localhost:5432/postgres");
//		driver.setUsername("postgres");
//		driver.setPassword("password");
//		return driver;
//
////	    EmbeddedDatabaseBuilder builder = new EmbeddedDatabaseBuilder();
////	    return builder.setType(EmbeddedDatabaseType.HSQL).build();
//	}
//
//	@Bean
//	public EntityManagerFactory entityManagerFactory() {
//
//		HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
//		vendorAdapter.setGenerateDdl(true);
//
//		LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
//		factory.setJpaVendorAdapter(vendorAdapter);
//		factory.setPackagesToScan("mattmck.mywebservice.persistence.db1");
//		factory.setDataSource(dataSource());
////		factory.setJpaDialect(
//		factory.afterPropertiesSet();
//
//		return factory.getObject();
//	}
//
//	@Bean
//	public PlatformTransactionManager transactionManager() {
//
//		JpaTransactionManager txManager = new JpaTransactionManager();
//		txManager.setEntityManagerFactory(entityManagerFactory());
//		return txManager;
//	}

}
