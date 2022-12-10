package com.example.webappagain.component;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Component
@Getter
@Setter
public class TaskReport {

    private Long workerID;
    private int allTasks;
    private int completeTasks;
    private int completeNotInTimeTasks;
    private int inProgressTasks;
    private int unCompleteTasks;

    public TaskReport(){

    }
}
