package com.example.webappagain.repository;

import com.example.webappagain.models.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface EmployeeRepo extends JpaRepository<Employee, Long> {
    @Query("SELECT emp FROM Employee emp WHERE emp.email = ?1")
    Employee findByEmail(String email);

    @Query("SELECT emp FROM  Employee emp WHERE emp.position = 'worker'")
    Iterable<Employee> findAllWorkers();
}
