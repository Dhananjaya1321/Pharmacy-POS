package com.novalabsglobal.pharmacy.service.mapper;

import com.novalabsglobal.pharmacy.dto.RoleDTO;
import com.novalabsglobal.pharmacy.dto.UserResponseDTO;
import com.novalabsglobal.pharmacy.enums.UserStatus;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class UserMapper {
    public List<UserResponseDTO> entityToDTO(List<Object> users) {
        List<UserResponseDTO> dtos = new ArrayList<>();
        for (Object o : users) {
            Object[] arr = (Object[]) o;

            UserResponseDTO userDTO = new UserResponseDTO();
            userDTO.setId((Integer) arr[0]);
            userDTO.setName(String.valueOf(arr[1]));
            userDTO.setAddress(String.valueOf(arr[2]));
            userDTO.setContact(String.valueOf(arr[3]));
            userDTO.setUsername(String.valueOf(arr[4]));
            userDTO.setStatus(UserStatus.valueOf(String.valueOf(arr[5])));
            userDTO.setRole(
                    new RoleDTO(
                            (Integer) arr[6],
                            (String) arr[7],
                            (String) arr[8]
                    )
            );
            userDTO.setCreatedBy(String.valueOf(arr[9]));
            userDTO.setUpdatedBy(String.valueOf(arr[10]));
            userDTO.setLastUpdate((arr[11] instanceof LocalDateTime) ? (LocalDateTime) arr[11] : ((Timestamp) arr[11]).toLocalDateTime());

            dtos.add(userDTO);
        }
        return dtos;
    }
}

