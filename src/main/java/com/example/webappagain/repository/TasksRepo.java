package com.example.webappagain.repository;

import com.example.webappagain.models.Tasks;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface TasksRepo extends CrudRepository<Tasks, Long> {
    @Query("SELECT task FROM Tasks task WHERE task.executor_id = :workerId")
    Iterable<Tasks> findByExecutor(Long workerId);

    @Query("SELECT task FROM Tasks task WHERE task.author_id = :employeeId")
    Iterable<Tasks>findByAuthor(Long employeeId);
}
