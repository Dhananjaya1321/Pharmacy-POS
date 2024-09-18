package com.novalabsglobal.pharmacy.controller;

import com.novalabsglobal.pharmacy.dto.CustomerDTO;
import com.novalabsglobal.pharmacy.dto.UserDTO;
import com.novalabsglobal.pharmacy.service.CustomerService;
import com.novalabsglobal.pharmacy.service.UserService;
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
@RequestMapping("/pharmacy/customer")
public class CustomerController {
    private static final Logger LOGGER = LoggerFactory.getLogger(CustomerController.class);

    @Autowired
    private CustomerService customerService;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    private ResponseEntity<ResponseUtil> saveOrUpdateCustomer(
            @RequestBody CustomerDTO dto
    ) {
        try {
            String msg = (dto.getId() == null || dto.getId()==0) ? "Customer saved successfully." : "Customer updated successfully.";
            return ResponseEntity.ok(new ResponseUtil(HttpStatus.OK, msg, customerService.saveOrUpdateCustomer(dto)));
        } catch (Exception e) {
            LOGGER.error(e.getMessage());

            if (e.getMessage().equals("The customer name is mandatory") || e.getMessage().equals("The password and username is mandatory"))
                return ExceptionHandler.handleCustomException(HttpStatus.BAD_REQUEST, e);

            if (e.getMessage().equals("Customer is not exists!"))
                return ExceptionHandler.handleCustomException(HttpStatus.NOT_FOUND, e);

            return ExceptionHandler.handleException(e);
        }
    }

    @DeleteMapping("/{id}")
    private ResponseEntity<ResponseUtil> deleteCustomer(
            @PathVariable("id") Integer id
    ) {
        try {
            if (customerService.deleteCustomer(id)) {
                return ResponseEntity.ok(new ResponseUtil(HttpStatus.OK, "Successfully deleted", id));
            } else {
                return ResponseEntity.ok(new ResponseUtil(HttpStatus.INTERNAL_SERVER_ERROR, "Something went wrong!", id));
            }
        } catch (Exception e) {
            LOGGER.error(e.getMessage());

            if (e.getMessage().equals("Customer is not exists!"))
                return ExceptionHandler.handleCustomException(HttpStatus.BAD_REQUEST, e);
            return ExceptionHandler.handleException(e);
        }
    }

    @GetMapping
    private ResponseEntity<ResponseUtil> getAllCustomers(
            @RequestParam(required = false) Integer page,
            @RequestParam(required = false) Integer size
    ) {
        try {
            return ResponseEntity.ok(new ResponseUtil(HttpStatus.OK, "Successfully loaded", customerService.getAllCustomers(page,size)));
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            return ExceptionHandler.handleException(e);
        }
    }

    @GetMapping("/count")
    private ResponseEntity<ResponseUtil> getCustomersCount() {
        try {
            return ResponseEntity.ok(new ResponseUtil(HttpStatus.OK, "Successfully loaded", customerService.getCustomersCount()));
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            return ExceptionHandler.handleException(e);
        }
    }
}
