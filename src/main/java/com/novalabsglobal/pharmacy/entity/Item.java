package com.novalabsglobal.pharmacy.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;


    private String createdBy;
    private String updatedBy;

    @LastModifiedDate
    private LocalDateTime lastUpdate;

    @OneToMany(mappedBy = "id.item")
    private List<ItemSuppliers> itemSuppliers;

    @OneToMany(mappedBy = "id.item")
    private List<StockItems> stockItems;

    @OneToMany(mappedBy = "id.item")
    private List<OrderItems> orderItems;

    @ManyToOne
    private Category category;

    @ManyToOne
    private Brand brand;
}
