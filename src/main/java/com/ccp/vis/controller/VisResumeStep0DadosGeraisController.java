package com.ccp.vis.controller;

import java.util.Map;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ccp.fields.validations.annotations.ValidationRules;
import com.ccp.vis.sync.validations.resumes.step0.VisResumeStep0DadosGeraisValidations;

@CrossOrigin
@RestController
@RequestMapping(value = "/resume/{email}/steps/0")
public class VisResumeStep0DadosGeraisController {

	@PostMapping
	@ValidationRules(rulesClass = VisResumeStep0DadosGeraisValidations.class)
	public Map<String, String> create(@RequestBody Map<String, Object> body){
		return null;
	}

	@PatchMapping
	@ValidationRules(rulesClass = VisResumeStep0DadosGeraisValidations.class)
	public Map<String, String> update(@RequestBody Map<String, Object> body){
		return null;
	}
	
}
