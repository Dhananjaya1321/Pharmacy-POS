package com.novalabsglobal.pharmacy.service.mapper;

import com.novalabsglobal.pharmacy.dto.BrandResponseDTO;
import com.novalabsglobal.pharmacy.dto.CategoryResponseDTO;
import com.novalabsglobal.pharmacy.entity.Brand;
import com.novalabsglobal.pharmacy.entity.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import java.util.ArrayList;
import java.util.List;

public class BrandMapper {
    public Page<BrandResponseDTO> entityToDTO(Page<Brand> brands) {
        List<BrandResponseDTO> dtos = new ArrayList<>();
        for (Brand b : brands) {
            BrandResponseDTO brandResponseDTO = new BrandResponseDTO();
            brandResponseDTO.setId(b.getId());
            brandResponseDTO.setName(b.getName());
            brandResponseDTO.setContact(b.getContact());
            brandResponseDTO.setWebsite(b.getWebsite());
            brandResponseDTO.setAddress(b.getAddress());
            brandResponseDTO.setDescription(b.getDescription());

            dtos.add(brandResponseDTO);
        }
        return new PageImpl<>(dtos, brands.getPageable(), brands.getTotalElements());
    }
}

