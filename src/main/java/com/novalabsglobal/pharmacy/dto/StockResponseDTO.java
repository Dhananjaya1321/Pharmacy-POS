package com.novalabsglobal.pharmacy.dto;

import com.novalabsglobal.pharmacy.enums.StockStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDateTime;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class StockResponseDTO {
    private Integer id;
    private double purchasedAmount;
    private double purchasedQty;
    private double purchased_Discount;
    private double expiryDate;
    private double purchased_return_amount;
    private double purchased_return_qty;
    private StockStatus status;
    private String createdBy;
    private String updatedBy;
    private LocalDateTime lastUpdate;

    private ItemDTO item;
}
