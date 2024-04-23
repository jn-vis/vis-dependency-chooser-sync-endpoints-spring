package com.ccp.vis.controller;

import java.util.Map;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ccp.jn.vis.sync.service.SyncServiceVisPosition;
import org.springframework.web.bind.annotation.RequestMethod;
@CrossOrigin
@RestController
@RequestMapping(value = "recruiter/{email}/positions")
public class ControllerVisPosition {

	private final SyncServiceVisPosition service = new SyncServiceVisPosition();
	
	@RequestMapping(method = {RequestMethod.POST, RequestMethod.PATCH})
	public Map<String, Object> create(@PathVariable("email") String email, @RequestBody Map<String, Object> json){
		Map<String, Object> save = this.service.save(email, json);
		return save;
	}
}
