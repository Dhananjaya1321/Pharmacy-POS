package com.novalabsglobal.pharmacy.service;

import com.novalabsglobal.pharmacy.dto.BrandDTO;
import com.novalabsglobal.pharmacy.dto.BrandResponseDTO;
import org.springframework.data.domain.Page;

public interface BrandService {
    BrandDTO saveOrUpdateBrand(BrandDTO dto);

    boolean deleteBrand(Integer id);

    Page<BrandResponseDTO> getAllBrands(Integer page, Integer size);
}
