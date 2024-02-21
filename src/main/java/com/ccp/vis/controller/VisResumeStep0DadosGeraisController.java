package com.ccp.vis.controller;

import java.util.Map;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ccp.validation.CcpJsonFieldsValidations;
import com.ccp.vis.sync.validations.resumes.steps.VisResumeStep0DadosGeraisValidations;

@CrossOrigin
@RestController
@RequestMapping(value = "/resume/{email}/steps/0")
public class VisResumeStep0DadosGeraisController {

	@PostMapping
	public Map<String, String> create(@RequestBody Map<String, Object> body){
		CcpJsonFieldsValidations.validate(VisResumeStep0DadosGeraisValidations.class, body);
		return null;
	}

	@PatchMapping
	public Map<String, String> update(@RequestBody Map<String, Object> body){
		CcpJsonFieldsValidations.validate(VisResumeStep0DadosGeraisValidations.class, body);
		return null;
	}
	
}
