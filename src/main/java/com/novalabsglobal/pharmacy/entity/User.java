package com.novalabsglobal.pharmacy.entity;

import com.novalabsglobal.pharmacy.enums.UnitStatus;
import com.novalabsglobal.pharmacy.enums.UserStatus;
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
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String address;
    private String contact;
    private String nic;

    @Column(unique = true)
    private String email;

    @Column(unique = true)
    private String username;
    private String password;

    @Enumerated(EnumType.STRING)
    private UserStatus status;

    private String createdBy;
    private String updatedBy;

    @LastModifiedDate
    private LocalDateTime lastUpdate;

    @ManyToOne
    private Role role;

    @OneToMany
    private List<Orders> orders;
}
