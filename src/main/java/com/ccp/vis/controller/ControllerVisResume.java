package com.ccp.vis.controller;

import java.util.Map;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ccp.decorators.CcpJsonRepresentation;
import com.ccp.jn.vis.sync.service.SyncServiceVisResume;

@CrossOrigin
@RestController
@RequestMapping(value = "/resume/{email}")
public class ControllerVisResume{
	
	@RequestMapping(method = {RequestMethod.POST, RequestMethod.PATCH})
	public Map<String, Object> save(@RequestBody Map<String, Object> sessionValues) {

		CcpJsonRepresentation json = new CcpJsonRepresentation(sessionValues);
		
		Map<String, Object> save = SyncServiceVisResume.INSTANCE.save(json).content;
		
		return save;
	}

	@DeleteMapping
	public Map<String, Object> delete(@RequestBody Map<String, Object> sessionValues){
		
		CcpJsonRepresentation json = new CcpJsonRepresentation(sessionValues);

		Map<String, Object> delete = SyncServiceVisResume.INSTANCE.delete(json).content;
	
		return delete;
	}

	@DeleteMapping("/status")
	public Map<String, Object> changeStatus(@RequestBody Map<String, Object> sessionValues){
		
		CcpJsonRepresentation json = new CcpJsonRepresentation(sessionValues);
		
		Map<String, Object> changeStatus = SyncServiceVisResume.INSTANCE.changeStatus(json).content;
	
		return changeStatus;
	}

	@GetMapping
	public Map<String, Object> getData(@RequestBody Map<String, Object> sessionValues){
		
		CcpJsonRepresentation json = new CcpJsonRepresentation(sessionValues);
		
		Map<String, Object> changeStatus = SyncServiceVisResume.INSTANCE.getData(json).content;
	
		return changeStatus;
	}

	@GetMapping("/viewMode/{viewMode}")
	public Map<String, Object> getResumeFile(
			@PathVariable("viewMode") String viewMode, 
			@RequestBody Map<String, Object> sessionValues){
		
		CcpJsonRepresentation json = new CcpJsonRepresentation(sessionValues).put("viewMode", viewMode);
		
		CcpJsonRepresentation put = SyncServiceVisResume.INSTANCE.getResumeFile(json);
		
		return put.content;
	}

}
