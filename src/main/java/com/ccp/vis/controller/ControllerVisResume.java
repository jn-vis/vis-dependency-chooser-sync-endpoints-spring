package com.ccp.vis.controller;

import java.util.Map;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ccp.jn.vis.sync.service.SyncServiceVisResume;

@CrossOrigin
@RestController
@RequestMapping(value = "/resume/{email}")
public class ControllerVisResume {

	private final SyncServiceVisResume service = new SyncServiceVisResume();
	
	@PostMapping
	public Map<String, Object> create(@PathVariable("email") String email, @RequestBody Map<String, Object> json) {
		Map<String, Object> save = this.service.save(email, json);
		return save;
	}

	@PatchMapping
	public Map<String, Object> update(@PathVariable("email") String email, @RequestBody Map<String, Object> json) {
		Map<String, Object> save = this.service.save(email, json);
		return save;
	}


}
