package com.novalabsglobal.pharmacy.service.impl;

import com.novalabsglobal.pharmacy.dto.UnitDTO;
import com.novalabsglobal.pharmacy.dto.UnitResponseDTO;
import com.novalabsglobal.pharmacy.entity.Unit;
import com.novalabsglobal.pharmacy.enums.UnitStatus;
import com.novalabsglobal.pharmacy.repo.ItemRepo;
import com.novalabsglobal.pharmacy.repo.UnitRepo;
import com.novalabsglobal.pharmacy.service.UnitService;
import com.novalabsglobal.pharmacy.service.mapper.UnitMapper;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UnitServiceImpl implements UnitService {
    @Autowired
    private UnitRepo unitRepo;

    @Autowired
    private ItemRepo itemRepo;

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

        if (itemRepo.getCountItemsUnderUnitByUnitId(id) > 0)
            throw new RuntimeException("This unit cannot be deleted. Some items are under unit");

        return unitRepo.deleteUnit(id) > 0;
    }


    @Override
    public Page<UnitResponseDTO> getAllUnits(Integer page, Integer size) {
        PageRequest pageRequest = (page == null && size == null) ? null : PageRequest.of(page, size);
        return new UnitMapper().entityToDTO(unitRepo.getAllUnits(pageRequest));
    }
}
