package com.novalabsglobal.pharmacy.service;

import com.novalabsglobal.pharmacy.dto.StockDTO;
import com.novalabsglobal.pharmacy.dto.StockResponseDTO;
import org.springframework.data.domain.Page;

public interface StockService {
    StockDTO saveOrUpdateStock(StockDTO dto);

    boolean deleteStock(Integer id);

    Page<StockResponseDTO> getAllStocks(Integer page, Integer size);
}
