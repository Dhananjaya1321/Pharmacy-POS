package com.novalabsglobal.pharmacy.entity;

import jakarta.persistence.Embeddable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;

@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ItemSuppliersIds implements Serializable {
    @ManyToOne
    @JoinColumn(name = "iid")
    private Item item;

    @ManyToOne
    @JoinColumn(name = "sid")
    private Supplier supplier;
}
