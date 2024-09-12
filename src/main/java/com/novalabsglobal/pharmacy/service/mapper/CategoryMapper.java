package com.novalabsglobal.pharmacy.service.mapper;

import com.novalabsglobal.pharmacy.dto.CategoryResponseDTO;
import com.novalabsglobal.pharmacy.entity.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import java.util.ArrayList;
import java.util.List;

public class CategoryMapper {
    public Page<CategoryResponseDTO> entityToDTO(Page<Category> categories) {
        List<CategoryResponseDTO> dtos = new ArrayList<>();
        for (Category c : categories) {
            CategoryResponseDTO categoryResponseDTO = new CategoryResponseDTO();
            categoryResponseDTO.setId(c.getId());
            categoryResponseDTO.setName(c.getName());
            categoryResponseDTO.setDescription(c.getDescription());

            dtos.add(categoryResponseDTO);
        }
        return new PageImpl<>(dtos, categories.getPageable(), categories.getTotalElements());
    }
}

