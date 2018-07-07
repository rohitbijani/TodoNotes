package com.fundooNotes.notes.controller;

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

import com.fundooNotes.notes.model.CreateNoteDto;
import com.fundooNotes.notes.model.UpdateNoteDto;
import com.fundooNotes.notes.service.NoteService;

@RestController
public class NoteController {
	@Autowired
	NoteService noteService;
	
	@RequestMapping(value = "/create-note", method = RequestMethod.POST)
	public ResponseEntity<Void> create(@Valid @RequestBody CreateNoteDto createNoteDto, HttpServletRequest request) {
		noteService.createNote(createNoteDto, request);
		return new ResponseEntity<Void>(HttpStatus.CREATED);
	}

	@RequestMapping(value = "/delete-note/{id}", method = RequestMethod.POST)
	public ResponseEntity<Void> delete(@PathVariable Integer id, HttpServletRequest request) {
		noteService.deleteNote(id, request);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
	
	@RequestMapping(value = "/update-note/{id}", method = RequestMethod.POST)
	public ResponseEntity<Void> update(@PathVariable Integer id, @RequestBody UpdateNoteDto updateNoteDto, HttpServletRequest request) {
		noteService.updateNote(id, updateNoteDto, request);
		return new ResponseEntity<Void>(HttpStatus.OK);
		
	}
	
	@RequestMapping(value = "/view-notes", method = RequestMethod.GET)
	public ResponseEntity<Void> viewNotes() {
		return new ResponseEntity<Void>(HttpStatus.OK);
		
	}
}
