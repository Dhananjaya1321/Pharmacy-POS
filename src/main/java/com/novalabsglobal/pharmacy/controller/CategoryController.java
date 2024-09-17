package com.novalabsglobal.pharmacy.controller;

import com.novalabsglobal.pharmacy.dto.CategoryDTO;
import com.novalabsglobal.pharmacy.service.CategoryService;
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
@RequestMapping("/pharmacy/category")
public class CategoryController {
    private static final Logger LOGGER = LoggerFactory.getLogger(CategoryController.class);

    @Autowired
    private CategoryService categoryService;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    private ResponseEntity<ResponseUtil> saveOrUpdateCategory(
            @RequestBody CategoryDTO dto
    ) {
        try {
            String msg = (dto.getId() == null || dto.getId()==0) ? "Category saved successfully." : "Category updated successfully.";
            return ResponseEntity.ok(new ResponseUtil(HttpStatus.OK, msg, categoryService.saveOrUpdateCategory(dto)));
        } catch (Exception e) {
            LOGGER.error(e.getMessage());

            if (e.getMessage().equals("The category name is mandatory"))
                return ExceptionHandler.handleCustomException(HttpStatus.BAD_REQUEST, e);

            if (e.getMessage().equals("Category is not exists!"))
                return ExceptionHandler.handleCustomException(HttpStatus.NOT_FOUND, e);

            return ExceptionHandler.handleException(e);
        }
    }

    @DeleteMapping("/{id}")
    private ResponseEntity<ResponseUtil> deleteCategory(
            @PathVariable("id") Integer id
    ) {
        try {
            if (categoryService.deleteCategory(id)) {
                return ResponseEntity.ok(new ResponseUtil(HttpStatus.OK, "Successfully deleted", id));
            } else {
                return ResponseEntity.ok(new ResponseUtil(HttpStatus.INTERNAL_SERVER_ERROR, "Something went wrong!", id));
            }
        } catch (Exception e) {
            LOGGER.error(e.getMessage());

            if (e.getMessage().equals("Category is not exists!") ||
                    e.getMessage().equals("This category cannot be deleted. Some items are under category"))
                return ExceptionHandler.handleCustomException(HttpStatus.BAD_REQUEST, e);

            return ExceptionHandler.handleException(e);
        }
    }

    @GetMapping
    private ResponseEntity<ResponseUtil> getAllCategories(
            @RequestParam(required = false) Integer page,
            @RequestParam(required = false) Integer size
    ) {
        try {
            return ResponseEntity.ok(new ResponseUtil(HttpStatus.OK, "Successfully loaded", categoryService.getAllCategories(page,size)));
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            return ExceptionHandler.handleException(e);
        }
    }

    @GetMapping("/count")
    private ResponseEntity<ResponseUtil> getCategoryCount() {
        try {
            return ResponseEntity.ok(new ResponseUtil(HttpStatus.OK, "Successfully loaded", categoryService.getCategoryCount()));
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            return ExceptionHandler.handleException(e);
        }
    }
}
