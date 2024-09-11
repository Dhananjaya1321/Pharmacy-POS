package com.novalabsglobal.pharmacy.service.mapper;

import com.novalabsglobal.pharmacy.dto.CustomerResponseDTO;
import com.novalabsglobal.pharmacy.dto.RoleDTO;
import com.novalabsglobal.pharmacy.dto.UserResponseDTO;
import com.novalabsglobal.pharmacy.enums.CustomerStatus;
import com.novalabsglobal.pharmacy.enums.UserStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class CustomerMapper {
    public Page<CustomerResponseDTO> entityToDTO(Page<Object> customers) {
        List<CustomerResponseDTO> dtos = new ArrayList<>();
        for (Object o : customers) {
            Object[] arr = (Object[]) o;

            CustomerResponseDTO customerResponseDTO = new CustomerResponseDTO();
            customerResponseDTO.setId((Integer) arr[0]);
            customerResponseDTO.setName(String.valueOf(arr[1]));
            customerResponseDTO.setContact(String.valueOf(arr[2]));
            customerResponseDTO.setAddress(String.valueOf(arr[3]));
            customerResponseDTO.setEmail(String.valueOf(arr[4]));
            customerResponseDTO.setNic(String.valueOf(arr[5]));
            customerResponseDTO.setStatus(CustomerStatus.valueOf(String.valueOf(arr[6])));
            customerResponseDTO.setCreatedBy(String.valueOf(arr[7]));
            customerResponseDTO.setUpdatedBy(String.valueOf(arr[8]));
            customerResponseDTO.setLastUpdate((arr[9] instanceof LocalDateTime) ? (LocalDateTime) arr[9] : ((Timestamp) arr[9]).toLocalDateTime());

            dtos.add(customerResponseDTO);
        }
        return new PageImpl<>(dtos, customers.getPageable(), customers.getTotalElements());
    }
}

