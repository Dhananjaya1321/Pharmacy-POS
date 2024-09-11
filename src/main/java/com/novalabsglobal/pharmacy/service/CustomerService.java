package com.novalabsglobal.pharmacy.service;

import com.novalabsglobal.pharmacy.dto.CustomerDTO;
import com.novalabsglobal.pharmacy.dto.CustomerResponseDTO;
import org.springframework.data.domain.Page;

public interface CustomerService {
    CustomerDTO saveOrUpdateCustomer(CustomerDTO dto);

    boolean deleteCustomer(Integer id);

    Page<CustomerResponseDTO> getAllCustomers(Integer page, Integer size);
}
