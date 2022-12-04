package com.example.webappagain.repository;
import com.example.webappagain.models.Clients;
import com.example.webappagain.models.Employee;
import jakarta.persistence.Index;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ClientsRepo extends CrudRepository<Clients, Long> {
   @Query("SELECT clients FROM Clients clients WHERE clients.name like %?1%")
    List<Clients> findByName(String name);

}
