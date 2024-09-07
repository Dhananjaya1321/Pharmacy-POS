package com.novalabsglobal.pharmacy.service.impl;

import com.novalabsglobal.pharmacy.dto.RoleDTO;
import com.novalabsglobal.pharmacy.entity.Role;
import com.novalabsglobal.pharmacy.repo.RoleRepo;
import com.novalabsglobal.pharmacy.service.RoleService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class RoleServiceImpl implements RoleService {
    @Autowired
    private RoleRepo roleRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<RoleDTO> getAllRoles() {
        List<Role> roles = roleRepo.findAll();
        List<RoleDTO> dtos = new ArrayList<>();
        for (Role r : roles) {
            RoleDTO dto = new RoleDTO();
            dto.setId(r.getId());
            dto.setName(r.getName());
            dto.setDescription(r.getDescription());

            dtos.add(dto);
        }
        return dtos;
    }
}
