package com.example.webappagain.repository;

import com.example.webappagain.models.Tasks;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TasksRepo extends JpaRepository<Tasks, Long> {
    @Query("SELECT task FROM Tasks task WHERE task.executor_id = :workerId")
    List<Tasks> findByExecutor(Long workerId);

    @Query("SELECT task FROM Tasks task WHERE task.author_id = :employeeId")
    List<Tasks>findByAuthor(Long employeeId);

    @Query("SELECT task FROM Tasks task WHERE task.task_id = :taskID" +
            " AND task.executor_id = :workerId AND task.finaltime is null")
    Tasks findTaskByTaskIDExecutorID(Long taskID, Long workerId);

    @Query("SELECT task FROM Tasks task WHERE task.task_id = :taskID AND task.author_id = :authorID" +
            " AND task.finaltime is null")
    Tasks findByTaskIDAuthorID(Long taskID, Long authorID);
}
