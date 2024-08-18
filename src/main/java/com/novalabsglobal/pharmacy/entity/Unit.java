
package com.novalabsglobal.pharmacy.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;


@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table
public class Unit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "unit_id")
    private long unitId;

    private String unitName;

    private String unitSymbology;

    @OneToMany(mappedBy = "unit")
    private List<Item> items;
}
