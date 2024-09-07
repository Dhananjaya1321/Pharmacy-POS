package com.novalabsglobal.pharmacy.service.mapper;

import com.novalabsglobal.pharmacy.dto.UnitResponseDTO;
import com.novalabsglobal.pharmacy.entity.Unit;

import java.util.ArrayList;
import java.util.List;

public class UnitMapper {
    public List<UnitResponseDTO> entityToDTO(List<Unit> units) {
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
        return dtos;
    }
}

