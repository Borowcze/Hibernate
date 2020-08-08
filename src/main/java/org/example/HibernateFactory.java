package org.example;

import org.example.db.Author;
import org.example.db.Badge;
import org.example.db.Movie;
import org.example.db.User;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

public class HibernateFactory {
	private Configuration getHibernateConfig() {
		Configuration configuration = new Configuration();
		configuration.setProperty("hibernate.connection.url", "jdbc:hsqldb:file:db-data/mydatabase");
		configuration.setProperty("hibernate.connection.username", "admin123");
		configuration.setProperty("hibernate.connection.password", "admin123");
		configuration.setProperty("hibernate.dialect", "org.hibernate.dialect.HSQLDialect");
		configuration.setProperty("hibernate.connection.driver_class", "org.hsqldb.jdbc.JDBCDriver");
		configuration.setProperty("hibernate.hbm2ddl.auto", "update");
//		configuration.setProperty("hibernate.show_sql", "true");
		configuration.addAnnotatedClass(Author.class);
		configuration.addAnnotatedClass(Movie.class);
		configuration.addAnnotatedClass(User.class);
		configuration.addAnnotatedClass(Badge.class);

		return configuration;
	}

	public SessionFactory getSessionFactory() {
		StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
											   .applySettings(getHibernateConfig().getProperties()).build();
		return getHibernateConfig().buildSessionFactory(registry);
	}
}