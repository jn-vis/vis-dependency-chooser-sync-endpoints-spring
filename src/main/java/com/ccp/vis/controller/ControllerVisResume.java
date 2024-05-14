package com.ccp.vis.controller;

import java.util.Map;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ccp.decorators.CcpJsonRepresentation;
import com.ccp.jn.vis.sync.service.SyncServiceVisResume;
import com.ccp.web.spring.utils.CcpSyncSessionValuesExtractor;

import jakarta.servlet.http.HttpServletRequest;

@CrossOrigin
@RestController
@RequestMapping(value = "/resume/{email}")
public class ControllerVisResume  implements CcpSyncSessionValuesExtractor{

	private final SyncServiceVisResume service = new SyncServiceVisResume();

	@RequestMapping(method = {RequestMethod.POST, RequestMethod.PATCH})
	public Map<String, Object> save(@PathVariable("email") String email, 
			@RequestBody Map<String, Object> json, HttpServletRequest request
			
			) {
		CcpJsonRepresentation sessionValues = this.getSessionValues(request, email);
		CcpJsonRepresentation resume = sessionValues.putAll(json);
		Map<String, Object> save = this.service.save(resume);
		return save;
	}

	public Map<String, Object> delete(@PathVariable("email") String email, HttpServletRequest request){
	
		CcpJsonRepresentation sessionValues = this.getSessionValues(request, email);
		
		Map<String, Object> delete = this.service.delete(sessionValues);
		return delete;
	}
}
