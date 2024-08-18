
package com.novalabsglobal.pharmacy.dto;

import com.novalabsglobal.pharmacy.enums.UnitStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;


@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UnitResponseDTO {
    private Integer id;
    private String unitName;
    private String unitSymbology;
    private UnitStatus status;

    private List<ItemDTO> items;
}
