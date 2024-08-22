package com.novalabsglobal.pharmacy.service;

import com.novalabsglobal.pharmacy.dto.ShopDTO;

public interface ShopService {
    ShopDTO saveOrUpdateShop(ShopDTO dto);

    ShopDTO getShopDetails();
}
