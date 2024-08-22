package com.novalabsglobal.pharmacy.controller;

import com.novalabsglobal.pharmacy.dto.ShopDTO;
import com.novalabsglobal.pharmacy.service.ShopService;
import com.novalabsglobal.pharmacy.util.ExceptionHandler;
import com.novalabsglobal.pharmacy.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/pharmacy/shop")
public class ShopController {
    private static final Logger LOGGER = LoggerFactory.getLogger(ShopController.class);

    @Autowired
    private ShopService shopService;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    private ResponseEntity<ResponseUtil> saveOrUpdateShop(
            @RequestBody ShopDTO dto
    ) {
        try {
            String msg = (dto.getId() == null || dto.getId()==0) ? "Shop saved successfully." : "Shop updated successfully.";
            return ResponseEntity.ok(new ResponseUtil(HttpStatus.OK, msg, shopService.saveOrUpdateShop(dto)));
        } catch (Exception e) {
            LOGGER.error(e.getMessage());

            if (e.getMessage().equals("The shop name, contact and address is mandatory"))
                return ExceptionHandler.handleCustomException(HttpStatus.BAD_REQUEST, e);

            if (e.getMessage().equals("Feature is not exists!"))
                return ExceptionHandler.handleCustomException(HttpStatus.NOT_FOUND, e);

            return ExceptionHandler.handleException(e);
        }
    }

    @GetMapping
    private ResponseEntity<ResponseUtil> getShopDetails() {
        try {
            return ResponseEntity.ok(new ResponseUtil(HttpStatus.OK, "Successfully loaded", shopService.getShopDetails()));
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            return ExceptionHandler.handleException(e);
        }
    }
}
