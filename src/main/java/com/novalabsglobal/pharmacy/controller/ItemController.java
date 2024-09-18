package com.novalabsglobal.pharmacy.controller;

import com.novalabsglobal.pharmacy.dto.ItemDTO;
import com.novalabsglobal.pharmacy.service.ItemService;
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
@RequestMapping("/pharmacy/item")
public class ItemController {
    private static final Logger LOGGER = LoggerFactory.getLogger(ItemController.class);

    @Autowired
    private ItemService itemService;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    private ResponseEntity<ResponseUtil> saveOrUpdateItem(
            @RequestBody ItemDTO dto
    ) {
        try {
            String msg = (dto.getId() == null || dto.getId()==0) ? "Item saved successfully." : "Item updated successfully.";
            return ResponseEntity.ok(new ResponseUtil(HttpStatus.OK, msg, itemService.saveOrUpdateItem(dto)));
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            if (
                    e.getMessage().equals("Item is not exists!")
                    || e.getMessage().equals("Unit is not exists!")
                    || e.getMessage().equals("Brand is not exists!")
                    || e.getMessage().equals("Category is not exists!")
            )
                return ExceptionHandler.handleCustomException(HttpStatus.NOT_FOUND, e);

            return ExceptionHandler.handleException(e);
        }
    }

    @DeleteMapping("/{id}")
    private ResponseEntity<ResponseUtil> deleteItem(
            @PathVariable("id") Integer id
    ) {
        try {
            if (itemService.deleteItem(id)) {
                return ResponseEntity.ok(new ResponseUtil(HttpStatus.OK, "Successfully deleted", id));
            } else {
                return ResponseEntity.ok(new ResponseUtil(HttpStatus.INTERNAL_SERVER_ERROR, "Something went wrong!", id));
            }
        } catch (Exception e) {
            LOGGER.error(e.getMessage());

            if (e.getMessage().equals("Item is not exists!") ||
                    e.getMessage().equals("This item cannot be deleted. Some stocks have this item"))
                return ExceptionHandler.handleCustomException(HttpStatus.BAD_REQUEST, e);
            return ExceptionHandler.handleException(e);
        }
    }

    @GetMapping
    private ResponseEntity<ResponseUtil> getAllItems(
            @RequestParam(required = false) Integer page,
            @RequestParam(required = false) Integer size
    ) {
        try {
            return ResponseEntity.ok(new ResponseUtil(HttpStatus.OK, "Successfully loaded", itemService.getAllItems(page,size)));
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            return ExceptionHandler.handleException(e);
        }
    }

    @GetMapping("/in-stock/count")
    private ResponseEntity<ResponseUtil> countDistinctAvailableItemsInStock() {
        try {
            return ResponseEntity.ok(new ResponseUtil(HttpStatus.OK, "Successfully loaded", itemService.countDistinctAvailableItemsInStock()));
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            return ExceptionHandler.handleException(e);
        }
    }
    @GetMapping("/out-stock/count")
    private ResponseEntity<ResponseUtil> countDistinctItemsOutOfStock() {
        try {
            return ResponseEntity.ok(new ResponseUtil(HttpStatus.OK, "Successfully loaded", itemService.countDistinctItemsOutOfStock()));
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            return ExceptionHandler.handleException(e);
        }
    }
    @GetMapping("/run-out-stock/count")
    private ResponseEntity<ResponseUtil> countDistinctItemsRunOutOfStock() {
        try {
            return ResponseEntity.ok(new ResponseUtil(HttpStatus.OK, "Successfully loaded", itemService.countDistinctItemsRunOutOfStock()));
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            return ExceptionHandler.handleException(e);
        }
    }
}
