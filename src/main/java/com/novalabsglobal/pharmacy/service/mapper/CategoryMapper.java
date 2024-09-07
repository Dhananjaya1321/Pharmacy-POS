package com.novalabsglobal.pharmacy.service.mapper;

import com.novalabsglobal.pharmacy.dto.CategoryResponseDTO;
import com.novalabsglobal.pharmacy.entity.Category;

import java.util.ArrayList;
import java.util.List;

public class CategoryMapper {
    public List<CategoryResponseDTO> entityToDTO(List<Category> categories) {
        List<CategoryResponseDTO> dtos = new ArrayList<>();
        for (Category c : categories) {
            CategoryResponseDTO categoryResponseDTO = new CategoryResponseDTO();
            categoryResponseDTO.setId(c.getId());
            categoryResponseDTO.setName(c.getName());
            categoryResponseDTO.setDescription(c.getDescription());
//            categoryResponseDTO.setStatus(c.getStatus());
//            categoryResponseDTO.setCreatedBy(c.getCreatedBy());
//            categoryResponseDTO.setUpdatedBy(c.getUpdatedBy());
//            categoryResponseDTO.setLastUpdate(c.getLastUpdate());

            dtos.add(categoryResponseDTO);
        }
        return dtos;
    }
}

