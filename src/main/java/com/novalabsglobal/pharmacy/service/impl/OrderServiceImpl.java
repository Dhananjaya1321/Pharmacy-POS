package com.novalabsglobal.pharmacy.service.impl;

import com.novalabsglobal.pharmacy.dto.BrandDTO;
import com.novalabsglobal.pharmacy.dto.BrandResponseDTO;
import com.novalabsglobal.pharmacy.entity.Brand;
import com.novalabsglobal.pharmacy.enums.BrandStatus;
import com.novalabsglobal.pharmacy.repo.BrandRepo;
import com.novalabsglobal.pharmacy.repo.ItemRepo;
import com.novalabsglobal.pharmacy.repo.OrderRepo;
import com.novalabsglobal.pharmacy.service.BrandService;
import com.novalabsglobal.pharmacy.service.OrderService;
import com.novalabsglobal.pharmacy.service.mapper.BrandMapper;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

@Service
@Transactional
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderRepo orderRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public String getReferenceNumber() {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
        Date date = new Date();
        String formattedDate = formatter.format(date);

        Random random = new Random();
        int randomNumber =random.nextInt(100001);

        String uniqueOrderNumber = "ORD" + formattedDate + randomNumber;

        return uniqueOrderNumber;
    }
}
