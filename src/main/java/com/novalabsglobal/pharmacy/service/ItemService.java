package com.novalabsglobal.pharmacy.service;

import com.novalabsglobal.pharmacy.dto.BrandDTO;
import com.novalabsglobal.pharmacy.dto.BrandResponseDTO;
import com.novalabsglobal.pharmacy.dto.ItemDTO;
import com.novalabsglobal.pharmacy.dto.ItemResponseDTO;

import java.util.List;

public interface ItemService {
    ItemDTO saveOrUpdateItem(ItemDTO dto);

    boolean deleteItem(Integer id);

    List<ItemResponseDTO> getAllItems();
}
