package com.quantal.clickbait.configurations.jpa;

//import org.apache.tomcat.jdbc.pool.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
//import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

/**
 * Created by dman on 20/10/2016.
 */
@Configuration
@EnableJpaRepositories(basePackages = "com.quantal.clickbait.repositories")
@EntityScan("com.quantal.clickbait.entities")
@EnableTransactionManagement
public class JpaConfig {


  private Environment env;


  private final String DB_DRIVER = "org.postgresql.Driver";
  private final String DB_URL;
  private final String DB_USERNAME;
  private final String DB_PASSWORD;

 @Autowired
  public JpaConfig(Environment env){
   this.env = env;
    DB_URL = env.getProperty("spring.datasource.url");
    DB_USERNAME = env.getProperty("spring.datasource.username");
    DB_PASSWORD = env.getProperty("spring.datasource.password");
  }
  @Bean
  public DataSource dataSource() {

    /**EmbeddedDatabaseBuilder builder = new EmbeddedDatabaseBuilder();
    return builder.setType(EmbeddedDatabaseType.HSQL).build();**/

    DriverManagerDataSource driver = new DriverManagerDataSource();
    driver.setDriverClassName(DB_DRIVER);
    driver.setUrl(DB_URL);
    driver.setUsername(DB_USERNAME);
    driver.setPassword(DB_PASSWORD);
    return driver;
  }

  @Bean
  public EntityManagerFactory entityManagerFactory() {

    HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
    vendorAdapter.setGenerateDdl(true);

    LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
    factory.setJpaVendorAdapter(vendorAdapter);
    factory.setPackagesToScan("com.quantal.clickbait.entities");
    factory.setDataSource(dataSource());
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
