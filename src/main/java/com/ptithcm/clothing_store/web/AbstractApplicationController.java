package com.ptithcm.clothing_store.web;

import com.ptithcm.clothing_store.util.ApplicationMapper;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class AbstractApplicationController {
    @Autowired
    ApplicationMapper mapper;
}
