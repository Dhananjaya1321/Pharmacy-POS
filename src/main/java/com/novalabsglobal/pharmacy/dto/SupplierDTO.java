package com.novalabsglobal.pharmacy.dto;

import com.novalabsglobal.pharmacy.enums.StockStatus;
import com.novalabsglobal.pharmacy.enums.SupplierStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.List;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class SupplierDTO {
    private Integer id;
    private String name;
    private String contact;
    private String website;
    private String createdBy;
    private String updatedBy;
    private LocalDateTime lastUpdate;
    private SupplierStatus status;

    private List<Integer> items;
}
