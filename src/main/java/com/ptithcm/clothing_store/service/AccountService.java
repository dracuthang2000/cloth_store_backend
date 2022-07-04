package com.ptithcm.clothing_store.service;

import com.ptithcm.clothing_store.model.entity.Account;

public interface AccountService {
    public Account findById(String username);
}
