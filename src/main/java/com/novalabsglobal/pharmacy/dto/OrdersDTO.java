package com.novalabsglobal.pharmacy.dto;

import com.novalabsglobal.pharmacy.enums.ItemStatus;
import com.novalabsglobal.pharmacy.enums.OrderStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class OrdersDTO {
    private Integer id;
    private Date orderDate;
    private double discount;
    private double total;
    private double subTotal;
    private OrderStatus status;
    private String createdBy;
    private String updatedBy;
    private LocalDateTime lastUpdate;

    private List<ItemDTO> itemDTOS;
    private UserDTO user;
    private CustomerDTO customer;
}
