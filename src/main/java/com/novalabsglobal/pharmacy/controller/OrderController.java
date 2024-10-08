package com.novalabsglobal.pharmacy.controller;

import com.novalabsglobal.pharmacy.service.BrandService;
import com.novalabsglobal.pharmacy.service.OrderService;
import com.novalabsglobal.pharmacy.util.ExceptionHandler;
import com.novalabsglobal.pharmacy.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/pharmacy/order")
public class OrderController {
    private static final Logger LOGGER = LoggerFactory.getLogger(OrderController.class);

    @Autowired
    private OrderService orderService;

    @GetMapping("/reference-number")
    private ResponseEntity<ResponseUtil> getReferenceNumber() {
        try {
            return ResponseEntity.ok(new ResponseUtil(HttpStatus.OK, "Successfully loaded", orderService.getReferenceNumber()));
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            return ExceptionHandler.handleException(e);
        }
    }
}
