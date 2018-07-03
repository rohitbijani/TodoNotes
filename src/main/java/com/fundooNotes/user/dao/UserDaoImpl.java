package com.fundooNotes.user.dao;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.fundooNotes.user.model.User;

@Repository
public class UserDaoImpl implements UserDao {

	@Autowired
	SessionFactory sessionFactory;
	
	@Override
	public Integer save(User user) {
		Integer id=(Integer) sessionFactory.getCurrentSession().save(user);
		return id;
	}

	@SuppressWarnings("deprecation")
	@Override
	public User getUserByEmail(String email) {
		Session session=sessionFactory.getCurrentSession();
		Criteria criteria=session.createCriteria(User.class);
		criteria.add(Restrictions.eq("email", email));
		User userLogin=(User) criteria.uniqueResult();
		if(userLogin!=null)
			return userLogin;
		
		return null;
	}

	@SuppressWarnings("deprecation")
	@Override
	public User getUserById(Integer id) {
		Session session=sessionFactory.getCurrentSession();
		Criteria criteria=session.createCriteria(User.class);
		criteria.add(Restrictions.eq("id", id));
		User userLogin=(User) criteria.uniqueResult();
		if(userLogin!=null)
			return userLogin;
		
		return null;
	}

}
