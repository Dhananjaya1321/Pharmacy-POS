package com.novalabsglobal.pharmacy.service.impl;

import com.novalabsglobal.pharmacy.dto.CategoryDTO;
import com.novalabsglobal.pharmacy.dto.CategoryResponseDTO;
import com.novalabsglobal.pharmacy.entity.Category;
import com.novalabsglobal.pharmacy.enums.CategoryStatus;
import com.novalabsglobal.pharmacy.repo.CategoryRepo;
import com.novalabsglobal.pharmacy.repo.ItemRepo;
import com.novalabsglobal.pharmacy.service.CategoryService;
import com.novalabsglobal.pharmacy.service.mapper.CategoryMapper;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private CategoryRepo categoryRepo;

    @Autowired
    private ItemRepo itemRepo;

    @Autowired
    private ModelMapper modelMapper;


    @Override
    public CategoryDTO saveOrUpdateCategory(CategoryDTO dto) {
        if (dto.getName() == null)
            throw new RuntimeException("The category name is mandatory");

        if (dto.getId() == null || dto.getId() == 0) {
            dto.setStatus(CategoryStatus.ACTIVE);
        } else {
            if (!categoryRepo.existsById(dto.getId()) || categoryRepo.getStatus(dto.getId()).equals(CategoryStatus.DELETED))
                throw new RuntimeException("Category is not exists!");

            dto.setStatus(categoryRepo.getStatus(dto.getId()));
        }
        Category category = categoryRepo.save(modelMapper.map(dto, Category.class));

        dto.setId(category.getId());
        return dto;
    }

    @Override
    public boolean deleteCategory(Integer id) {
        if (!categoryRepo.existsById(id) || categoryRepo.getStatus(id).equals(CategoryStatus.DELETED))
            throw new RuntimeException("Category is not exists!");

        if (itemRepo.getCountItemsUnderCategoryByCategoryId(id) > 0)
            throw new RuntimeException("This category cannot be deleted. Some items are under category");

        return categoryRepo.deleteCategory(id) > 0;
    }


    @Override
    public Page<CategoryResponseDTO> getAllCategories(Integer page, Integer size) {
        PageRequest pageRequest = (page == null && size == null) ? null : PageRequest.of(page, size);
        return new CategoryMapper().entityToDTO(categoryRepo.getAllCategories(pageRequest));
    }

    @Override
    public int getCategoryCount() {
        return categoryRepo.getCategoryCount();
    }
}
