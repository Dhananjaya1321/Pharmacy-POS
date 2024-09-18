package com.novalabsglobal.pharmacy.service.impl;

import com.novalabsglobal.pharmacy.dto.SupplierDTO;
import com.novalabsglobal.pharmacy.dto.SupplierResponseDTO;
import com.novalabsglobal.pharmacy.entity.Supplier;
import com.novalabsglobal.pharmacy.enums.SupplierStatus;
import com.novalabsglobal.pharmacy.repo.SupplierRepo;
import com.novalabsglobal.pharmacy.service.SupplierService;
import com.novalabsglobal.pharmacy.service.mapper.SupplierMapper;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class SupplierServiceImpl implements SupplierService {
    @Autowired
    private SupplierRepo supplierRepo;

    @Autowired
    private ModelMapper modelMapper;


    @Override
    public SupplierDTO saveOrUpdateSupplier(SupplierDTO dto) {
        if (dto.getName() == null)
            throw new RuntimeException("The supplier name is mandatory");

        if (dto.getId() == null || dto.getId() == 0) {
            dto.setStatus(SupplierStatus.ACTIVE);
        } else {
            if (!supplierRepo.existsById(dto.getId()) || supplierRepo.getStatus(dto.getId()).equals(SupplierStatus.DELETED))
                throw new RuntimeException("Supplier is not exists!");

            dto.setStatus(supplierRepo.getStatus(dto.getId()));
        }
        Supplier supplier = supplierRepo.save(modelMapper.map(dto, Supplier.class));

        dto.setId(supplier.getId());
        return dto;
    }

    @Override
    public boolean deleteSupplier(Integer id) {
        if (!supplierRepo.existsById(id) || supplierRepo.getStatus(id).equals(SupplierStatus.DELETED))
            throw new RuntimeException("Supplier is not exists!");

        return supplierRepo.deleteSupplier(id) > 0;
    }


    @Override
    public Page<SupplierResponseDTO> getAllSuppliers(Integer page, Integer size) {
        PageRequest pageRequest = (page == null && size == null) ? null : PageRequest.of(page, size);
        return new SupplierMapper().entityToDTO(supplierRepo.getAllSuppliers(pageRequest));
    }

    @Override
    public int getSuppliersCount() {
        return supplierRepo.getSuppliersCount();
    }
}
