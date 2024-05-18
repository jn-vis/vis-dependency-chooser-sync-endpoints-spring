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
	
	private final SyncServiceVisResume service = new SyncServiceVisResume();

	@RequestMapping(method = {RequestMethod.POST, RequestMethod.PATCH})
	public Map<String, Object> save(@RequestBody Map<String, Object> sessionValues) {

		CcpJsonRepresentation values = new CcpJsonRepresentation(sessionValues);
		
		Map<String, Object> save = this.service.save(values).content;
		
		return save;
	}

	@DeleteMapping
	public Map<String, Object> delete(@RequestBody Map<String, Object> sessionValues){
		
		CcpJsonRepresentation values = new CcpJsonRepresentation(sessionValues);

		Map<String, Object> delete = this.service.delete(values).content;
	
		return delete;
	}

	@DeleteMapping("/status")
	public Map<String, Object> changeStatus(@RequestBody Map<String, Object> sessionValues){
		
		CcpJsonRepresentation values = new CcpJsonRepresentation(sessionValues);
		
		Map<String, Object> changeStatus = this.service.changeStatus(values).content;
	
		return changeStatus;
	}

	@GetMapping
	public Map<String, Object> getData(@RequestBody Map<String, Object> sessionValues){
		
		CcpJsonRepresentation values = new CcpJsonRepresentation(sessionValues);
		
		Map<String, Object> changeStatus = this.service.getData(values).content;
	
		return changeStatus;
	}

	@GetMapping("/type/{contentType}")
	public Map<String, Object> getResumeFile(
			@PathVariable("contentType") String contentType, 
			@RequestBody Map<String, Object> sessionValues){
		
		CcpJsonRepresentation values = new CcpJsonRepresentation(sessionValues).put("contentType", contentType);
		
		CcpJsonRepresentation put = this.service.getResumeFile(values);
		
		return put.content;
	}

}
