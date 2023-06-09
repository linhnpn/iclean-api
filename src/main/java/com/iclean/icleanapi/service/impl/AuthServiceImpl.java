package com.iclean.icleanapi.service.impl;

import com.iclean.icleanapi.config.JwtUtils;
import com.iclean.icleanapi.domain.UserDto;
import com.iclean.icleanapi.repository.RoleMapper;
import com.iclean.icleanapi.repository.UserMapper;
import com.iclean.icleanapi.domain.User;
import com.iclean.icleanapi.dto.*;
import com.iclean.icleanapi.service.interf.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AccountExpiredException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.sql.Date;

@Service
public class AuthServiceImpl implements AuthService {

    @Value("company.account.gmail.contact")
    private String companyEmail;

    PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private RoleMapper roleMapper;
    @Autowired
    private JwtUtils jwtUtils;
    @Autowired
    private AuthenticationManager authenticationManager;

    @Override
    public ResponseEntity<ResponseObject> login(LoginForm form) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(form.getUsername(), form.getPassword()));
            if (authentication != null) {
                UserPrinciple userPrinciple = (UserPrinciple) authentication.getPrincipal();
                String accessToken = jwtUtils.createToken(userPrinciple);
                UserDto account = userMapper.findUserDtoByUserName(form.getUsername());
                return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject(HttpStatus.OK.toString(), "Login success!", new JwtResponse(accessToken, account)));
            } else
                ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new ResponseObject(HttpStatus.UNAUTHORIZED.toString(), "Wrong username or password.", null));
        } catch (Exception e) {
            if (e instanceof DisabledException) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new ResponseObject(HttpStatus.UNAUTHORIZED.toString(), "Account has been locked. Please contact " + companyEmail + " for more information", null));
            } else if (e instanceof AccountExpiredException) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new ResponseObject(HttpStatus.UNAUTHORIZED.toString(), "The account has expired. Please contact " + companyEmail + " for more information", null));
            } else if (e instanceof AuthenticationException) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new ResponseObject(HttpStatus.UNAUTHORIZED.toString(), "Wrong username or password.", null));
            } else
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new ResponseObject(HttpStatus.UNAUTHORIZED.toString(), "Account is not NULL.", null));

        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new ResponseObject(HttpStatus.UNAUTHORIZED.toString(), "No username or password.", null));
    }

    @Override
    public ResponseEntity<ResponseObject> createAccount(NewAccountForm form) {
        if (userMapper.findUserByUserName(form.getUsername()) == null) {
            try {
                int role_id = roleMapper.findRoleByRoleName("renter").getRoleId();
                User user = new User();
                user.setUsername(form.getUsername());
                user.setPassword(passwordEncoder.encode(form.getPassword()));
                user.setFullname(form.getFullname());
                user.setDateOfBirth(Date.valueOf(form.getDateOfBirth()));
                user.setGender(form.getGender());
                user.setPhone(form.getPhone());
                user.setEmail(form.getEmail());
                user.setRole_id(role_id);

                userMapper.createUser(user);
                return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject(HttpStatus.OK.toString(), "Register success!", null));
            } catch (Exception e) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseObject(HttpStatus.BAD_REQUEST.toString(), "Something wrong occur.", null));
            }
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseObject(HttpStatus.BAD_REQUEST.toString(), "Username is existed.", null));
    }

    @Override
    public ResponseEntity<ResponseObject> changePassword(ChangePasswordForm form) {
        User user = userMapper.findUserByUserName(form.getUser_name());
        if (user != null) {
            try {
                if (!form.getRe_new_password().equals(form.getNew_password())) {
                    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseObject(HttpStatus.BAD_REQUEST.toString(), "The password is not match.", null));
                }
                if (!passwordEncoder.matches(form.getOld_password(), user.getPassword())) {
                    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseObject(HttpStatus.BAD_REQUEST.toString(), "Password is not correct.", null));
                }
                userMapper.changePassword(form);
                return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject(HttpStatus.OK.toString(), "Change password success!", null));

            } catch (Exception e) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseObject(HttpStatus.BAD_REQUEST.toString(), "Something wrong occur.", null));
            }
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseObject(HttpStatus.NOT_FOUND.toString(), "Not found account.", null));
    }
}
