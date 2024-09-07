package com.novalabsglobal.pharmacy.service.impl;

import com.novalabsglobal.pharmacy.dto.CategoryDTO;
import com.novalabsglobal.pharmacy.dto.CategoryResponseDTO;
import com.novalabsglobal.pharmacy.dto.UnitDTO;
import com.novalabsglobal.pharmacy.dto.UnitResponseDTO;
import com.novalabsglobal.pharmacy.entity.Category;
import com.novalabsglobal.pharmacy.entity.Unit;
import com.novalabsglobal.pharmacy.enums.CategoryStatus;
import com.novalabsglobal.pharmacy.enums.UnitStatus;
import com.novalabsglobal.pharmacy.repo.UnitRepo;
import com.novalabsglobal.pharmacy.service.UnitService;
import com.novalabsglobal.pharmacy.service.mapper.CategoryMapper;
import com.novalabsglobal.pharmacy.service.mapper.UnitMapper;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class UnitServiceImpl implements UnitService {
    @Autowired
    private UnitRepo unitRepo;

    @Autowired
    private ModelMapper modelMapper;


    @Override
    public UnitDTO saveOrUpdateUnit(UnitDTO dto) {
        if (dto.getUnitName() == null || dto.getUnitSymbology()==null)
            throw new RuntimeException("The unit name and symbology is mandatory");

        if (dto.getId() == null || dto.getId() == 0) {
            dto.setStatus(UnitStatus.ACTIVE);
        } else {
            if (!unitRepo.existsById(dto.getId()) || unitRepo.getStatus(dto.getId()).equals(UnitStatus.DELETED))
                throw new RuntimeException("Unit is not exists!");

            dto.setStatus(unitRepo.getStatus(dto.getId()));
        }
        Unit unit = unitRepo.save(modelMapper.map(dto, Unit.class));

        dto.setId(unit.getId());
        return dto;
    }

    @Override
    public boolean deleteUnit(Integer id) {
        if (!unitRepo.existsById(id) || unitRepo.getStatus(id).equals(UnitStatus.DELETED))
            throw new RuntimeException("Unit is not exists!");

        return unitRepo.deleteUnit(id) > 0;
    }


    @Override
    public List<UnitResponseDTO> getAllUnits() {
      return new UnitMapper().entityToDTO(unitRepo.getAllUnits());
    }
}
