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
@Entity(name = "item_suppliers")
public class ItemSuppliers {
    @EmbeddedId
    private ItemSuppliersIds id;
}
