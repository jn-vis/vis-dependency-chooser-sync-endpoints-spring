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
import com.ccp.jn.sync.mensageria.JnSyncMensageriaSender;
import com.jn.vis.commons.utils.VisAsyncBusiness;
import com.jn.vis.commons.utils.VisCommonsUtils;
import com.vis.commons.entities.VisEntityResume;

@CrossOrigin
@RestController
@RequestMapping(value = "/resume/{email}")
public class ControllerVisResume{
	
	@RequestMapping(method = {RequestMethod.POST, RequestMethod.PATCH})
	public Map<String, Object> save(@RequestBody Map<String, Object> sessionValues) {

		Map<String, Object> result =
				JnSyncMensageriaSender.INSTANCE.sendJsonToTopic(VisAsyncBusiness.resume, sessionValues);

		return  result;
	}

	
	
	@DeleteMapping
	public Map<String, Object> delete(@RequestBody Map<String, Object> sessionValues){
		
		Map<String, Object> result =
				JnSyncMensageriaSender.INSTANCE.sendJsonToTopic(VisAsyncBusiness.resumeDelete, sessionValues);

		return  result;
	}

	@DeleteMapping("/status")
	public Map<String, Object> changeStatus(@RequestBody Map<String, Object> sessionValues){
		
		Map<String, Object> result = JnSyncMensageriaSender.INSTANCE.sendJsonToTopic(VisAsyncBusiness.resumeStatusChange, sessionValues);

		return  result;
	}

	@GetMapping
	public Map<String, Object> getData(@RequestBody Map<String, Object> sessionValues){
		
		CcpJsonRepresentation json = new CcpJsonRepresentation(sessionValues);
		
		Map<String, Object> changeStatus = VisEntityResume.INSTANCE.getData(json).content;
	
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

}
