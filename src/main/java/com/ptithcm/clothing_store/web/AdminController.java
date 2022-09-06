package com.ptithcm.clothing_store.web;

import com.ptithcm.clothing_store.model.dto.JwtRequest;
import com.ptithcm.clothing_store.model.dto.UserDto;
import com.ptithcm.clothing_store.security.CustomAuthenticationManager;
import com.ptithcm.clothing_store.security.jwt.JWTResponse;
import com.ptithcm.clothing_store.service.AccountService;
import com.ptithcm.clothing_store.service.CustomerService;
import com.ptithcm.clothing_store.service.StaffService;
import com.ptithcm.clothing_store.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/admin/")
public class AdminController extends AbstractApplicationController{
    @Autowired
    StaffService staffService;
    @Autowired
    private CustomAuthenticationManager authenticationManager;
    @Autowired
    private AccountService accountService;
    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("login")
    public ResponseEntity<?> generateJWT(@RequestBody JwtRequest jwtRequest) {
        final Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(jwtRequest.getUsername(), jwtRequest.getPassword()));

        // if authentication succeeded and is not anonymous
        if (authentication != null && !(authentication instanceof AnonymousAuthenticationToken) && authentication.isAuthenticated()) {

            // set authentication in security context holder
            SecurityContextHolder.getContext().setAuthentication(authentication);
            UserDto userDto = userMapper.mapperUser(staffService.findStaffByUsername(jwtRequest.getUsername()));


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
                userMapper.mapperUser(staffService.getStaffById(user.getId()))
                , HttpStatus.OK);
    }
    @GetMapping("get_list_staff_by_role_id")
    public ResponseEntity<Object> getListAllStaff(@Param("id_role")Long idRole){
        return new ResponseEntity<>(
                staffService.findAllShipper(idRole).stream()
                .map(userMapper::mapperUser)
                .collect(Collectors.toList()),HttpStatus.OK
        );
    }
}
