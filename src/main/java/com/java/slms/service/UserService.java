package com.java.slms.service;

import com.java.slms.dto.PasswordDto;
import com.java.slms.dto.UpdateUserDetails;
import com.java.slms.dto.UserRequest;
import com.java.slms.model.User;

public interface UserService
{
    void changePassword(Long userId, String password);

    void deleteUser(Long userId);

    UserRequest updateUserDetails(Long userId, UpdateUserDetails updateUserDetails);

	User getByPan(String panNumber);

	User getByEmail(String email);

}

