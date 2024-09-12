package com.novalabsglobal.pharmacy.service;

import com.novalabsglobal.pharmacy.dto.UnitDTO;
import com.novalabsglobal.pharmacy.dto.UnitResponseDTO;
import org.springframework.data.domain.Page;

public interface UnitService {
    UnitDTO saveOrUpdateUnit(UnitDTO dto);

    boolean deleteUnit(Integer id);

    Page<UnitResponseDTO> getAllUnits(Integer page, Integer size);
}
