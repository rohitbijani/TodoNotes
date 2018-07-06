package com.fundooNotes.notes.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.fundooNotes.notes.model.Note;

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

	@SuppressWarnings("deprecation")
	@Override
	public Note getNoteById(Integer id) {
		Session session=sessionFactory.getCurrentSession();
		Criteria criteria=session.createCriteria(Note.class);
		criteria.add(Restrictions.eq("id", id));
		Note note=(Note) criteria.uniqueResult();
		
		return note;
	}

	@SuppressWarnings({ "deprecation", "unchecked" })
	@Override
	public List<Note> getNotes(Integer userId) {
		Session session=sessionFactory.getCurrentSession();
		Criteria criteria=session.createCriteria(Note.class);
		criteria.add(Restrictions.eq("user_id", userId));
		List<Note> notes=criteria.list();
		
		return notes;
	}

}
