package com.example.webappagain.repository;
import com.example.webappagain.models.Clients;
import com.example.webappagain.models.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ClientsRepo extends JpaRepository<Clients, Long> {

}
