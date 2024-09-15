package com.novalabsglobal.pharmacy.service.mapper;

import com.novalabsglobal.pharmacy.dto.BrandResponseDTO;
import com.novalabsglobal.pharmacy.dto.CategoryDTO;
import com.novalabsglobal.pharmacy.dto.ItemResponseDTO;
import com.novalabsglobal.pharmacy.dto.UnitDTO;
import com.novalabsglobal.pharmacy.entity.Brand;
import com.novalabsglobal.pharmacy.enums.ItemStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class BrandMapper {
    public Page<BrandResponseDTO> brandEntitiesToDTOs(Page<Brand> brands) {
        List<BrandResponseDTO> dtos = new ArrayList<>();
        for (Brand b : brands) {
            BrandResponseDTO brandResponseDTO = new BrandResponseDTO();
            brandResponseDTO.setId(b.getId());
            brandResponseDTO.setName(b.getName());
            brandResponseDTO.setContact(b.getContact());
            brandResponseDTO.setWebsite(b.getWebsite());
            brandResponseDTO.setAddress(b.getAddress());
            brandResponseDTO.setDescription(b.getDescription());

            dtos.add(brandResponseDTO);
        }
        return new PageImpl<>(dtos, brands.getPageable(), brands.getTotalElements());
    }

    public BrandResponseDTO brandEntityToDTO(List<Object> brand) {
        BrandResponseDTO brandResponseDTO = new BrandResponseDTO();;
        ArrayList<ItemResponseDTO> items = new ArrayList<>();

        for (Object o : brand) {
            Object[] arr = (Object[]) o;

            if (brandResponseDTO.getId() == null) {
                brandResponseDTO.setId((Integer) arr[0]);
                brandResponseDTO.setAddress(String.valueOf(arr[1]));
                brandResponseDTO.setContact(String.valueOf(arr[2]));
                brandResponseDTO.setDescription(String.valueOf(arr[3]));
                brandResponseDTO.setName(String.valueOf(arr[4]));
                brandResponseDTO.setWebsite(String.valueOf(arr[5]));
            }

            if (arr[9]==null || ItemStatus.valueOf(String.valueOf(arr[9])).equals(ItemStatus.DELETED))
                continue;

            ItemResponseDTO itemResponseDTO=new ItemResponseDTO();
            itemResponseDTO.setId((Integer) arr[6]);
            itemResponseDTO.setName(String.valueOf(arr[7]));
            itemResponseDTO.setDescription(String.valueOf(arr[8]));

            CategoryDTO categoryDTO = new CategoryDTO();
            categoryDTO.setName(String.valueOf(arr[10]));
            categoryDTO.setDescription(String.valueOf(arr[11]));
            itemResponseDTO.setCategory(categoryDTO);

            UnitDTO unitDTO = new UnitDTO();
            unitDTO.setUnitName(String.valueOf(arr[12]));
            unitDTO.setUnitSymbology(String.valueOf(arr[13]));
            itemResponseDTO.setUnit(unitDTO);

            items.add(itemResponseDTO);

        }
        brandResponseDTO.setItems(items);
        return brandResponseDTO;
    }
}

