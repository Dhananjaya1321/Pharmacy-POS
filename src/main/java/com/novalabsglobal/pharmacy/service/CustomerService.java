package com.novalabsglobal.pharmacy.service;

import com.novalabsglobal.pharmacy.dto.CustomerDTO;
import com.novalabsglobal.pharmacy.dto.CustomerResponseDTO;
import com.novalabsglobal.pharmacy.dto.RoleDTO;

import java.util.List;

public interface CustomerService {
    CustomerDTO saveOrUpdateCustomer(CustomerDTO dto);

    boolean deleteCustomer(Integer id);

    List<CustomerResponseDTO> getAllCustomers();
}
