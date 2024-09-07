package com.novalabsglobal.pharmacy.service.mapper;

import com.novalabsglobal.pharmacy.dto.BrandResponseDTO;
import com.novalabsglobal.pharmacy.dto.CategoryResponseDTO;
import com.novalabsglobal.pharmacy.entity.Brand;
import com.novalabsglobal.pharmacy.entity.Category;

import java.util.ArrayList;
import java.util.List;

public class BrandMapper {
    public List<BrandResponseDTO> entityToDTO(List<Brand> brands) {
        List<BrandResponseDTO> dtos = new ArrayList<>();
        for (Brand b : brands) {
            BrandResponseDTO brandResponseDTO = new BrandResponseDTO();
            brandResponseDTO.setId(b.getId());
            brandResponseDTO.setName(b.getName());
            brandResponseDTO.setContact(b.getContact());
            brandResponseDTO.setWebsite(b.getWebsite());
            brandResponseDTO.setAddress(b.getAddress());
            brandResponseDTO.setDescription(b.getDescription());
//            categoryResponseDTO.setStatus(c.getStatus());
//            categoryResponseDTO.setCreatedBy(c.getCreatedBy());
//            categoryResponseDTO.setUpdatedBy(c.getUpdatedBy());
//            categoryResponseDTO.setLastUpdate(c.getLastUpdate());

            dtos.add(brandResponseDTO);
        }
        return dtos;
    }
}

