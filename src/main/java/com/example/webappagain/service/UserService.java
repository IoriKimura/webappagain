package com.example.webappagain.service;

import com.example.webappagain.repository.EmployeeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserDetailsService{

    @Autowired
    private final EmployeeRepo eRepo;

    @Autowired
    public UserService(EmployeeRepo eRepo){
        this.eRepo = eRepo;
    }

    @Override
    public UserDetails loadUserByUsername(String email) {
        return eRepo.findByEmail(email);
    }
}
