package com.ptithcm.clothing_store.service.serviceImpl;

import com.ptithcm.clothing_store.model.entity.Staff;
import com.ptithcm.clothing_store.model.exception.ResourceNotFoundException;
import com.ptithcm.clothing_store.repository.StaffRepository;
import com.ptithcm.clothing_store.service.StaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class StaffServiceImpl implements StaffService {

    @Autowired
    StaffRepository staffRepository;

    @Override
    public Staff getStaffById(Long id) {
        return staffRepository.findById(id).orElseThrow(()->{
            throw new ResourceNotFoundException("Can't found Staff");
        });
    }

    @Override
    public Staff findStaffByUsername(String username) {
        Staff staff = staffRepository.findByAccountStaff_Username(username);
        if(Objects.isNull(staff)){
            throw new ResourceNotFoundException("Can't found staff by username");
        }
        return staff;
    }

    @Override
    public List<Staff> findAllShipper(Long roleId) {
        return staffRepository.findAllByAccountStaff_RoleId(roleId);
    }
}
