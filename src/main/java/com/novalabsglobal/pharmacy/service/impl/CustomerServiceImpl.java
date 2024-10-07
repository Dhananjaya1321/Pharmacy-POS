package com.novalabsglobal.pharmacy.service.impl;

import com.novalabsglobal.pharmacy.dto.CustomerDTO;
import com.novalabsglobal.pharmacy.dto.CustomerResponseDTO;
import com.novalabsglobal.pharmacy.entity.Customer;
import com.novalabsglobal.pharmacy.enums.CustomerStatus;
import com.novalabsglobal.pharmacy.repo.CustomerRepo;
import com.novalabsglobal.pharmacy.service.CustomerService;
import com.novalabsglobal.pharmacy.service.mapper.CustomerMapper;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class CustomerServiceImpl implements CustomerService {
    @Autowired
    private CustomerRepo customerRepo;

    @Autowired
    private ModelMapper modelMapper;


    @Override
    public CustomerDTO saveOrUpdateCustomer(CustomerDTO dto) {
        if (dto.getName() == null)
            throw new RuntimeException("The customer name is mandatory");

        if (dto.getId() == null || dto.getId() == 0) {
            dto.setStatus(CustomerStatus.ACTIVE);
        } else {
            if (!customerRepo.existsById(dto.getId()) || customerRepo.getStatus(dto.getId()).equals(CustomerStatus.DELETED))
                throw new RuntimeException("Customer is not exists!");

            dto.setStatus(customerRepo.getStatus(dto.getId()));
        }
        Customer customer = customerRepo.save(modelMapper.map(dto, Customer.class));

        dto.setId(customer.getId());
        return dto;
    }

    @Override
    public boolean deleteCustomer(Integer id) {
        if (!customerRepo.existsById(id) || customerRepo.getStatus(id).equals(CustomerStatus.DELETED))
            throw new RuntimeException("Customer is not exists!");

        return customerRepo.deleteCustomer(id) > 0;
    }

    @Override
    public Page<CustomerResponseDTO> getAllCustomers(Integer page, Integer size) {
        PageRequest pageRequest = (page == null && size == null) ? null : PageRequest.of(page, size);
        return new CustomerMapper().entityToDTO(customerRepo.getAllCustomers(pageRequest));
    }

    @Override
    public int getCustomersCount() {
        return customerRepo.getCustomersCount();
    }

    @Override
    public List<CustomerDTO> searchCustomers(String query) {
        List<Object> objects = customerRepo.searchCustomers(query);
        List<CustomerDTO> dtos = new ArrayList<>();

        for(Object o:objects){
            Object[] arr= (Object[]) o;
            CustomerDTO customerDTO = new CustomerDTO();
            customerDTO.setId((Integer) arr[0]);
            customerDTO.setName(String.valueOf(arr[1]));
            customerDTO.setContact(String.valueOf(arr[2]));

            dtos.add(customerDTO);
        }
        return dtos;
    }
}
