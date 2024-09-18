package com.novalabsglobal.pharmacy.service;

import com.novalabsglobal.pharmacy.dto.SupplierDTO;
import com.novalabsglobal.pharmacy.dto.SupplierResponseDTO;
import org.springframework.data.domain.Page;

public interface SupplierService {
    SupplierDTO saveOrUpdateSupplier(SupplierDTO dto);

    boolean deleteSupplier(Integer id);

    Page<SupplierResponseDTO> getAllSuppliers(Integer page, Integer size);

    int getSuppliersCount();
}
