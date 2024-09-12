package com.novalabsglobal.pharmacy.service.mapper;

import com.novalabsglobal.pharmacy.dto.UnitResponseDTO;
import com.novalabsglobal.pharmacy.entity.Unit;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import java.util.ArrayList;
import java.util.List;

public class UnitMapper {
    public Page<UnitResponseDTO> entityToDTO(Page<Unit> units) {
        List<UnitResponseDTO> dtos = new ArrayList<>();
        for (Unit u : units) {
            UnitResponseDTO unitResponseDTO = new UnitResponseDTO();
            unitResponseDTO.setId(u.getId());
            unitResponseDTO.setUnitName(u.getUnitName());
            unitResponseDTO.setUnitSymbology(u.getUnitSymbology());
            unitResponseDTO.setDescription(u.getDescription());
//            categoryResponseDTO.setStatus(c.getStatus());
//            categoryResponseDTO.setCreatedBy(c.getCreatedBy());
//            categoryResponseDTO.setUpdatedBy(c.getUpdatedBy());
//            categoryResponseDTO.setLastUpdate(c.getLastUpdate());

            dtos.add(unitResponseDTO);
        }
        return new PageImpl<>(dtos, units.getPageable(), units.getTotalElements());
    }
}

