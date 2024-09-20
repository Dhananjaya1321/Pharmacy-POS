package com.novalabsglobal.pharmacy.service;

import com.novalabsglobal.pharmacy.dto.ItemDTO;
import com.novalabsglobal.pharmacy.dto.ItemResponseDTO;
import org.springframework.data.domain.Page;

public interface ItemService {
    ItemDTO saveOrUpdateItem(ItemDTO dto);

    boolean deleteItem(Integer id);

    Page<ItemResponseDTO> getAllItems(Integer page, Integer size);

    int countDistinctAvailableItemsInStock();

    int countDistinctItemsOutOfStock();

    int countDistinctItemsRunOutOfStock();

    int getExpiredAvailableStockItemsCount();
}
