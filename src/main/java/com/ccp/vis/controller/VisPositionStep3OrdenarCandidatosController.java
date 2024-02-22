package com.ccp.vis.controller;

import java.util.Map;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ccp.validation.CcpJsonFieldsValidations;
import com.ccp.vis.sync.validations.VisPositionStep3OrdenarCandidatosValidations;

@CrossOrigin
@RestController
@RequestMapping(value = "recruiter/{email}/positions/{title}/steps/3")
public class VisPositionStep3OrdenarCandidatosController {

	@PostMapping
	public Map<String, String> create(@RequestBody Map<String, Object> json){
		CcpJsonFieldsValidations.validate(VisPositionStep3OrdenarCandidatosValidations.class, json);
		return null;
	}

	@PatchMapping
	public Map<String, String> update(@RequestBody Map<String, Object> json){
		CcpJsonFieldsValidations.validate(VisPositionStep3OrdenarCandidatosValidations.class, json);
		return null;
	}
	
}
