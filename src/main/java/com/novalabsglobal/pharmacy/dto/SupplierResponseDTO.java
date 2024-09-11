package com.novalabsglobal.pharmacy.dto;

import com.novalabsglobal.pharmacy.enums.SupplierStatus;
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
public class SupplierResponseDTO {
    private Integer id;
    private String name;
    private String contact;
    private String website;
    private String nic;
    private String email;
    private String description;
    private String createdBy;
    private String updatedBy;
    private LocalDateTime lastUpdate;
    private SupplierStatus status;

    private List<ItemDTO> items;
}
