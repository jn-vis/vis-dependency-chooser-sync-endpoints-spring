package com.ccp.vis.controller;

import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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

	
	//FIXME CURRICULOS POR E-MAIL PARA RECRUTADORES
	@PostMapping("/resumes/sending/email")
	public Map<String, Object> sendResumesToEmail(
			@RequestParam("resumeIds") List<String> resumeIds,
			@RequestParam("emails") List<String> emails,
			@RequestBody String sessionValues
			){
		
		CcpJsonRepresentation json = new CcpJsonRepresentation(sessionValues)
				.put("resumeIds", resumeIds)
				.put("emails", emails)
				;
		
		Map<String, Object> result = SyncServiceVisRecruiter.INSTANCE.sendResumesToEmail(json).content;
	
		return result;
	}

	@GetMapping("/resumes/seen/{opinionType}")
	public Map<String, Object> getAlreadySeenResumes(
			@PathVariable("opinionType") String opinionType
			,@RequestBody String sessionValues
			){
		
		CcpJsonRepresentation json = new CcpJsonRepresentation(sessionValues)
				.put("opinionType", opinionType)
				;
		
		Map<String, Object> result = SyncServiceVisRecruiter.INSTANCE.getAlreadySeenResumes(json).content;
	
		return result;
	}

	@GetMapping("/positions/{positionStatus}")
	public Map<String, Object> getPositionsFromThisRecruiter(
			@PathVariable("positionStatus") String positionStatus
			,@RequestBody String sessionValues
			){
		
		CcpJsonRepresentation json = new CcpJsonRepresentation(sessionValues)
				.put("positionStatus", positionStatus)
				;
		
		Map<String, Object> result = SyncServiceVisRecruiter.INSTANCE.getPositionsFromThisRecruiter(json).content;
	
		return result;
	}
	
	@PostMapping("/resumes/{resumeId}")
	public Map<String, Object> changeOpinionAboutThisResume(
			@PathVariable("resumeId") String resumeId,
			@RequestBody String sessionValues
			){
		
		CcpJsonRepresentation json = new CcpJsonRepresentation(sessionValues)
				.put("resumeId", resumeId)
				;
		
		Map<String, Object> result = SyncServiceVisRecruiter.INSTANCE.changeOpinionAboutThisResume(json).content;
	
		return result;
	}
	@PostMapping("/resumes/{resumeId}")
	public Map<String, Object> saveOpinionAboutThisResume(
			@PathVariable("resumeId") String resumeId,
			@RequestBody String sessionValues
			){
		
		CcpJsonRepresentation json = new CcpJsonRepresentation(sessionValues)
				.put("resumeId", resumeId)
				;
		
		Map<String, Object> result = SyncServiceVisRecruiter.INSTANCE.saveOpinionAboutThisResume(json).content;
	
		return result;
	}

}
