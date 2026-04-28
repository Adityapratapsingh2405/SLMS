package com.java.slms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.java.slms.model.Transport;
import com.java.slms.payload.RestResponse;
import com.java.slms.service.TransportService;

import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/trans")

public class TransportController 
{
	@Autowired
	private TransportService transService;
	
	@PostMapping("/save")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity saveTrans(@RequestBody Transport ob,
			@RequestAttribute("schoolId") Long schoolId) 
	{
		boolean status = transService.saveTransport(ob,schoolId);
		return ResponseEntity.ok(RestResponse.builder().data(status?"Route Save Done":"Route Save Failed")
				.message("Route Saved successfully")
				.status(HttpStatus.OK.value()).build());
	}
	@PutMapping("/update")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity updateTrans(@RequestBody Transport ob) 
	{
		boolean status = transService.updateTransport(ob);
		return ResponseEntity.ok(RestResponse.builder().data(status?"Route Update Done":"Route Update Failed")
				.status(HttpStatus.OK.value()).build());
	}
	@DeleteMapping("/delete")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity deleteTrans(@RequestBody Transport ob) 
	{
		boolean status = transService.deleteTransport(ob);
		return ResponseEntity.ok(RestResponse.builder().data(status?"Route Delete Done":"Route Delete Failed")
				.status(HttpStatus.OK.value()).build());
	}
	@GetMapping("/list")
	@PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_STUDENT')")
	public ResponseEntity listTrans(@RequestAttribute("schoolId") Long schoolId) 
	{
		return ResponseEntity.ok(RestResponse.builder().data(transService.listAll(schoolId))
				.status(HttpStatus.OK.value()).build());
	}
}
