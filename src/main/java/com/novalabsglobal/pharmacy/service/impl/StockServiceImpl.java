package com.novalabsglobal.pharmacy.service.impl;

import com.novalabsglobal.pharmacy.dto.StockDTO;
import com.novalabsglobal.pharmacy.dto.StockResponseDTO;
import com.novalabsglobal.pharmacy.entity.Item;
import com.novalabsglobal.pharmacy.entity.Stock;
import com.novalabsglobal.pharmacy.enums.StockStatus;
import com.novalabsglobal.pharmacy.repo.StockRepo;
import com.novalabsglobal.pharmacy.service.StockService;
import com.novalabsglobal.pharmacy.service.mapper.StockMapper;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class StockServiceImpl implements StockService {
    @Autowired
    private StockRepo stockRepo;

    @Autowired
    private ModelMapper modelMapper;


    @Override
    public StockDTO saveOrUpdateStock(StockDTO dto) {
        if (dto.getId() == null || dto.getId() == 0) {
            dto.setAvailableQty(dto.getPurchasedQty());
            dto.setStatus(StockStatus.ACTIVE);
        } else {
            if (!stockRepo.existsById(dto.getId()) || stockRepo.getStatus(dto.getId()).equals(StockStatus.DELETED))
                throw new RuntimeException("Stock is not exists!");

            dto.setStatus(stockRepo.getStatus(dto.getId()));
        }
        Stock stock = modelMapper.map(dto, Stock.class);
        stock.setItem(new Item(dto.getItem()));
        stock = stockRepo.save(stock);

        dto.setId(stock.getId());
        return dto;
    }

    @Override
    public boolean deleteStock(Integer id) {
        if (!stockRepo.existsById(id) || stockRepo.getStatus(id).equals(StockStatus.DELETED))
            throw new RuntimeException("Stock is not exists!");

        return stockRepo.deleteStock(id) > 0;
    }


    @Override
    public Page<StockResponseDTO> getAllStocks(Integer page, Integer size) {
        PageRequest pageRequest = (page == null && size == null) ? null : PageRequest.of(page, size);
        return new StockMapper().entityToDTO(stockRepo.getAllStocks(pageRequest));
    }
}
