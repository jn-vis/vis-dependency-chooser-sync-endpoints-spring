package com.ccp.vis.controller;

import java.util.Map;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ccp.constantes.CcpConstants;
import com.ccp.decorators.CcpJsonRepresentation;
import com.ccp.jn.sync.service.SyncServiceJnLogin;
import com.ccp.jn.vis.sync.service.SyncServiceVisResume;
import com.ccp.web.spring.utils.CcpSyncSessionValuesExtractor;
import com.jn.vis.commons.utils.VisCommonsUtils;

import jakarta.servlet.http.HttpServletRequest;

@CrossOrigin
@RestController
@RequestMapping(value = "/resume/{email}")
public class ControllerVisResume  implements CcpSyncSessionValuesExtractor{
	
	private final SyncServiceJnLogin loginService = new SyncServiceJnLogin();

	private final SyncServiceVisResume service = new SyncServiceVisResume();

	@RequestMapping(method = {RequestMethod.POST, RequestMethod.PATCH})
	public Map<String, Object> save(@PathVariable("email") String email, 
			@RequestBody Map<String, Object> json, HttpServletRequest request
			) {
		CcpJsonRepresentation sessionValues = this.getSessionValues(request, email);
		CcpJsonRepresentation resume = sessionValues.putAll(json);
		Map<String, Object> save = this.service.save(resume).content;
		return save;
	}

	public Map<String, Object> delete(@PathVariable("email") String email, HttpServletRequest request){
		
		CcpJsonRepresentation sessionValues = this.getSessionValues(request, email);
		
		Map<String, Object> delete = this.service.delete(sessionValues).content;
	
		return delete;
	}

	public Map<String, Object> changeStatus(@PathVariable("email") String email, HttpServletRequest request){
		
		CcpJsonRepresentation sessionValues = this.getSessionValues(request, email);
		
		Map<String, Object> changeStatus = this.service.changeStatus(sessionValues).content;
	
		return changeStatus;
	}

	public Map<String, Object> getData(@PathVariable("email") String email, HttpServletRequest request){
		
		CcpJsonRepresentation sessionValues = this.getSessionValues(request, email);
		
		Map<String, Object> changeStatus = this.service.getData(sessionValues).content;
	
		return changeStatus;
	}

	@GetMapping("/type/{contentType}")
	public Map<String, Object> getResumeFile(
			@PathVariable("contentType") String contentType, 
			@PathVariable("email") String email, 
			HttpServletRequest request){
		
		CcpJsonRepresentation sessionValues = this.getSessionValues(request, email);
		
		this.loginService.validateSession(sessionValues);
		String resumeContent = VisCommonsUtils.getResumeContent(email, contentType);
		
		CcpJsonRepresentation put = CcpConstants.EMPTY_JSON
				.put("content", resumeContent)
				.put("type", contentType);
		
		return put.content;
	}
}
