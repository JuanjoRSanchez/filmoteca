package ccom.filmoteca.hibernate.spring.util;

import java.util.Properties;

import javax.sql.DataSource;

//import org.hibernate.SessionFactory;
//import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
//import org.hibernate.cfg.Configuration;
//import org.hibernate.cfg.Environment;
//import org.hibernate.service.ServiceRegistry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;


public class HibernateUtil {

	
	public Properties hibernateProperties() {
		Properties properties = new Properties();
		properties.put("hibernate.dialect","org.hibernate.dialect.MYSQL5Dialect");
		properties.put("hibernate.show_sql", "true");
		return properties;
	}
	
	@Bean
	public DataSource dataSource() {
	    DriverManagerDataSource dataSource= new DriverManagerDataSource();
	    dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
	    dataSource.setUsername("root");
	    dataSource.setPassword("");
	    dataSource.setUrl("jdbc:mysql://localhost:3306/prueba");
	    return dataSource;
	}
	
	@Bean
	public LocalSessionFactoryBean sessionFactory() {
	    LocalSessionFactoryBean localSessionFactoryBean = new LocalSessionFactoryBean();
	    localSessionFactoryBean.setDataSource(dataSource());
	    localSessionFactoryBean.setPackagesToScan("ccom.filmoteca.hibernate.spring.model");
	    localSessionFactoryBean.setHibernateProperties(hibernateProperties());
	    return localSessionFactoryBean;
	}

	@Bean
	@Autowired
	public HibernateTransactionManager transactionManager () {
	    HibernateTransactionManager hibernateTransactionManager = new HibernateTransactionManager();
	    hibernateTransactionManager.setSessionFactory(sessionFactory().getObject());
	    return hibernateTransactionManager;
	}

	
//	private static SessionFactory sessionFactory;
//	
//	public static SessionFactory getSessionFactory() {
//		if(sessionFactory == null) {
//			try {
//				Configuration configuration = new Configuration();
//				
//				//Hibernate settings
//				Properties settings = new Properties();
//				settings.put(Environment.DRIVER, "com.mysql.cj.jdbc.Driver");
//				settings.put(Environment.URL, "jdbc:mysql://localhost:3306/prueba");
//				settings.put(Environment.USER, "root");
//				settings.put(Environment.PASS, "");
//				settings.put(Environment.DIALECT, "org.hibernate.dialect.MYSQL5Dialect");
//				
//				settings.put(Environment.SHOW_SQL, "true");
//				settings.put(Environment.CURRENT_SESSION_CONTEXT_CLASS, "thread");
//				
//				settings.put(Environment.HBM2DDL_AUTO, "create-drop");
//				
//				configuration.setProperties(settings);
//				configuration.addAnnotatedClass(null);
//				
//				ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
//						.applySettings(configuration.getProperties()).build();
//				
//				sessionFactory = configuration.buildSessionFactory(serviceRegistry);
//					
//			
//				
//			}catch(Exception e){
//				e.printStackTrace();
//			}
//		}
//		return sessionFactory;
//		
//	}
	
}
