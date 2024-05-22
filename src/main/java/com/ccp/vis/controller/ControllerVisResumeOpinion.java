package com.ccp.vis.controller;

import java.util.Map;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ccp.decorators.CcpJsonRepresentation;
import com.ccp.jn.vis.sync.service.SyncServiceVisResumeOpinion;
@CrossOrigin
@RestController
@RequestMapping(value = "recruiter/{email}/resumes/{resumeId}")
public class ControllerVisResumeOpinion {

	private final SyncServiceVisResumeOpinion service = new SyncServiceVisResumeOpinion();
	
	@PostMapping("/resumes/{resumeId}/viewMode/{viewMode}")
	public Map<String, Object> getResumeContent(
			@PathVariable("resumeId") String resumeId,
			@PathVariable("viewMode") String viewMode,
			@RequestBody String sessionValues
			){
		
		CcpJsonRepresentation values = new CcpJsonRepresentation(sessionValues)
				.put("resumeId", resumeId)
				.put("viewMode", viewMode)
				;
		
		Map<String, Object> result = this.service.getResumeContent(values).content;
	
		return result;
	}

	@PostMapping("/resumes/{resumeId}/viewMode/{viewMode}")
	public Map<String, Object> changeOpinionAboutThisResume(
			@PathVariable("resumeId") String resumeId,
			@RequestBody String sessionValues
			){
		
		CcpJsonRepresentation values = new CcpJsonRepresentation(sessionValues)
				.put("resumeId", resumeId)
				;
		
		Map<String, Object> result = this.service.changeOpinionAboutThisResume(values).content;
	
		return result;
	}
	@PostMapping("/resumes/{resumeId}/viewMode/{viewMode}")
	public Map<String, Object> saveOpinionAboutThisResume(
			@PathVariable("resumeId") String resumeId,
			@RequestBody String sessionValues
			){
		
		CcpJsonRepresentation values = new CcpJsonRepresentation(sessionValues)
				.put("resumeId", resumeId)
				;
		
		Map<String, Object> result = this.service.saveOpinionAboutThisResume(values).content;
	
		return result;
	}
}
