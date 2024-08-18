package com.novalabsglobal.pharmacy.dto;

import com.novalabsglobal.pharmacy.enums.BrandStatus;
import com.novalabsglobal.pharmacy.enums.CategoryStatus;
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
public class CategoryDTO {
    private Integer id;
    private String name;
    private String description;
    private CategoryStatus status;
    private String createdBy;
    private String updatedBy;
    private LocalDateTime lastUpdate;

    private List<Integer> items;
}
