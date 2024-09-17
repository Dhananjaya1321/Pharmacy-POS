package com.novalabsglobal.pharmacy.service.impl;

import com.novalabsglobal.pharmacy.dto.UserDTO;
import com.novalabsglobal.pharmacy.dto.UserResponseDTO;
import com.novalabsglobal.pharmacy.entity.Role;
import com.novalabsglobal.pharmacy.entity.User;
import com.novalabsglobal.pharmacy.enums.UserStatus;
import com.novalabsglobal.pharmacy.repo.UserRepo;
import com.novalabsglobal.pharmacy.service.UserService;
import com.novalabsglobal.pharmacy.service.mapper.UserMapper;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepo userRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public UserDTO saveOrUpdateUser(UserDTO dto) {
        if (dto.getName() == null)
            throw new RuntimeException("The name is mandatory");

        if (dto.getId() == null || dto.getId() == 0) {
            if (dto.getPassword() == null || dto.getUsername() == null)
                throw new RuntimeException("The password and username is mandatory");

            dto.setStatus(UserStatus.ACTIVE);
        } else {
            if (!userRepo.existsById(dto.getId())|| userRepo.getStatus(dto.getId()).equals(UserStatus.DELETED))
                throw new RuntimeException("User is not exists!");

            dto.setStatus(userRepo.getStatus(dto.getId()));
            dto.setUsername(userRepo.getUsername(dto.getId()));
            dto.setPassword(userRepo.getPassword(dto.getId()));
        }
        Role role = new Role(dto.getRole());
        User user = modelMapper.map(dto, User.class);
        user.setRole(role);
        user = userRepo.save(user);

        dto.setId(user.getId());
        return dto;
    }

    @Override
    public boolean deleteUser(Integer id) {
        if (!userRepo.existsById(id) || userRepo.getStatus(id).equals(UserStatus.DELETED))
            throw new RuntimeException("User is not exists!");

        return userRepo.deleteUser(id) > 0;
    }

    @Override
    public List<UserResponseDTO> getAllUserDetails() {
        return new UserMapper().entityToDTO(userRepo.getAllUserDetails());
    }

    @Override
    public UserResponseDTO getAllUserById(Integer id) {
        if (!userRepo.existsById(id) || userRepo.getStatus(id).equals(UserStatus.DELETED))
            throw new RuntimeException("User is not exists!");

        return new UserMapper().entityToDTO(userRepo.getAllUserById(id)).get(0);
    }

    @Override
    public int getUserCount() {
       return userRepo.getUserCount();
    }
}
