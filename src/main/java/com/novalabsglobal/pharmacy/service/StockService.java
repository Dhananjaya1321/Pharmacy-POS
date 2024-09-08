package com.novalabsglobal.pharmacy.service;

import com.novalabsglobal.pharmacy.dto.StockDTO;
import com.novalabsglobal.pharmacy.dto.StockResponseDTO;

import java.util.List;

public interface StockService {
    StockDTO saveOrUpdateStock(StockDTO dto);

    boolean deleteStock(Integer id);

    List<StockResponseDTO> getAllStocks();
}
