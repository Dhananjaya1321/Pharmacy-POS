package com.novalabsglobal.pharmacy.dto;

import com.novalabsglobal.pharmacy.enums.ShopStatus;
import com.novalabsglobal.pharmacy.enums.StockStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.Date;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class StockDTO {
    private Integer id;
    private double purchasedAmount;
    private double purchasedQty;
    private double purchasedDiscount;
    private Date expiryDate;
    private double purchased_return_amount;
    private double purchased_return_qty;
    private String description;
    private StockStatus status;
    private String createdBy;
    private String updatedBy;
    private LocalDateTime lastUpdate;

    private Integer item;
}
