package com.ccp.vis.controller;

import java.util.Map;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ccp.validation.CcpJsonFieldsValidations;
import com.ccp.vis.sync.validations.VisResumeStep1DemaisDadosValidations;

@CrossOrigin
@RestController
@RequestMapping(value = "/resume/{email}/steps/1")
public class VisResumeStep1DemaisDadosController {

	@PostMapping
	public Map<String, String> create(@RequestBody Map<String, Object> json){
		CcpJsonFieldsValidations.validate(VisResumeStep1DemaisDadosValidations.class, json);
		return null;
	}

	@PatchMapping
	public Map<String, String> update(@RequestBody Map<String, Object> json){
		CcpJsonFieldsValidations.validate(VisResumeStep1DemaisDadosValidations.class, json);
		return null;
	}
	
}
