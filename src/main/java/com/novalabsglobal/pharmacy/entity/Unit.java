
package com.novalabsglobal.pharmacy.entity;

import com.novalabsglobal.pharmacy.enums.UnitStatus;
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
public class Unit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String unitName;

    private String unitSymbology;

    @Enumerated(EnumType.STRING)
    private UnitStatus status;

    private String createdBy;
    private String updatedBy;

    @LastModifiedDate
    private LocalDateTime lastUpdate;

    @OneToMany(mappedBy = "unit")
    private List<Item> items;
}
