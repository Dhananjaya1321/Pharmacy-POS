package com.novalabsglobal.pharmacy.service;

import com.novalabsglobal.pharmacy.dto.CategoryDTO;
import com.novalabsglobal.pharmacy.dto.CategoryResponseDTO;
import com.novalabsglobal.pharmacy.dto.CustomerDTO;
import com.novalabsglobal.pharmacy.dto.CustomerResponseDTO;

import java.util.List;

public interface CategoryService {
    CategoryDTO saveOrUpdateCategory(CategoryDTO dto);

    boolean deleteCategory(Integer id);

    List<CategoryResponseDTO> getAllCategories();
}
