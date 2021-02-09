package com.project2.config;

import java.io.FileInputStream;
import java.util.Properties;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import oracle.jdbc.pool.OracleDataSource;

@Configuration
@EnableTransactionManagement
public class HibernateConfig {

	@Bean
    public LocalSessionFactoryBean sessionFactory() {
        LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
        sessionFactory.setDataSource(dataSource());
        sessionFactory.setPackagesToScan("com.project2.demo.entities");
        sessionFactory.setHibernateProperties(hibernateProperties());
        return sessionFactory;
    }
	
    @Bean
    public DataSource dataSource() {
        try {
            OracleDataSource dataSource = new OracleDataSource();
            
            Properties props = new Properties();
            FileInputStream input = new FileInputStream(HibernateConfig.class.getClassLoader().getResource("application.properties").getFile());
            props.load(input);
            
            dataSource.setDriverType(props.getProperty("spring.datasource.driver-class-name"));
            dataSource.setURL(props.getProperty("spring.datasource.url"));
            dataSource.setUser(props.getProperty("spring.datasource.username"));
            dataSource.setPassword(props.getProperty("spring.datasource.password"));
            return dataSource;
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return null;
    }
    
    @Bean
    public PlatformTransactionManager hibernateTransactionManager() {
        HibernateTransactionManager transactionManager
          = new HibernateTransactionManager();
        transactionManager.setSessionFactory(sessionFactory().getObject());
        return transactionManager;
    }
    
    private final Properties hibernateProperties() {
        Properties hibernateProperties = new Properties();
        hibernateProperties.setProperty(
          "hibernate.hbm2ddl.auto", "validate");
        hibernateProperties.setProperty(
          "hibernate.dialect", "org.hibernate.dialect.Oracle10gDialect");
        return hibernateProperties;
    }

}
