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
import com.jn.commons.utils.JnDeleteKeysFromCache;
import com.jn.sync.mensageria.JnSyncMensageriaSender;
import com.vis.commons.entities.VisEntityResume;
import com.vis.commons.utils.VisAsyncBusiness;
import com.vis.commons.utils.VisCommonsUtils;

@CrossOrigin
@RestController
@RequestMapping(value = "/resume/{email}")
public class ControllerVisResume{
	
	@RequestMapping(method = {RequestMethod.POST, RequestMethod.PATCH}, path = "/language/{language}")
	public Map<String, Object> save(
			@PathVariable("language") String language,
			@PathVariable("email") String email,
			@RequestBody Map<String, Object> sessionValues) {

		Map<String, Object> result = new JnSyncMensageriaSender(VisAsyncBusiness.resume).apply(sessionValues);

		return  result;
	}
	
	@DeleteMapping("/language/{language}")
	public Map<String, Object> delete(@RequestBody Map<String, Object> sessionValues){
		
		Map<String, Object> result = new JnSyncMensageriaSender(VisAsyncBusiness.resumeDelete).apply(sessionValues);

		return  result;
	}

	@DeleteMapping("/status")
	public Map<String, Object> changeStatus(@RequestBody Map<String, Object> sessionValues){
		
		Map<String, Object> result = new JnSyncMensageriaSender(VisAsyncBusiness.resumeStatusChange).apply(sessionValues);

		return  result;
	}

	@GetMapping
	public Map<String, Object> getData(@RequestBody Map<String, Object> sessionValues){
		
		CcpJsonRepresentation json = new CcpJsonRepresentation(sessionValues);
		
		Map<String, Object> changeStatus = VisEntityResume.ENTITY.getData(json, JnDeleteKeysFromCache.INSTANCE).content;
	
		return changeStatus;
	}

	@GetMapping("/viewMode/{viewMode}")
	public Map<String, Object> getFile(
			@PathVariable("viewMode") String viewMode, 
			@RequestBody Map<String, Object> sessionValues){
		
		CcpJsonRepresentation json = new CcpJsonRepresentation(sessionValues).put("viewMode", viewMode);
		
		CcpJsonRepresentation resume = VisCommonsUtils.getResumeFromBucket(json);

		return resume.content;
		
	}

	@GetMapping("/oi")
	public String oi() {
		return "oi";
	}
}
