package com.fundooNotes.notes.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fundooNotes.labels.model.Label;
import com.fundooNotes.notes.model.CreateNoteDto;
import com.fundooNotes.notes.model.Note;
import com.fundooNotes.notes.model.UpdateNoteDto;
import com.fundooNotes.notes.service.NoteService;
import com.fundooNotes.user.model.Response;

@RestController
public class NoteController {
	@Autowired
	NoteService noteService;
	@Autowired
	Response response;
	
	@RequestMapping(value = "/create-note", method = RequestMethod.POST)
	public ResponseEntity<Response> create(@Valid @RequestBody CreateNoteDto createNoteDto, HttpServletRequest request) {
		String token = request.getHeader("token");
		noteService.createNote(createNoteDto, token);
		response.setMessage("Note created");
		return new ResponseEntity<>(response, HttpStatus.CREATED);
	}

	@RequestMapping(value = "/delete-note/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Response> delete(@PathVariable Integer id, HttpServletRequest request) {
		String token = request.getHeader("token");
		noteService.deleteNote(id, token);
		response.setMessage("Note deleted");
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/update-note", method = RequestMethod.PUT)
	public ResponseEntity<Response> update(@RequestBody UpdateNoteDto updateNoteDto, HttpServletRequest request) {
		String token = request.getHeader("token");
		noteService.updateNote(updateNoteDto, token);
		response.setMessage("Note updated");
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/view-notes", method = RequestMethod.GET)
	public ResponseEntity<List<Note>> viewNotes(HttpServletRequest request) {
		String token = request.getHeader("token");
		List<Note>notes=noteService.getNotes(token);
		return new ResponseEntity<>(notes, HttpStatus.OK);		
	}
	
	@RequestMapping(value = "/view-labelnotes", method = RequestMethod.POST)
	public ResponseEntity<List<Note>> viewLabelNotes(@RequestBody Label label, HttpServletRequest request) {
		String token = request.getHeader("token");
		List<Note>notes=noteService.getLabelNotes(label, token);
		return new ResponseEntity<>(notes, HttpStatus.OK);
	}
}
