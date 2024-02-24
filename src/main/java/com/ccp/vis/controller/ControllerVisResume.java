package com.ccp.vis.controller;

import java.util.Map;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ccp.decorators.CcpJsonRepresentation;
import com.ccp.jn.vis.sync.service.SyncServiceVisResume;
import com.ccp.validation.CcpJsonFieldsValidations;
import com.ccp.vis.sync.validations.JsonFieldsValidationsVisResume;

@CrossOrigin
@RestController
@RequestMapping(value = "/resume/{email}")
public class ControllerVisResume {

	private final SyncServiceVisResume service = new SyncServiceVisResume();
	
	@PostMapping
	public Map<String, String> create(@PathVariable("email") String email, @RequestBody Map<String, Object> json) {
		CcpJsonFieldsValidations.validate(JsonFieldsValidationsVisResume.class, json);
		this.service.save(email, new CcpJsonRepresentation(json));
		return null;
	}

	@PatchMapping
	public Map<String, String> update(@PathVariable("email") String email, @RequestBody Map<String, Object> json) {
		CcpJsonFieldsValidations.validate(JsonFieldsValidationsVisResume.class, json);
		this.service.save(email, new CcpJsonRepresentation(json));
		return null;
	}

}
