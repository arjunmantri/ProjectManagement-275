package edu.sjsu.cmpe275.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
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
	
	@Override
	public void createUserSignUp(User user) {
		sessionFactory.getCurrentSession().save(user);
	}

	@Override
	public User getUserDetail(String emailId) {
		return (User) sessionFactory.getCurrentSession().get(User.class, emailId);
    }

	@Override
	public boolean isUserValidated(String emailId, String password, String userName) {
		User user = getUserDetail(emailId);
		if(user!=null && user.getPassword().equals(password) && user.getValidated().equals(true) 
				&& userName.equals(userName) && user.getEmail().equals(emailId)) {
			return true;
		}
		return false;
	}
	
	@Override
	public void updateUser(User updatedUser) {
		sessionFactory.getCurrentSession().merge(updatedUser);
	}
	
	@Override
	public User userSignIn(String userName, String password) {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(User.class);
		criteria.add(Restrictions.and(Restrictions.eq("userName", userName),
				Restrictions.eq("password", password)));
		List<User> user =  criteria.list();
		if(user.size() != 0) {
			return user.get(0);
		}
		return null;
	}
	
	@Override
	public boolean validateUserName(String userName) {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(User.class);
		criteria.add(Restrictions.and(Restrictions.eq("userName", userName)));
		List<User> user =  criteria.list();
		if(user.size() != 0) {
			return userName.equalsIgnoreCase(user.get(0).getUserName());
		}
		return false;
	}
}