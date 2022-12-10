package com.example.webappagain.repository;

import com.example.webappagain.models.Tasks;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.List;


@Repository
@Transactional
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

    @Procedure("little_company.get_all_tasks")
    int getAllTasks(Integer ID, Timestamp startDate, Timestamp endDate);

    @Procedure("little_company.get_complete_tasks_in_time")
    int getCompleteTasksInTime(Integer ID, Timestamp startDate, Timestamp endDate);

    @Procedure("little_company.get_complete_tasks_not_in_time")
    int getCompleteTasksNoTime(Integer ID, Timestamp startDate, Timestamp endDate);

    @Procedure("little_company.get_in_progress_tasks")
    int getInProgressTasks(Integer ID, Timestamp startDate, Timestamp endDate);

    @Procedure("little_company.get_uncomplete_tasks")
    int getUncompletedTasks(Integer ID, Timestamp startDate, Timestamp endDate);
}
