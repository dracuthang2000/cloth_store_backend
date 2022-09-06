package com.ptithcm.clothing_store.web;

import com.ptithcm.clothing_store.model.Enum.EnumState;
import com.ptithcm.clothing_store.model.dto.UserDto;
import com.ptithcm.clothing_store.model.dto.order.UpdateStateProcessing;
import com.ptithcm.clothing_store.model.entity.Orders;
import com.ptithcm.clothing_store.model.entity.Staff;
import com.ptithcm.clothing_store.service.OrdersService;
import com.ptithcm.clothing_store.service.StaffService;
import com.ptithcm.clothing_store.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;

@RestController
@RequestMapping("order")
@CrossOrigin
public class OrdersController extends AbstractApplicationController {
    @Autowired
    OrdersService ordersService;
    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private StaffService staffService;

    @PutMapping("update-status-order")
    public ResponseEntity<Object> updateStatusProcessing(@RequestBody UpdateStateProcessing req, HttpServletRequest request){
        String requestTokenHeader = request.getHeader("Authorization");
        String data = jwtUtil.getUsernameFromToken(requestTokenHeader.substring(7));
        UserDto user = userMapper.jsonToUserDto(data);
        Orders orders = new Orders();
        orders = ordersService.findOrdersByBillId(req.getId());
        orders.setStaff(staffService.getStaffById(user.getId()));
        orders.setState(req.getState());
        if(req.getId_shipper()!=0){
            orders.setDriver(staffService.getStaffById(req.getId_shipper()));
        }
        return new ResponseEntity<>(ordersService.save(orders), HttpStatus.OK);
    }
    @GetMapping("update-status-finishing")
    public ResponseEntity<Object> updateStatusFinishing(@RequestParam("id_bill")Long id){
        Orders orders = ordersService.findOrdersByBillId(id);
        orders.setState(EnumState.FIN);
        orders.setEndDate(LocalDate.now());
        return new ResponseEntity<>(ordersService.save(orders), HttpStatus.OK);
    }
}
