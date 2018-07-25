package com.fundooNotes.labels.dao;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.fundooNotes.labels.model.Label;
import com.fundooNotes.user.model.User;

@Repository
public class LabelDaoImpl implements LabelDao {

	@Autowired
	SessionFactory sessionFactory;

	@Override
	public Integer save(Label label) {
		Integer id = (Integer) sessionFactory.getCurrentSession().save(label);
		return id;
	}

	@Override
	public void update(Label label) {
		sessionFactory.getCurrentSession().update(label);
	}

	@Override
	public void delete(Label label) {
		sessionFactory.getCurrentSession().createQuery("delete from Label where id= :id")
		.setParameter("id", label.getId()).executeUpdate();
	}

	@Override
	public List<Label> getLabels(User user) {
		// TODO Auto-generated method stub
		return null;
	}

}
