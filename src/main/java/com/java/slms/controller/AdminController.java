package com.java.slms.controller;

import com.java.slms.dto.UserRequest;
import com.java.slms.model.Transport;
import com.java.slms.payload.RestResponse;
import com.java.slms.service.AdminService;
import com.java.slms.service.TransportService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/admin")
@PreAuthorize("hasRole('ROLE_ADMIN')")
@Tag(name = "Admin Controller", description = "APIs for managing admins")
public class AdminController
{
    private final AdminService adminService;
    
    @Autowired
	private TransportService transService;
	
	@PostMapping("/transsave")
//	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity saveTrans(@RequestBody Transport ob,
			@RequestAttribute("schoolId") Long schoolId) 
	{
		boolean status = transService.saveTransport(ob,schoolId);
		return ResponseEntity.ok(RestResponse.builder().data(status?"Route Save Done":"Route Save Failed")
				.message("Route Saved successfully")
				.status(HttpStatus.OK.value()).build());
	}
	@PutMapping("/transupdate")
//	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity updateTrans(@RequestBody Transport ob) 
	{
		boolean status = transService.updateTransport(ob);
		return ResponseEntity.ok(RestResponse.builder().data(status?"Route Update Done":"Route Update Failed")
				.status(HttpStatus.OK.value()).build());
	}
	@DeleteMapping("/transdelete")
//	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity deleteTrans(@RequestBody Transport ob) 
	{
		boolean status = transService.deleteTransport(ob);
		return ResponseEntity.ok(RestResponse.builder().data(status?"Route Delete Done":"Route Delete Failed")
				.status(HttpStatus.OK.value()).build());
	}
    

    @Operation(
            summary = "Get Admin Details",
            responses = {
                    @ApiResponse(responseCode = "200", description = "List of all admins retrieved successfully"),
                    @ApiResponse(responseCode = "400", description = "Invalid request", content = @Content)
            }
    )
    @GetMapping
    public ResponseEntity<RestResponse<UserRequest>> getAdminDetails(@RequestAttribute("schoolId") Long schoolId)
    {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();

        UserRequest admin = adminService.getAdminDetails(email, schoolId);

        return ResponseEntity.ok(
                RestResponse.<UserRequest>builder()
                        .data(admin)
                        .message("Admin Details fetched Successfully")
                        .status(HttpStatus.OK.value())
                        .build()
        );
    }
}
