package com.novalabsglobal.pharmacy.service.impl;

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
import com.novalabsglobal.pharmacy.repo.*;
import com.novalabsglobal.pharmacy.service.ItemService;
import com.novalabsglobal.pharmacy.service.mapper.ItemMapper;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
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
    private StockRepo stockRepo;

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

        if (stockRepo.getCountStocksUnderItemByItemId(id) > 0)
            throw new RuntimeException("This item cannot be deleted. Some stocks have this item");

        return itemRepo.deleteItem(id) > 0;
    }


    @Override
    public Page<ItemResponseDTO> getAllItems(Integer page, Integer size) {
        PageRequest pageRequest = (page == null && size == null) ? null : PageRequest.of(page, size);
        return new ItemMapper().entityToDTO(itemRepo.getAllItems(pageRequest));
    }

    /*Not expired and in-stock available items*/
    @Override
    public int countDistinctAvailableItemsInStock() {
        return itemRepo.countDistinctAvailableItemsInStock();
    }


    /*Expired or out-of-stocks items*/
    @Override
    public int countDistinctItemsOutOfStock() {
        int countDistinctItemsOutOfStock = 0;
        List<Integer> allItemsIds = itemRepo.getAllItemsIds();
        for (int i = 0; i < allItemsIds.size(); i++) {
            int availableStocksCountByItem = itemRepo.getAvailableStocksCountByItem(allItemsIds.get(i));
            if (availableStocksCountByItem == 0)
                countDistinctItemsOutOfStock++;
        }
        return countDistinctItemsOutOfStock;
    }

    /*Items currently available and run out of stock*/
    @Override
    public int countDistinctItemsRunOutOfStock() {
        int countDistinctItemsRunOutOfStock = 0;
        List<Integer> allItemsIds = itemRepo.getAllItemsIds();
        for (int i = 0; i < allItemsIds.size(); i++) {
            List<Object> availableStocksCountByItem = itemRepo.getAvailableStocksAvailableQtyAndPurchasedQtyByItem(allItemsIds.get(i));

            double availableQty = 0, purchasedQty = 0;
            for (Object o : availableStocksCountByItem) {
                Object[] arr = (Object[]) o;
                availableQty += (double) arr[0];
                purchasedQty += (double) arr[1];
            }

            if (availableQty<=(purchasedQty*(itemRepo.getItemReorderLevelByItemId(allItemsIds.get(i))*0.01)))
                countDistinctItemsRunOutOfStock++;

        }
        return countDistinctItemsRunOutOfStock;
    }
    @Override
    public int getExpiredAvailableStockItemsCount() {
        return itemRepo.getExpiredAvailableStockItemsCount();
    }
}
