package com.ptithcm.clothing_store.service.serviceImpl;

import com.ptithcm.clothing_store.model.entity.Account;
import com.ptithcm.clothing_store.model.exception.UsernameNotFoundException;
import com.ptithcm.clothing_store.repository.AccountRepository;
import com.ptithcm.clothing_store.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;

    @Autowired
    public AccountServiceImpl(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public Account findById(String username) {
        return accountRepository.findById(username)
                .orElseThrow(() -> new UsernameNotFoundException("Username can't found"));
    }
}
