/**
 * 
 */
package config;

import java.util.Properties;

import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;
import org.hibernate.FlushMode;
import org.hibernate.SessionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.orm.hibernate4.LocalSessionFactoryBuilder;
import org.springframework.transaction.annotation.EnableTransactionManagement;


/**
 * @author vtupe
 *Hibernate configuration class consists of
 *data source related configuration details
 */

@Configuration
@EnableTransactionManagement
public class HibernateConfig {
	
/*
	@Bean
	public DataSource dataSource() {
		BasicDataSource dataSource = new BasicDataSource();
		dataSource.setDriverClassName(com.mysql.jdbc.Driver.class.getName());
		dataSource.setUrl("jdbc:mysql://localhost:3306/lab2");
		dataSource.setUsername("root");
		dataSource.setPassword("12345");
		dataSource.setInitialSize(2);
		dataSource.setMaxTotal(5);
		return dataSource;
	}



	*//**
	 * @return HibernateTemplate() This is bean creation method for
	 *         HibernateTemplate.
	 *//*
	@Bean
	public HibernateTemplate hibernateTemplate() {
		HibernateTemplate ht=new HibernateTemplate(sessionFactory());
		//ht.getSessionFactory().getCurrentSession().setFlushMode(FlushMode.AUTO);
		return ht;

	}



	*//**
	 * @return SessionFactory() This is bean creation method for SessionFactory.
	 *//*
	@Bean
	public SessionFactory sessionFactory() {
		LocalSessionFactoryBuilder builder = new LocalSessionFactoryBuilder(dataSource());
		builder.scanPackages("*");
		Properties hibernateProperties = new Properties();
		hibernateProperties.put("hibernate.dialect", "org.hibernate.dialect.MySQL5Dialect");
		builder.addProperties(hibernateProperties);
		return builder.buildSessionFactory();
		
	}



	*//**
	 * @return HibernateTransactionManager() This is bean creation method for
	 *         HibernateTransactionManager.
	 *//*
	@Bean
	@Primary
	public HibernateTransactionManager hibTransMan() {
		return new HibernateTransactionManager(sessionFactory());
	}*/

}
