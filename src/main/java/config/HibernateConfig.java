/**
 *
 */
package config;

import java.util.Properties;

import javax.sql.DataSource;

import edu.sjsu.cmpe275.dto.Project;
import edu.sjsu.cmpe275.dto.Task;
import org.apache.commons.dbcp2.BasicDataSource;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.orm.hibernate4.LocalSessionFactoryBuilder;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import edu.sjsu.cmpe275.dto.User;


/**
 * @author Team - 3
 * Hibernate configuration class consists of data source related configuration details
 */

@Configuration
@ComponentScan({ "config" })
@EnableTransactionManagement
public class HibernateConfig {

	@Bean
	public DataSource dataSource() {
		BasicDataSource dataSource = new BasicDataSource();
		dataSource.setDriverClassName(com.mysql.jdbc.Driver.class.getName());
		dataSource.setUrl("jdbc:mysql://localhost:3306/projectmanagment");
		dataSource.setUsername("root");
		dataSource.setPassword("");
		dataSource.setInitialSize(2);
		dataSource.setMaxTotal(5);
		return dataSource;
	}

	/**
	 * @return SessionFactory() This is bean creation method for SessionFactory.
	 */
	@Autowired
	@Bean(name = "sessionFactory")
	public SessionFactory sessionFactory() {
		LocalSessionFactoryBuilder builder = new LocalSessionFactoryBuilder(dataSource());
		builder.addAnnotatedClass(User.class);
		Properties hibernateProperties = new Properties();
		hibernateProperties.put("hibernate.dialect", "org.hibernate.dialect.MySQL5Dialect");
		hibernateProperties.put("hibernate.show_sql", "true");
		hibernateProperties.put("hibernate.hbm2ddl.auto", "create");
		builder.addProperties(hibernateProperties);
		builder.addAnnotatedClasses(Task.class);
		builder.addAnnotatedClasses(User.class);
		builder.addAnnotatedClasses(Project.class);
		return builder.buildSessionFactory();

	}

	/**
	 * @return HibernateTransactionManager() This is bean creation method for
	 *         HibernateTransactionManager.
	 */
	@Autowired
	@Bean
	public HibernateTransactionManager hibTransMan(SessionFactory s) {
		HibernateTransactionManager txManager = new HibernateTransactionManager();
		txManager.setSessionFactory(s);
		return txManager;
	}
}