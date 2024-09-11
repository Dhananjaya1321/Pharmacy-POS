package com.novalabsglobal.pharmacy.dto;

import com.novalabsglobal.pharmacy.entity.ItemSuppliers;
import com.novalabsglobal.pharmacy.entity.OrderItems;
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
public class ItemDTO {
    private Integer id;
    private String name;
    private String description;
    private double reOrderLevel;
    private ItemStatus status;
    private String createdBy;
    private String updatedBy;
    private LocalDateTime lastUpdate;

    private List<Integer> suppliers;
    private List<Integer> stocks;
    private Integer category;
    private Integer brand;
    private Integer unit;
}
