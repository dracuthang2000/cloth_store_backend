package com.ptithcm.clothing_store.web;

import com.ptithcm.clothing_store.model.dto.PersonDto;
import com.ptithcm.clothing_store.model.dto.JwtRequest;
import com.ptithcm.clothing_store.model.dto.PersonUpdateDto;
import com.ptithcm.clothing_store.model.dto.UserDto;
import com.ptithcm.clothing_store.model.entity.Customer;
import com.ptithcm.clothing_store.security.CustomAuthenticationManager;
import com.ptithcm.clothing_store.security.jwt.JWTResponse;
import com.ptithcm.clothing_store.service.AccountService;
import com.ptithcm.clothing_store.service.CustomerService;
import com.ptithcm.clothing_store.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/customer/")
public class CustomerController extends AbstractApplicationController {
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
            UserDto userDto = mapper.mapperUser(customerService.findCustomerByUsername(jwtRequest.getUsername()));


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
        UserDto user = mapper.jsonToUserDto(data);
        return new ResponseEntity<Object>(
                mapper.mapperUser(customerService.findCustomerById(user.getId()))
                , HttpStatus.OK);
    }

    @GetMapping("test2")
    public String get1() throws InterruptedException {
        UserDto result = count;
        if (result == null) {
            synchronized (this) {
                System.out.println("check");
                result = count;
                Thread.sleep(10000);
                if (result == null) {
                    count = result = new UserDto();
                }
            }
        }
        return count.toString();
    }

    @PostMapping("create")
    public String createCustomer(@RequestBody PersonDto personDto) {
        personDto.setId(0l);
        Customer customer = mapper.personDtoToCustomer(personDto);
        return customerService.save(customer);
    }

    @PutMapping("update-customer/{id}")
    public String updateCustomer(@PathVariable("id") Long id, @RequestBody PersonUpdateDto personDto) {
        personDto.setId(id);
        Customer customer = mapper.personUpdateDtoToCustomer(personDto);
        customer.setAccountCustomer(accountService.findById(personDto.getUsername()));
        return customerService.save(customer);
    }
}
