package com.novalabsglobal.pharmacy.service.mapper;

import com.novalabsglobal.pharmacy.dto.ItemDTO;
import com.novalabsglobal.pharmacy.dto.StockResponseDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class StockMapper {
    public Page<StockResponseDTO> entityToDTO(Page<Object> stocks) {
        List<StockResponseDTO> dtos = new ArrayList<>();
        for (Object o : stocks) {
            Object[] arr = (Object[]) o;

            StockResponseDTO stockResponseDTO = new StockResponseDTO();
            stockResponseDTO.setId((Integer) arr[0]);
            stockResponseDTO.setExpiryDate((Date) arr[1]);
            stockResponseDTO.setPurchasedQty((Double) arr[2]);
            stockResponseDTO.setPurchasedAmount((Double) arr[3]);
            stockResponseDTO.setPurchasedDiscount((Double) arr[4]);
            stockResponseDTO.setAvailableQty((Double) arr[5]);
            stockResponseDTO.setDescription(String.valueOf(arr[6]));

            ItemDTO itemDTO = new ItemDTO();
            itemDTO.setId((Integer) arr[7]);
            itemDTO.setName(String.valueOf(arr[8]));
            stockResponseDTO.setItem(itemDTO);

            dtos.add(stockResponseDTO);
        }
        return new PageImpl<>(dtos, stocks.getPageable(), stocks.getTotalElements());
    }
}

