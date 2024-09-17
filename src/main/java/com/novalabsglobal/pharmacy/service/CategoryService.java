package com.novalabsglobal.pharmacy.service;

import com.novalabsglobal.pharmacy.dto.CategoryDTO;
import com.novalabsglobal.pharmacy.dto.CategoryResponseDTO;
import org.springframework.data.domain.Page;

public interface CategoryService {
    CategoryDTO saveOrUpdateCategory(CategoryDTO dto);

    boolean deleteCategory(Integer id);

    Page<CategoryResponseDTO> getAllCategories(Integer page, Integer size);

    int getCategoryCount();
}
