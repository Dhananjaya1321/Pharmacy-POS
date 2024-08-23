package com.novalabsglobal.pharmacy.dto;

import com.novalabsglobal.pharmacy.enums.UserStatus;
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
public class UserDTO {
    private Integer id;
    private String name;
    private String address;
    private String contact;
    private String username;
    private String password;
    private String createdBy;
    private String updatedBy;
    private LocalDateTime lastUpdate;
    private UserStatus status;

    private Integer role;
    private List<Integer> orders;
}