package com.example.webappagain.service;

import com.example.webappagain.models.Employee;
import com.example.webappagain.repository.EmployeeRepo;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

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
