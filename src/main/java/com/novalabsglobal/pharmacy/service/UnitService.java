package com.novalabsglobal.pharmacy.service;

import com.novalabsglobal.pharmacy.dto.CategoryDTO;
import com.novalabsglobal.pharmacy.dto.CategoryResponseDTO;
import com.novalabsglobal.pharmacy.dto.UnitDTO;
import com.novalabsglobal.pharmacy.dto.UnitResponseDTO;

import java.util.List;

public interface UnitService {
    UnitDTO saveOrUpdateUnit(UnitDTO dto);

    boolean deleteUnit(Integer id);

    List<UnitResponseDTO> getAllUnits();
}
