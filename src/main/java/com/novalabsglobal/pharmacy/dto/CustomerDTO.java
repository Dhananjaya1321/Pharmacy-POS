package com.novalabsglobal.pharmacy.dto;

import com.novalabsglobal.pharmacy.enums.CustomerStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.List;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class CustomerDTO {
    private Integer id;
    private String name;
    private String contact;
    private String address;
    private String email;
    private String nic;
    private CustomerStatus status;
    private String createdBy;
    private String updatedBy;
    private LocalDateTime lastUpdate;

    private List<Integer> orders;
}
