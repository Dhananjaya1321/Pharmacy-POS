package com.novalabsglobal.pharmacy.controller;

import com.novalabsglobal.pharmacy.dto.CategoryDTO;
import com.novalabsglobal.pharmacy.dto.UnitDTO;
import com.novalabsglobal.pharmacy.service.UnitService;
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
@RequestMapping("/pharmacy/unit")
public class UnitController {
    private static final Logger LOGGER = LoggerFactory.getLogger(UnitController.class);

    @Autowired
    private UnitService unitService;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    private ResponseEntity<ResponseUtil> saveOrUpdateCategory(
            @RequestBody UnitDTO dto
    ) {
        try {
            String msg = (dto.getId() == null || dto.getId()==0) ? "Unit saved successfully." : "Unit updated successfully.";
            return ResponseEntity.ok(new ResponseUtil(HttpStatus.OK, msg, unitService.saveOrUpdateUnit(dto)));
        } catch (Exception e) {
            LOGGER.error(e.getMessage());

            if (e.getMessage().equals("The unit name and symbology is mandatory"))
                return ExceptionHandler.handleCustomException(HttpStatus.BAD_REQUEST, e);

            if (e.getMessage().equals("Unit is not exists!"))
                return ExceptionHandler.handleCustomException(HttpStatus.NOT_FOUND, e);

            return ExceptionHandler.handleException(e);
        }
    }

    @DeleteMapping("/{id}")
    private ResponseEntity<ResponseUtil> deleteUnit(
            @PathVariable("id") Integer id
    ) {
        try {
            if (unitService.deleteUnit(id)) {
                return ResponseEntity.ok(new ResponseUtil(HttpStatus.OK, "Successfully deleted", id));
            } else {
                return ResponseEntity.ok(new ResponseUtil(HttpStatus.INTERNAL_SERVER_ERROR, "Something went wrong!", id));
            }
        } catch (Exception e) {
            LOGGER.error(e.getMessage());

            if (e.getMessage().equals("Unit is not exists!") ||
                    e.getMessage().equals("This unit cannot be deleted. Some items are under unit"))
                return ExceptionHandler.handleCustomException(HttpStatus.BAD_REQUEST, e);

            return ExceptionHandler.handleException(e);
        }
    }

    @GetMapping
    private ResponseEntity<ResponseUtil> getAllUnits(
            @RequestParam(required = false) Integer page,
            @RequestParam(required = false) Integer size
    ) {
        try {
            return ResponseEntity.ok(new ResponseUtil(HttpStatus.OK, "Successfully loaded", unitService.getAllUnits(page,size)));
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            return ExceptionHandler.handleException(e);
        }
    }
}
