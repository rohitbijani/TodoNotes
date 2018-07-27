package com.fundooNotes.labels.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
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
	public Label getLabelById(Integer id) {
		Session session=sessionFactory.getCurrentSession();
		Criteria criteria=session.createCriteria(Label.class);
		criteria.add(Restrictions.eq("id", id));
		Label label=(Label) criteria.uniqueResult();
		
		return label;
	}

	@Override
	public List<Label> getLabels(User user) {
		Session session=sessionFactory.getCurrentSession();
		Criteria criteria=session.createCriteria(Label.class);
		criteria.add(Restrictions.eq("user", user));
		List<Label> labels=criteria.list();
		
		return labels;
	}

}
