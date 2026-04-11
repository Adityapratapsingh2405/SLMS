package com.java.slms.controller;

import com.java.slms.dto.PasswordDto;
import com.java.slms.dto.UpdateUserDetails;
import com.java.slms.dto.UserRequest;
import com.java.slms.model.User;
import com.java.slms.payload.RestResponse;
import com.java.slms.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
@Tag(name = "User Controller", description = "APIs for managing users including password changes, updates and deletions")
public class UserController
{

    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    @Operation(
            summary = "Change user's password",
            description = "Change password for the user",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Password updated successfully"),
                    @ApiResponse(responseCode = "400", description = "Invalid request", content = @Content)
            }
    )
    @PutMapping("/change-password")
    public ResponseEntity<RestResponse<Void>> changePassword(@RequestBody PasswordDto req
    )
    {
    	User user = null;
    	String type = req.getUserType();
    	
    	if(type.equals("student"))
    	   user = userService.getByPan(req.getPanNumber());
    	else
    		user = userService.getByEmail(req.getEmail());
    	
    	
    	if(user!=null)
    	{
    		if(passwordEncoder.matches(req.getOldpassword(), user.getPassword()))
    		{
    		userService.changePassword(user.getId(), req.getPassword());
    		 return ResponseEntity.ok(
    	                RestResponse.<Void>builder()
    	                        .message("Password updated successfully")
    	                        .status(HttpStatus.OK.value())
    	                        .build()
    	        );
    		}else {
    			 return ResponseEntity.ok(
     	                RestResponse.<Void>builder()
     	                        .message("Old Password Not Match !")
     	                        .status(HttpStatus.BAD_REQUEST.value())
     	                        .build()
     	        );
    		}
    	}else {
    		 return ResponseEntity.ok(
    	                RestResponse.<Void>builder()
    	                        .message("User Not Found !")
    	                        .status(HttpStatus.BAD_REQUEST.value())
    	                        .build()
    	        );
    	}      
    }

    @Operation(
            summary = "Update user details",
            description = "Update details for the user identified by userId.",
            parameters = {@Parameter(name = "userId", description = "ID of the user", required = true)},
            responses = {
                    @ApiResponse(responseCode = "200", description = "User details updated successfully",
                            content = @Content(schema = @Schema(implementation = UserRequest.class))),
                    @ApiResponse(responseCode = "400", description = "Invalid request", content = @Content)
            }
    )
    @PutMapping("/{userId}/update")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<RestResponse<UserRequest>> updateUserDetails(
            @PathVariable Long userId,
            @RequestBody UpdateUserDetails updateUserDetails
    )
    {
        UserRequest updatedUser = userService.updateUserDetails(userId, updateUserDetails);
        return ResponseEntity.ok(
                RestResponse.<UserRequest>builder()
                        .data(updatedUser)
                        .message("User details updated successfully")
                        .status(HttpStatus.OK.value())
                        .build()
        );
    }

    @Operation(
            summary = "Delete user",
            description = "Delete the user identified by userId.",
            parameters = {@Parameter(name = "userId", description = "ID of the user", required = true)},
            responses = {
                    @ApiResponse(responseCode = "200", description = "User deleted successfully"),
                    @ApiResponse(responseCode = "400", description = "Invalid request", content = @Content)
            }
    )
    @DeleteMapping("/{userId}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<RestResponse<Void>> deleteUser(@PathVariable Long userId)
    {
        userService.deleteUser(userId);
        return ResponseEntity.ok(
                RestResponse.<Void>builder()
                        .message("User deleted successfully")
                        .status(HttpStatus.OK.value())
                        .build()
        );
    }
}
