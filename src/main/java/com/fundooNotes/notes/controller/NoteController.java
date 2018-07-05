package com.fundooNotes.notes.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fundooNotes.notes.model.CreateNoteDto;
import com.fundooNotes.notes.service.NoteService;

@RestController
public class NoteController {
	@Autowired
	NoteService noteService;
	
	@RequestMapping(value = "/create-note", method = RequestMethod.POST)
	public ResponseEntity<Void> create(@RequestBody CreateNoteDto createNoteDto, HttpServletRequest request) {
		noteService.createNote(createNoteDto, request);
		return new ResponseEntity<Void>(HttpStatus.CREATED);
	}

}
