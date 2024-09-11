package com.novalabsglobal.pharmacy.dto;

import com.novalabsglobal.pharmacy.enums.ItemStatus;
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
public class ItemResponseDTO {
    private Integer id;
    private String name;
    private String description;
    private double reOrderLevel;
    private ItemStatus status;
    private String createdBy;
    private String updatedBy;
    private LocalDateTime lastUpdate;

    private List<SupplierDTO> suppliers;
    private List<StockDTO> stocks;
    private CategoryDTO category;
    private BrandDTO brand;
    private UnitDTO unit;
}
