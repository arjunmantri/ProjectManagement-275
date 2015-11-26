package edu.sjsu.cmpe275.dao.impl;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import edu.sjsu.cmpe275.dao.interfaces.IEmailDao;
import edu.sjsu.cmpe275.dto.User;

@Repository
public class EmailDAOImpl implements IEmailDao {

	EmailDAOImpl () {
	}

	@Autowired
	private SessionFactory sessionFactory;

	public void createUserSignUp(User user) {
		sessionFactory.getCurrentSession().save(user);

	}


}