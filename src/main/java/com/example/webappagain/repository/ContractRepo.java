package com.example.webappagain.repository;

import com.example.webappagain.models.Contracts;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ContractRepo extends JpaRepository<Contracts, Long> {
    @Query("SELECT contract from Contracts contract WHERE contract.task_id = :taskID")
    Contracts findByTaskId(Long taskID);
}
