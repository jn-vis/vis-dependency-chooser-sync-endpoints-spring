package com.ccp.vis.controller;

import java.util.Map;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ccp.jn.vis.sync.service.SyncServiceVisResume;

@CrossOrigin
@RestController
@RequestMapping(value = "/resume/{email}")
public class ControllerVisResume {

	private final SyncServiceVisResume service = new SyncServiceVisResume();
	// Esse projeto e essa classe são o ponto de entrada do front-end, sendo POST para criar novo currículo (Resume) 
	// 	sendo PATCH para atualizar, para fazer update. Esse projeto contem zero de regra de negócio sendo enrtão facilmente
	// 	substituível por qualquer outra tecnologia do spring, quando conveniente.
	@RequestMapping(method = {RequestMethod.POST, RequestMethod.PATCH})
	public Map<String, Object> create(@PathVariable("email") String email, @RequestBody Map<String, Object> json) {
		Map<String, Object> save = this.service.save(email, json);
		return save;
	}


}
