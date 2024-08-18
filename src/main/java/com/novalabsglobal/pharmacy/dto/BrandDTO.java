package com.novalabsglobal.pharmacy.dto;

import com.novalabsglobal.pharmacy.enums.BrandStatus;
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
public class BrandDTO {
    private Integer id;
    private String name;
    private String address;
    private String contact;
    private String website;
    private String description;
    private BrandStatus status;

    private String createdBy;
    private String updatedBy;
    private LocalDateTime lastUpdate;

    private List<Integer> items;
}
