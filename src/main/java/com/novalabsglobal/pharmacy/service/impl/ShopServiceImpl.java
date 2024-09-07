package com.novalabsglobal.pharmacy.service.impl;

import com.novalabsglobal.pharmacy.dto.ShopDTO;
import com.novalabsglobal.pharmacy.entity.Shop;
import com.novalabsglobal.pharmacy.enums.ShopStatus;
import com.novalabsglobal.pharmacy.repo.ShopRepo;
import com.novalabsglobal.pharmacy.service.ShopService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ShopServiceImpl implements ShopService {
    @Autowired
    private ShopRepo shopRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public ShopDTO saveOrUpdateShop(ShopDTO dto) {
//        if (dto.getName() == null || dto.getContact() == null || dto.getAddress() == null)
//            throw new RuntimeException("The shop name, contact and address is mandatory");

        if (dto.getId() == null || dto.getId() == 0) {
            dto.setStatus(ShopStatus.ACTIVE);
        } else {
            if (!shopRepo.existsById(dto.getId()))
                throw new RuntimeException("Shop is not exists!");

            dto.setStatus(shopRepo.getStatus(dto.getId()));
        }
        Shop shop = shopRepo.save(modelMapper.map(dto, Shop.class));

        dto.setId(shop.getId());
        return dto;
    }

    @Override
    public ShopDTO getShopDetails() {
        return modelMapper.map(
                shopRepo.findAll().get(0),
                ShopDTO.class
        );
    }
}
