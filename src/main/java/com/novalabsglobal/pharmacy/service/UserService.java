package com.novalabsglobal.pharmacy.service;

import com.novalabsglobal.pharmacy.dto.UserDTO;
import com.novalabsglobal.pharmacy.dto.UserResponseDTO;

import java.util.List;

public interface UserService {
    UserDTO saveOrUpdateUser(UserDTO dto);

    boolean deleteUser(Integer id);

    List<UserResponseDTO> getAllUserDetails();

    UserResponseDTO getAllUserById(Integer id);

    int getUserCount();

    UserResponseDTO checkLogin(String emailOrUsername, String password);

    String checkEmailAndSendOTP(String emailOrUsername);

    boolean updatePassword(String email, String newPassword);
}
