package com.ccp.vis.controller;

import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ccp.decorators.CcpJsonRepresentation;
import com.ccp.jn.vis.sync.service.SyncServiceVisRecruiter;
@CrossOrigin
@RestController
@RequestMapping(value = "recruiter/{email}")
public class ControllerVisRecruiter {

	private final SyncServiceVisRecruiter service = new SyncServiceVisRecruiter();
	

	@PostMapping("/resumes/sending/email")
	public Map<String, Object> sendResumesToEmail(
			@RequestParam("resumeIds") List<String> resumeIds,
			@RequestParam("emails") List<String> emails,
			@RequestBody String sessionValues
			){
		
		CcpJsonRepresentation values = new CcpJsonRepresentation(sessionValues)
				.put("resumeIds", resumeIds)
				.put("emails", emails)
				;
		
		Map<String, Object> result = this.service.sendResumesToEmail(values).content;
	
		return result;
	}

	@GetMapping("/resumes/opinions")
	public Map<String, Object> getAlreadySeenResumes(
			@RequestBody String sessionValues
			){
		
		CcpJsonRepresentation values = new CcpJsonRepresentation(sessionValues)
				;
		
		Map<String, Object> result = this.service.getAlreadySeenResumes(values).content;
	
		return result;
	}
	

}
