
package com.novalabsglobal.pharmacy.dto;

import com.novalabsglobal.pharmacy.enums.UnitStatus;
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
public class UnitDTO {
    private Integer id;
    private String unitName;
    private String unitSymbology;
    private String description;
    private UnitStatus status;

    private List<Integer> items;
}
