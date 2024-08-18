package com.novalabsglobal.pharmacy.dto;

import com.novalabsglobal.pharmacy.enums.OrderStatus;
import com.novalabsglobal.pharmacy.enums.RoleStatus;
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
public class RoleDTO {
    private Integer id;
    private String name;
    private String description;
    private RoleStatus status;
    private String createdBy;
    private String updatedBy;
    private LocalDateTime lastUpdate;

    private List<Integer> users;
}
