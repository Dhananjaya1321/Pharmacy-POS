package com.novalabsglobal.pharmacy.entity;

import com.novalabsglobal.pharmacy.enums.BrandStatus;
import com.novalabsglobal.pharmacy.enums.RoleStatus;
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
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String description;

    @Enumerated(EnumType.STRING)
    private RoleStatus status;

    private String createdBy;
    private String updatedBy;

    @LastModifiedDate
    private LocalDateTime lastUpdate;

    @OneToMany(mappedBy = "role", cascade = {CascadeType.PERSIST})
    private List<User> users;

    public Role(Integer id) {
        this.id = id;
    }
}
