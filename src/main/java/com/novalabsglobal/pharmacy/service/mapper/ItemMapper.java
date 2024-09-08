package com.novalabsglobal.pharmacy.service.mapper;

import com.novalabsglobal.pharmacy.dto.BrandDTO;
import com.novalabsglobal.pharmacy.dto.CategoryDTO;
import com.novalabsglobal.pharmacy.dto.ItemResponseDTO;
import com.novalabsglobal.pharmacy.dto.UnitDTO;

import java.util.ArrayList;
import java.util.List;

public class ItemMapper {
    public List<ItemResponseDTO> entityToDTO(List<Object> items) {
        List<ItemResponseDTO> dtos = new ArrayList<>();
        for (Object o : items) {
            Object[] arr = (Object[]) o;

            ItemResponseDTO itemResponseDTO = new ItemResponseDTO();
            itemResponseDTO.setId((Integer) arr[0]);
            itemResponseDTO.setName(String.valueOf(arr[1]));
            itemResponseDTO.setReorderLevel((Double) arr[2]);
            itemResponseDTO.setDescription(String.valueOf(arr[3]));

            UnitDTO unitDTO = new UnitDTO();
            unitDTO.setUnitName(String.valueOf(arr[4]));
            unitDTO.setUnitSymbology(String.valueOf(arr[5]));
            itemResponseDTO.setUnit(unitDTO);

            BrandDTO brandDTO = new BrandDTO();
            brandDTO.setName(String.valueOf(arr[6]));
            itemResponseDTO.setBrand(brandDTO);

            CategoryDTO categoryDTO = new CategoryDTO();
            categoryDTO.setName(String.valueOf(arr[7]));
            itemResponseDTO.setCategory(categoryDTO);

            dtos.add(itemResponseDTO);
        }
        return dtos;
    }
}

