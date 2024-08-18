package com.novalabsglobal.pharmacy.dto;

import com.novalabsglobal.pharmacy.enums.ShopStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDateTime;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class ShopDTO {
    private Integer id;
    private String name;
    private String address;
    private String contact;
    private String website;
    private ShopStatus status;
    private String createdBy;
    private String updatedBy;
    private LocalDateTime lastUpdate;
}
