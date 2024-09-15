package com.novalabsglobal.pharmacy.service.impl;

import com.novalabsglobal.pharmacy.dto.BrandDTO;
import com.novalabsglobal.pharmacy.dto.BrandResponseDTO;
import com.novalabsglobal.pharmacy.entity.Brand;
import com.novalabsglobal.pharmacy.enums.BrandStatus;
import com.novalabsglobal.pharmacy.repo.BrandRepo;
import com.novalabsglobal.pharmacy.repo.ItemRepo;
import com.novalabsglobal.pharmacy.service.BrandService;
import com.novalabsglobal.pharmacy.service.mapper.BrandMapper;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class BrandServiceImpl implements BrandService {
    @Autowired
    private BrandRepo brandRepo;

    @Autowired
    private ItemRepo itemRepo;

    @Autowired
    private ModelMapper modelMapper;


    @Override
    public BrandDTO saveOrUpdateBrand(BrandDTO dto) {
        if (dto.getName() == null)
            throw new RuntimeException("The brand name is mandatory");

        if (dto.getId() == null || dto.getId() == 0) {
            dto.setStatus(BrandStatus.ACTIVE);
        } else {
            if (!brandRepo.existsById(dto.getId()) || brandRepo.getStatus(dto.getId()).equals(BrandStatus.DELETED))
                throw new RuntimeException("Brand is not exists!");

            dto.setStatus(brandRepo.getStatus(dto.getId()));
        }
        Brand brand = brandRepo.save(modelMapper.map(dto, Brand.class));

        dto.setId(brand.getId());
        return dto;
    }

    @Override
    public boolean deleteBrand(Integer id) {
        if (!brandRepo.existsById(id) || brandRepo.getStatus(id).equals(BrandStatus.DELETED))
            throw new RuntimeException("Brand is not exists!");

        if(itemRepo.getCountItemsUnderBrandByBrandId(id)>0)
            throw new RuntimeException("This brand cannot be deleted. Some items are under brand names");

        return brandRepo.deleteBrand(id) > 0;
    }


    @Override
    public Page<BrandResponseDTO> getAllBrands(Integer page, Integer size) {
        PageRequest pageRequest = (page == null && size == null) ? null : PageRequest.of(page, size);
        return new BrandMapper().brandEntitiesToDTOs(brandRepo.getAllBrands(pageRequest));
    }

    @Override
    public BrandResponseDTO getBrandById(Integer id) {
        if (!brandRepo.existsById(id) || brandRepo.getStatus(id).equals(BrandStatus.DELETED))
            throw new RuntimeException("Brand is not exists!");

        return new BrandMapper().brandEntityToDTO(brandRepo.getBrandById(id));
    }
}
