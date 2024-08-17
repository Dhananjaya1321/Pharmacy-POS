package com.novalabsglobal.pharmacy.entity;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity(name = "order_items")
public class OrderItems {
    @EmbeddedId
    private OrderItemsIds id;
}
