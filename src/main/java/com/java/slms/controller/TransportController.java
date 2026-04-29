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
	
	@GetMapping("/list")
	@PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_STUDENT')")
	public ResponseEntity listTrans(@RequestAttribute("schoolId") Long schoolId) 
	{
		return ResponseEntity.ok(RestResponse.builder().data(transService.listAll(schoolId))
				.status(HttpStatus.OK.value()).build());
	}
}
