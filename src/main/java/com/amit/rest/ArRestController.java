package com.amit.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.amit.binding.App;
import com.amit.services.ArService;

@RestController
public class ArRestController {

	@Autowired
	private ArService arService;

	@PostMapping("/app")
	public ResponseEntity<String> createApp(App app) {
		String status = arService.createApplication(app);
		return new ResponseEntity<>(status, HttpStatus.CREATED);
	}

	@GetMapping("/users/{userId}")
	public List<App> getApps(@PathVariable Integer userId) {
		return arService.fetchApps(userId);

	}

}
