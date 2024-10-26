package com.novalabsglobal.pharmacy.entity;

import com.novalabsglobal.pharmacy.enums.BrandStatus;
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

@Entity
@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class Orders {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String reference;

    @Column(name = "order_date")
    private Date orderDate;

    private double discount;

    private double total;

    @Column(name = "sub_total")
    private double subTotal;

    @Enumerated(EnumType.STRING)
    private OrderStatus status;


    private String createdBy;
    private String updatedBy;

    @LastModifiedDate
    private LocalDateTime lastUpdate;

    @OneToMany(mappedBy = "id.orders")
    private List<OrderItems> orderItems;

    @ManyToOne
    private User user;

    @ManyToOne
    private Customer customer;
}
