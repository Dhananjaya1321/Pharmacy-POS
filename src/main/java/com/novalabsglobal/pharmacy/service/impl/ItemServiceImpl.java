package com.novalabsglobal.pharmacy.service.impl;

import com.novalabsglobal.pharmacy.dto.BrandDTO;
import com.novalabsglobal.pharmacy.dto.BrandResponseDTO;
import com.novalabsglobal.pharmacy.dto.ItemDTO;
import com.novalabsglobal.pharmacy.dto.ItemResponseDTO;
import com.novalabsglobal.pharmacy.entity.Brand;
import com.novalabsglobal.pharmacy.entity.Category;
import com.novalabsglobal.pharmacy.entity.Item;
import com.novalabsglobal.pharmacy.entity.Unit;
import com.novalabsglobal.pharmacy.enums.BrandStatus;
import com.novalabsglobal.pharmacy.enums.CategoryStatus;
import com.novalabsglobal.pharmacy.enums.ItemStatus;
import com.novalabsglobal.pharmacy.enums.UnitStatus;
import com.novalabsglobal.pharmacy.repo.BrandRepo;
import com.novalabsglobal.pharmacy.repo.CategoryRepo;
import com.novalabsglobal.pharmacy.repo.ItemRepo;
import com.novalabsglobal.pharmacy.repo.UnitRepo;
import com.novalabsglobal.pharmacy.service.ItemService;
import com.novalabsglobal.pharmacy.service.mapper.BrandMapper;
import com.novalabsglobal.pharmacy.service.mapper.ItemMapper;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ItemServiceImpl implements ItemService {
    @Autowired
    private ItemRepo itemRepo;

    @Autowired
    private UnitRepo unitRepo;

    @Autowired
    private BrandRepo brandRepo;

    @Autowired
    private CategoryRepo categoryRepo;

    @Autowired
    private ModelMapper modelMapper;


    @Override
    public ItemDTO saveOrUpdateItem(ItemDTO dto) {
        if (dto.getId() == null || dto.getId() == 0) {
            dto.setStatus(ItemStatus.ACTIVE);
        } else {
            if (!itemRepo.existsById(dto.getId()) || itemRepo.getStatus(dto.getId()).equals(ItemStatus.DELETED))
                throw new RuntimeException("Item is not exists!");

            dto.setStatus(itemRepo.getStatus(dto.getId()));
        }

        if (!unitRepo.existsById(dto.getUnit()) || unitRepo.getStatus(dto.getUnit()).equals(UnitStatus.DELETED))
            throw new RuntimeException("Unit is not exists!");

        if (!brandRepo.existsById(dto.getBrand()) || brandRepo.getStatus(dto.getBrand()).equals(BrandStatus.DELETED))
            throw new RuntimeException("Brand is not exists!");

        if (!categoryRepo.existsById(dto.getCategory()) || categoryRepo.getStatus(dto.getCategory()).equals(CategoryStatus.DELETED))
            throw new RuntimeException("Category is not exists!");

        Item item = modelMapper.map(dto, Item.class);

        item.setUnit(new Unit(dto.getUnit()));
        item.setBrand(new Brand(dto.getBrand()));
        item.setCategory(new Category(dto.getCategory()));

        item = itemRepo.save(item);

        dto.setId(item.getId());
        return dto;
    }

    @Override
    public boolean deleteItem(Integer id) {
        if (!itemRepo.existsById(id) || itemRepo.getStatus(id).equals(ItemStatus.DELETED))
            throw new RuntimeException("Item is not exists!");

        return itemRepo.deleteItem(id) > 0;
    }


    @Override
    public List<ItemResponseDTO> getAllItems() {
        return new ItemMapper().entityToDTO(itemRepo.getAllItems());
    }
}
