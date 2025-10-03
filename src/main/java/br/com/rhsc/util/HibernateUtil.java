package br.com.rhsc.util;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Environment;

import br.com.rhsc.entity.Category;
import br.com.rhsc.entity.Task;

public class HibernateUtil {
	
	@ConfigProperty(name="data.db.user")
	private String user;
    
    private static final SessionFactory sessionFactory = buildSessionFactory();
    
    private static SessionFactory buildSessionFactory() {
        try {
            // Configuração programática do Hibernate 7.x
            StandardServiceRegistry standardRegistry = new StandardServiceRegistryBuilder()
                    .applySettings(getHibernateProperties())
                    .build();
            
            Metadata metadata = new MetadataSources(standardRegistry)
                    .addAnnotatedClass(Task.class)
                    .addAnnotatedClass(Category.class)
                    .getMetadataBuilder()
                    .build();
            
            return metadata.getSessionFactoryBuilder().build();
        } catch (Exception e) {
            e.printStackTrace();
            throw new ExceptionInInitializerError("Falha ao criar SessionFactory: " + e.getMessage());
        }
    }
    
    private static Map<String, Object> getHibernateProperties() throws IOException {
        Map<String, Object> settings = new HashMap<>();
        
        // Configurações do banco de dados
        settings.put(Environment.JAKARTA_JDBC_DRIVER, "com.mysql.cj.jdbc.Driver");
        settings.put(Environment.JAKARTA_JDBC_URL, "jdbc:mysql://localhost:3306/dio_java?useSSL=false&serverTimezone=UTC");
        settings.put(Environment.JAKARTA_JDBC_USER, "root");
        settings.put(Environment.JAKARTA_JDBC_PASSWORD, "");
        
        // Configurações do Hibernate
        settings.put(Environment.DIALECT, "org.hibernate.dialect.MySQLDialect");
        settings.put(Environment.SHOW_SQL, "true");
        settings.put(Environment.FORMAT_SQL, "false");
        settings.put(Environment.HBM2DDL_AUTO, "update");
        settings.put(Environment.CURRENT_SESSION_CONTEXT_CLASS, "thread");
        
//        // Configurações do banco de dados
//        settings.put(Environment.JAKARTA_JDBC_DRIVER, PropertiesApplication.loadProperties().getProperty("data.db.driver"));
//        settings.put(Environment.JAKARTA_JDBC_URL, PropertiesApplication.loadProperties().getProperty("data.db.url"));
//        settings.put(Environment.JAKARTA_JDBC_USER, PropertiesApplication.loadProperties().getProperty("data.db.user"));
//        settings.put(Environment.JAKARTA_JDBC_PASSWORD, PropertiesApplication.loadProperties().getProperty("data.db.password"));
//        
//        // Configurações do Hibernate
//        settings.put(Environment.DIALECT, PropertiesApplication.loadProperties().getProperty("data.db.dialect"));
//        settings.put(Environment.SHOW_SQL, PropertiesApplication.loadProperties().getProperty("data.db.show-sql"));
//        settings.put(Environment.FORMAT_SQL, PropertiesApplication.loadProperties().getProperty("data.db.format-sql"));
//        settings.put(Environment.HBM2DDL_AUTO, PropertiesApplication.loadProperties().getProperty("data.db.hbm2-auto"));
//        settings.put(Environment.CURRENT_SESSION_CONTEXT_CLASS, PropertiesApplication.loadProperties().getProperty("data.db.current-session-context-class"));
        
        return settings;
    }
    
    public static Session openSession() {
        return sessionFactory.openSession();
    }
    
    public static Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }
    
    public static void shutdown() {
        if (sessionFactory != null && !sessionFactory.isClosed()) {
            sessionFactory.close();
        }
    }
    
    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
}