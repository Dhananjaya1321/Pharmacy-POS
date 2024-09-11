package com.novalabsglobal.pharmacy.service;

import com.novalabsglobal.pharmacy.dto.SupplierDTO;
import com.novalabsglobal.pharmacy.dto.SupplierResponseDTO;

import java.util.List;

public interface SupplierService {
    SupplierDTO saveOrUpdateSupplier(SupplierDTO dto);

    boolean deleteSupplier(Integer id);

    List<SupplierResponseDTO> getAllSuppliers();
}
