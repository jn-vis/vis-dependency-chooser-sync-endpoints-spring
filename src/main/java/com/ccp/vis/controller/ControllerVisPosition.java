package com.ccp.vis.controller;

import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ccp.decorators.CcpJsonRepresentation;
import com.ccp.jn.vis.sync.service.SyncServiceVisPosition;
@CrossOrigin
@RestController
@RequestMapping(value = "recruiters/{email}/positions/{title}")
public class ControllerVisPosition {

	private final SyncServiceVisPosition service = new SyncServiceVisPosition();
	
	@RequestMapping(method = {RequestMethod.POST, RequestMethod.PATCH})
	public Map<String, Object> create(@RequestBody String sessionValues){
		CcpJsonRepresentation json = new CcpJsonRepresentation(sessionValues);
		Map<String, Object> result = this.service.save(json).content;
		return result;
	}
	
	@DeleteMapping("/status")
	public Map<String, Object> changeStatus(@RequestBody String sessionValues){
		
		CcpJsonRepresentation json = new CcpJsonRepresentation(sessionValues);
		
		Map<String, Object> result = this.service.changeStatus(json).content;
	
		return result;
	}

	@GetMapping
	public Map<String, Object> getData(@RequestBody String sessionValues){
		
		CcpJsonRepresentation json = new CcpJsonRepresentation(sessionValues);
		
		Map<String, Object> result = this.service.getData(json).content;
	
		return result;
	}
	
	
	@GetMapping("/resumes/fromIndex/{fromIndex}/toIndex/{toIndex}")
	public Map<String, Object> getResumeList(
			@PathVariable("fromIndex") String fromIndex,
			@PathVariable("toIndex") String toIndex,
			@PathVariable("title") String title,
			@RequestBody String sessionValues
			){
		
		CcpJsonRepresentation json = new CcpJsonRepresentation(sessionValues)
				.put("fromIndex", fromIndex)
				.put("toIndex", toIndex)
				.put("title", title)
				;
		
		Map<String, Object> result = this.service.getResumeList(json).content;
	
		return result;
	}

	@PostMapping("/resumes/{resumeId}/viewMode/{viewMode}")
	public Map<String, Object> getResumeContent(
			@PathVariable("resumeId") String resumeId,
			@PathVariable("viewMode") String viewMode,
			@PathVariable("title") String title,
			@RequestBody String sessionValues
			){
		
		CcpJsonRepresentation json = new CcpJsonRepresentation(sessionValues)
				.put("resumeId", resumeId)
				.put("viewMode", viewMode)
				.put("title", title)
				;
		
		Map<String, Object> result = this.service.getResumeContent(json).content;
	
		return result;
	}
	
	@PostMapping("/resumes/sending/email")
	public Map<String, Object> sendResumesToEmail(
			@RequestParam("resumeIds") List<String> resumeIds,
			@RequestParam("emails") List<String> emails,
			@PathVariable("title") String title,
			@RequestBody String sessionValues
			){
		
		CcpJsonRepresentation json = new CcpJsonRepresentation(sessionValues)
				.put("resumeIds", resumeIds)
				.put("emails", emails)
				.put("title", title)
				;
		
		Map<String, Object> result = this.service.sendResumesToEmail(json).content;
	
		return result;
	}
	
	@PostMapping("/words")
	public Map<String, Object> getImportantSkillsFromText(
			@PathVariable("title") String title,
			@RequestBody String sessionValues
			){
		
		CcpJsonRepresentation json = new CcpJsonRepresentation(sessionValues)
				.put("title", title)
				;
		
		Map<String, Object> result = this.service.getImportantSkillsFromText(json).content;
	
		return result;
	}

	@PatchMapping("/words")
	public Map<String, Object> suggestNewSkills(
			@PathVariable("title") String title,
			@RequestBody String sessionValues
			){
		
		CcpJsonRepresentation json = new CcpJsonRepresentation(sessionValues)
				.put("title", title)
				;
		
		Map<String, Object> result = this.service.suggestNewSkills(json).content;
	
		return result;
	}

}
