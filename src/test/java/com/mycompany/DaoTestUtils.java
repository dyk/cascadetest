
package com.mycompany;

import java.util.Properties;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.cfg.Configuration;


public abstract class DaoTestUtils {

	
	public static Properties HSQLDB_CONF = new Properties();
	
	
	static {
		
		HSQLDB_CONF.setProperty("hibernate.dialect", "org.hibernate.dialect.HSQLDialect");
		HSQLDB_CONF.setProperty("hibernate.connection.driver_class", "org.hsqldb.jdbcDriver");
		HSQLDB_CONF.setProperty("hibernate.connection.url", "jdbc:hsqldb:mem:tmecc");
		HSQLDB_CONF.setProperty("hibernate.connection.username", "sa");
		HSQLDB_CONF.setProperty("hibernate.connection.password", "");
		HSQLDB_CONF.setProperty("hibernate.connection.pool_size", "1");
		HSQLDB_CONF.setProperty("hibernate.connection.autocommit", "true");
		HSQLDB_CONF.setProperty("hibernate.hbm2ddl.auto", "create-drop");
		HSQLDB_CONF.setProperty("hibernate.show_sql", "true");
	}
	
	/**
	 * Build session factory for classes specified in annotatedClasses.xml
	 * 
	 * @param p
	 * 		hibernate properties
	 * @return 
	 * 		hibernate session factory
	 */
	public static SessionFactory buildFactory(Properties p, Class ... classes) {

		AnnotationConfiguration cfg = new AnnotationConfiguration();
		for (Class clazz : classes) {
			cfg.addAnnotatedClass(clazz);
		}
		addProperties(cfg,p);
		return cfg.buildSessionFactory();

	}
	
	public static SessionFactory buildFactory(Class ... classes) {
		return buildFactory(null, classes);
	}
	
	/**
	 * adds properties to configuration
	 * @param cfg
	 * @param properties
	 * 		configuration properties. if null then HSQLDB_CONF by default is taken
	 */
	private static void addProperties(Configuration cfg, Properties properties) {

		if (properties != null) {

			cfg.addProperties(properties);
		}
		else {
			
			cfg.addProperties(HSQLDB_CONF);
		}
		
	}

}
