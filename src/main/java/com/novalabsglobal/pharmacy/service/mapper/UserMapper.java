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

            userDTO.setNic(String.valueOf(arr[4]));
            userDTO.setEmail(String.valueOf(arr[5]));

            userDTO.setUsername(String.valueOf(arr[6]));
            userDTO.setStatus(UserStatus.valueOf(String.valueOf(arr[7])));
            userDTO.setRole(
                    new RoleDTO(
                            (Integer) arr[8],
                            (String) arr[9],
                            (String) arr[10]
                    )
            );
            System.out.println((Integer) arr[8]+" "+
                    (String) arr[9]+" "+
                    (String) arr[10]);
            userDTO.setCreatedBy(String.valueOf(arr[11]));
            userDTO.setUpdatedBy(String.valueOf(arr[12]));
            userDTO.setLastUpdate((arr[13] instanceof LocalDateTime) ? (LocalDateTime) arr[13] : ((Timestamp) arr[13]).toLocalDateTime());

            dtos.add(userDTO);
        }
        return dtos;
    }
}

