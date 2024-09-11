package com.novalabsglobal.pharmacy.controller;

import com.novalabsglobal.pharmacy.dto.SupplierDTO;
import com.novalabsglobal.pharmacy.service.SupplierService;
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
@RequestMapping("/pharmacy/supplier")
public class SupplierController {
    private static final Logger LOGGER = LoggerFactory.getLogger(SupplierController.class);

    @Autowired
    private SupplierService supplierService;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    private ResponseEntity<ResponseUtil> saveOrUpdateSupplier(
            @RequestBody SupplierDTO dto
    ) {
        try {
            String msg = (dto.getId() == null || dto.getId()==0) ? "Supplier saved successfully." : "Supplier updated successfully.";
            return ResponseEntity.ok(new ResponseUtil(HttpStatus.OK, msg, supplierService.saveOrUpdateSupplier(dto)));
        } catch (Exception e) {
            LOGGER.error(e.getMessage());

            if (e.getMessage().equals("The supplier name is mandatory"))
                return ExceptionHandler.handleCustomException(HttpStatus.BAD_REQUEST, e);

            if (e.getMessage().equals("Supplier is not exists!"))
                return ExceptionHandler.handleCustomException(HttpStatus.NOT_FOUND, e);

            return ExceptionHandler.handleException(e);
        }
    }

    @DeleteMapping("/{id}")
    private ResponseEntity<ResponseUtil> deleteSupplier(
            @PathVariable("id") Integer id
    ) {
        try {
            if (supplierService.deleteSupplier(id)) {
                return ResponseEntity.ok(new ResponseUtil(HttpStatus.OK, "Successfully deleted", id));
            } else {
                return ResponseEntity.ok(new ResponseUtil(HttpStatus.INTERNAL_SERVER_ERROR, "Something went wrong!", id));
            }
        } catch (Exception e) {
            LOGGER.error(e.getMessage());

            if (e.getMessage().equals("SupplierSupplier is not exists!"))
                return ExceptionHandler.handleCustomException(HttpStatus.BAD_REQUEST, e);
            return ExceptionHandler.handleException(e);
        }
    }

    @GetMapping
    private ResponseEntity<ResponseUtil> getAllSuppliers(
            @RequestParam(required = false) Integer page,
            @RequestParam(required = false) Integer size
    ) {
        try {
            return ResponseEntity.ok(new ResponseUtil(HttpStatus.OK, "Successfully loaded", supplierService.getAllSuppliers(page,size)));
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            return ExceptionHandler.handleException(e);
        }
    }
}
