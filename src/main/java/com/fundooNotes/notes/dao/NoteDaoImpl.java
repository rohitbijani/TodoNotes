package com.fundooNotes.notes.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.fundooNotes.notes.model.Note;
import com.fundooNotes.user.model.User;

@Repository
public class NoteDaoImpl implements NoteDao {

	@Autowired
	SessionFactory sessionFactory;
	
	@Override
	public Integer save(Note note) {
		Integer id=(Integer) sessionFactory.getCurrentSession().save(note);
		return id;
	}

	@Override
	public void update(Note note) {
		sessionFactory.getCurrentSession().update(note);
	}

	@Override
	public void delete(Note note) {
		sessionFactory.getCurrentSession().createQuery("delete from Note where id= :id")
		.setParameter("id", note.getId()).executeUpdate();
		
//		sessionFactory.getCurrentSession().delete(note);
	}
	
	@SuppressWarnings("deprecation")
	@Override
	public Note getNoteById(Integer id) {
		Session session=sessionFactory.getCurrentSession();
		Criteria criteria=session.createCriteria(Note.class);
		criteria.add(Restrictions.eq("id", id));
		Note note=(Note) criteria.uniqueResult();
		
		return note;
	}

	@SuppressWarnings({ "unchecked" })
	@Override
	public List<Note> getNotes(User user) {
		/*Session session=sessionFactory.getCurrentSession();
		Criteria criteria=session.createCriteria(Note.class);
		criteria.add(Restrictions.eq("user", user));
		List<Note> notes=criteria.list();
		*/
		
		String hql = "FROM Note WHERE user = :user";
		Query query = sessionFactory.getCurrentSession().createQuery(hql).setParameter("user", user);
		List notes = query.getResultList();
		return notes;
	}

}
