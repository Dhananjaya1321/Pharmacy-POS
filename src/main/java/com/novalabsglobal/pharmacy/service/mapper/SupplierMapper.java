package com.novalabsglobal.pharmacy.service.mapper;

import com.novalabsglobal.pharmacy.dto.BrandResponseDTO;
import com.novalabsglobal.pharmacy.dto.SupplierResponseDTO;
import com.novalabsglobal.pharmacy.entity.Brand;
import com.novalabsglobal.pharmacy.entity.Supplier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import java.util.ArrayList;
import java.util.List;

public class SupplierMapper {
    public Page<SupplierResponseDTO> entityToDTO(Page<Supplier> suppliers) {
        List<SupplierResponseDTO> dtos = new ArrayList<>();
        for (Supplier s : suppliers) {
            SupplierResponseDTO supplierResponseDTO = new SupplierResponseDTO();

            supplierResponseDTO.setId(s.getId());
            supplierResponseDTO.setName(s.getName());
            supplierResponseDTO.setContact(s.getContact());
            supplierResponseDTO.setWebsite(s.getWebsite());
            supplierResponseDTO.setNic(s.getNic());
            supplierResponseDTO.setEmail(s.getEmail());
            supplierResponseDTO.setDescription(s.getDescription());

            dtos.add(supplierResponseDTO);
        }
        return new PageImpl<>(dtos, suppliers.getPageable(), suppliers.getTotalElements());

    }
}

