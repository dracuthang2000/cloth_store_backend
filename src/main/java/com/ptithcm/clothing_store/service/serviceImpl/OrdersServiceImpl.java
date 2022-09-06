package com.ptithcm.clothing_store.service.serviceImpl;

import com.ptithcm.clothing_store.model.entity.Orders;
import com.ptithcm.clothing_store.model.exception.ModifyHandleException;
import com.ptithcm.clothing_store.model.exception.ResourceNotFoundException;
import com.ptithcm.clothing_store.repository.OrderRepository;
import com.ptithcm.clothing_store.service.OrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class OrdersServiceImpl implements OrdersService {

    @Autowired
    private OrderRepository orderRepository;

    @Override
    public String save(Orders orders) {
        try{
            orderRepository.save(orders);
        }catch (Exception e){
            throw new ModifyHandleException("insert or update is Error");
        }
        return null;
    }

    @Override
    public Orders findOrdersByBillId(Long id) {
        Orders orders = orderRepository.findByBillId(id);
        if(Objects.isNull(orders)){
            throw new ResourceNotFoundException("Can't found Orders");
        }
        return orders;
    }
}
