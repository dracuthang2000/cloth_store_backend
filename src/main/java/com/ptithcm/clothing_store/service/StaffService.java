package com.ptithcm.clothing_store.service;

import com.ptithcm.clothing_store.model.entity.Staff;

import java.util.List;

public interface StaffService {
    Staff getStaffById(Long id);
    Staff findStaffByUsername(String username);
    List<Staff> findAllShipper(Long roleId);
}
