package com.example.webappagain.component;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Component
@Getter
@Setter
@NoArgsConstructor
public class TaskReport {

    private int workerID;
    private int allTasks;
    private int completeInTimeTasks;
    private int completeNotInTimeTasks;
    private int inProgressTasks;
    private int unCompleteTasks;

    public TaskReport(int workerID, int allTasks,
                      int completeTasks, int completeNotInTimeTasks,
                      int inProgressTasks, int unCompleteTasks) {
        this.workerID = workerID;
        this.allTasks = allTasks;
        this.completeInTimeTasks = completeTasks;
        this.completeNotInTimeTasks = completeNotInTimeTasks;
        this.inProgressTasks = inProgressTasks;
        this.unCompleteTasks = unCompleteTasks;
    }
}
