package com.fundooNotes.labels.controller;

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
import com.fundooNotes.labels.model.LabelDto;
import com.fundooNotes.labels.service.LabelService;
import com.fundooNotes.notes.model.Note;
import com.fundooNotes.user.model.Response;

@RestController
public class LabelController {
	@Autowired
	LabelService labelService;
	@Autowired
	Response response;
	
	@RequestMapping(value = "/create-label", method = RequestMethod.POST)
	public ResponseEntity<Response> create(@Valid @RequestBody LabelDto labelDto, HttpServletRequest request) {
		String token = request.getHeader("token");
		labelService.createLabel(labelDto, token);
		response.setMessage("Label created");
		return new ResponseEntity<>(response, HttpStatus.CREATED);
	}
	
	@RequestMapping(value = "/delete-label", method = RequestMethod.DELETE)
	public ResponseEntity<Response> delete(@RequestBody LabelDto labelDto, HttpServletRequest request) {
		String token = request.getHeader("token");
		labelService.deleteLabel(labelDto, token);
		response.setMessage("Label deleted");
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/update-label", method = RequestMethod.PUT)
	public ResponseEntity<Response> update(@RequestBody LabelDto labelDto, HttpServletRequest request) {
		String token = request.getHeader("token");
		labelService.updateLabel(labelDto, token);
		response.setMessage("Note updated");
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/view-labels", method = RequestMethod.GET)
	public ResponseEntity<List<Label>> viewNotes(HttpServletRequest request) {
		String token = request.getHeader("token");
		List<Label>labels=labelService.getLabels(token);
		return new ResponseEntity<>(labels, HttpStatus.OK);		
	}

}
