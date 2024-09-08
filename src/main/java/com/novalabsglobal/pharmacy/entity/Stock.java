package com.novalabsglobal.pharmacy.entity;

import com.novalabsglobal.pharmacy.enums.BrandStatus;
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
import java.util.List;

@Entity
@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class Stock {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "purchased_amount")
    private double purchasedAmount;

    @Column(name = "purchased_qty")
    private double purchasedQty;

    @Column(name = "purchased_discount")
    private double purchasedDiscount;

    @Column(name = "expiry_date")
    private Date expiryDate;

    private double purchased_return_amount;
    private double purchased_return_qty;
    private String description;

    @Enumerated(EnumType.STRING)
    private StockStatus status;


    private String createdBy;
    private String updatedBy;

    @LastModifiedDate
    private LocalDateTime lastUpdate;

    @ManyToOne
    private Item item;
}
