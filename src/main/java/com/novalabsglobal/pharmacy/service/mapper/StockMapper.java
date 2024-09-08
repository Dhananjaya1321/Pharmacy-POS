package com.novalabsglobal.pharmacy.service.mapper;

import com.novalabsglobal.pharmacy.dto.ItemDTO;
import com.novalabsglobal.pharmacy.dto.StockResponseDTO;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class StockMapper {
    public List<StockResponseDTO> entityToDTO(List<Object> stocks) {
        List<StockResponseDTO> dtos = new ArrayList<>();
        for (Object o : stocks) {
            Object[] arr = (Object[]) o;

            StockResponseDTO stockResponseDTO = new StockResponseDTO();
            stockResponseDTO.setId((Integer) arr[0]);
            stockResponseDTO.setExpiryDate((Date) arr[1]);
            stockResponseDTO.setPurchasedQty((Double) arr[2]);
            stockResponseDTO.setPurchasedAmount((Double) arr[3]);
            stockResponseDTO.setPurchasedDiscount((Double) arr[4]);

            ItemDTO itemDTO = new ItemDTO();
            itemDTO.setId((Integer) arr[5]);
            itemDTO.setName(String.valueOf(arr[6]));
            stockResponseDTO.setItem(itemDTO);

            dtos.add(stockResponseDTO);
        }
        return dtos;
    }
}

