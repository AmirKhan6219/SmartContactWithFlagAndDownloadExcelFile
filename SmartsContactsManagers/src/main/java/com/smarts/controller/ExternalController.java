package com.smarts.controller;

import com.smarts.dto.ContactDto;
import com.smarts.service.ExternalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.smarts.service.ContactServiceImpl;

@RestController
public class ExternalController {

	@Autowired
	RestTemplate restTemplate;

	@Autowired
	private ContactServiceImpl contactService;

	@Autowired
	private ExternalService externalService;

	@RequestMapping("/fetchall/{isRemote}")
	public ResponseEntity<String> fetchAll(@PathVariable("isRemote") String isRemote) {

		return externalService.fetchAll(isRemote);
	}

	@RequestMapping("/postData/{isRemote}")
	public ResponseEntity<String> postData(@PathVariable("isRemote") String isRemote, @RequestBody ContactDto contactDto) {
		return externalService.postData(isRemote, contactDto);

	}
}


