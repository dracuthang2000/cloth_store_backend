package com.ptithcm.clothing_store.web;

import com.ptithcm.clothing_store.model.dto.CustomerDto;
import com.ptithcm.clothing_store.model.dto.JwtRequest;
import com.ptithcm.clothing_store.model.dto.CustomerUpdateDto;
import com.ptithcm.clothing_store.model.dto.UserDto;
import com.ptithcm.clothing_store.model.entity.Customer;
import com.ptithcm.clothing_store.model.exception.ResourceNotFoundException;
import com.ptithcm.clothing_store.security.CustomAuthenticationManager;
import com.ptithcm.clothing_store.security.jwt.JWTResponse;
import com.ptithcm.clothing_store.service.AccountService;
import com.ptithcm.clothing_store.service.BillService;
import com.ptithcm.clothing_store.service.CustomerService;
import com.ptithcm.clothing_store.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/customer/")
public class CustomerController extends AbstractApplicationController {
    @Autowired
    private BillService billService;
    private volatile UserDto count = null;
    @Autowired
    private CustomAuthenticationManager authenticationManager;
    @Autowired
    private AccountService accountService;
    @Autowired
    private CustomerService customerService;
    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("login")
    public ResponseEntity<?> generateJWT(@RequestBody JwtRequest jwtRequest) {
        final Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(jwtRequest.getUsername(), jwtRequest.getPassword()));

        // if authentication succeeded and is not anonymous
        if (authentication != null && !(authentication instanceof AnonymousAuthenticationToken) && authentication.isAuthenticated()) {

            // set authentication in security context holder
            SecurityContextHolder.getContext().setAuthentication(authentication);
            UserDto userDto = userMapper.mapperUser(customerService.findPersonByUsername(jwtRequest.getUsername()));


            // generate new JWT token
            final String jwtToken = jwtUtil.generateToken(userDto);

            // return response containing the JWT token
            return new ResponseEntity<>(new JWTResponse(jwtToken), HttpStatus.OK);
        }

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();

    }

    @GetMapping("information/me")
    public ResponseEntity<Object> getInfoCustomer(HttpServletRequest request) {
        String requestTokenHeader = request.getHeader("Authorization");
        String data = jwtUtil.getUsernameFromToken(requestTokenHeader.substring(7));
        UserDto user = userMapper.jsonToUserDto(data);
        return new ResponseEntity<Object>(
                userMapper.mapperUser(customerService.findCustomerById(user.getId()))
                , HttpStatus.OK);
    }
    @GetMapping("bill/bill-by-me")
    public ResponseEntity<Object> getBillByMySelf(HttpServletRequest request){
        String requestTokenHeader = request.getHeader("Authorization");
        String data = jwtUtil.getUsernameFromToken(requestTokenHeader.substring(7));
        UserDto user = userMapper.jsonToUserDto(data);
        return new ResponseEntity<>(billService.findByCustomerId(user.getId())
                .stream()
                .map(billMapper::billToBillDto)
                .collect(Collectors.toList()), HttpStatus.OK);
    }
    @GetMapping(value = "image/{image}")
    public ResponseEntity<Object> loadImageProduct(@PathVariable("image")String image){
        try{
            var imgFile = new ClassPathResource("image/"+image);
            return ResponseEntity
                    .ok()
                    .contentType(MediaType.IMAGE_JPEG)
                    .body(new InputStreamResource(imgFile.getInputStream()));
        }catch (IOException e){
            throw new ResourceNotFoundException("Image can't found");
        }
    }

    @PostMapping("create")
    public String createCustomer(@RequestBody CustomerDto customerDto) {
        customerDto.setId(0l);
        Customer customer = customerMapper.customerDtoToCustomer(customerDto);
        return customerService.save(customer);
    }

    @PutMapping("update-customer/{id}")
    public String updateCustomer(@PathVariable("id") Long id, @RequestBody CustomerUpdateDto personDto) {
        personDto.setId(id);
        Customer customer = customerMapper.customerUpdateToCustomer(personDto);
        customer.setAccountCustomer(accountService.findById(personDto.getUsername()));
        return customerService.save(customer);
    }
}
