package com.novalabsglobal.pharmacy.dto;

import com.novalabsglobal.pharmacy.enums.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class OrdersResponseDTO {
    private Integer id;
    private Date orderDate;
    private double discount;
    private double total;
    private double subTotal;
    private OrderStatus status;
    private String createdBy;
    private String updatedBy;
    private LocalDateTime lastUpdate;

    private List<Integer> itemDTOS;
    private Integer user;
    private Integer customer;
}
