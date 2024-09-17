package com.novalabsglobal.pharmacy.controller;

import com.novalabsglobal.pharmacy.dto.UserDTO;
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
@RequestMapping("/pharmacy/user")
public class UserController {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    private ResponseEntity<ResponseUtil> saveOrUpdateUser(
            @RequestBody UserDTO dto
    ) {
        try {
            String msg = (dto.getId() == null || dto.getId()==0) ? "User saved successfully." : "User updated successfully.";
            return ResponseEntity.ok(new ResponseUtil(HttpStatus.OK, msg, userService.saveOrUpdateUser(dto)));
        } catch (Exception e) {
            LOGGER.error(e.getMessage());

            if (e.getMessage().equals("The name is mandatory") || e.getMessage().equals("The password and username is mandatory"))
                return ExceptionHandler.handleCustomException(HttpStatus.BAD_REQUEST, e);

            if (e.getMessage().equals("User is not exists!"))
                return ExceptionHandler.handleCustomException(HttpStatus.NOT_FOUND, e);

            return ExceptionHandler.handleException(e);
        }
    }

    @DeleteMapping("/{id}")
    private ResponseEntity<ResponseUtil> deleteUser(
            @PathVariable("id") Integer id
    ) {
        try {
            if (userService.deleteUser(id)) {
                return ResponseEntity.ok(new ResponseUtil(HttpStatus.OK, "Successfully deleted", id));
            } else {
                return ResponseEntity.ok(new ResponseUtil(HttpStatus.INTERNAL_SERVER_ERROR, "Something went wrong!", id));
            }
        } catch (Exception e) {
            LOGGER.error(e.getMessage());

            if (e.getMessage().equals("User is not exists!"))
                return ExceptionHandler.handleCustomException(HttpStatus.BAD_REQUEST, e);
            return ExceptionHandler.handleException(e);
        }
    }

    @GetMapping
    private ResponseEntity<ResponseUtil> getAllUserDetails() {
        try {
            return ResponseEntity.ok(new ResponseUtil(HttpStatus.OK, "Successfully loaded", userService.getAllUserDetails()));
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            return ExceptionHandler.handleException(e);
        }
    }

    @GetMapping("/{id}")
    private ResponseEntity<ResponseUtil> getAllUserById( @PathVariable("id") Integer id) {
        try {
            return ResponseEntity.ok(new ResponseUtil(HttpStatus.OK, "Successfully loaded", userService.getAllUserById(id)));
        } catch (Exception e) {
            LOGGER.error(e.getMessage());

            if (e.getMessage().equals("User is not exists!"))
                return ExceptionHandler.handleCustomException(HttpStatus.BAD_REQUEST, e);
            return ExceptionHandler.handleException(e);
        }
    }

    @GetMapping("/count")
    private ResponseEntity<ResponseUtil> getUserCount() {
        try {
            return ResponseEntity.ok(new ResponseUtil(HttpStatus.OK, "Successfully loaded", userService.getUserCount()));
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            return ExceptionHandler.handleException(e);
        }
    }
}
