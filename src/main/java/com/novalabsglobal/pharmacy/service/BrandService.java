package com.novalabsglobal.pharmacy.service;

import com.novalabsglobal.pharmacy.dto.BrandDTO;
import com.novalabsglobal.pharmacy.dto.BrandResponseDTO;
import com.novalabsglobal.pharmacy.dto.CategoryDTO;
import com.novalabsglobal.pharmacy.dto.CategoryResponseDTO;

import java.util.List;

public interface BrandService {
    BrandDTO saveOrUpdateBrand(BrandDTO dto);

    boolean deleteBrand(Integer id);

    List<BrandResponseDTO> getAllBrands();
}
