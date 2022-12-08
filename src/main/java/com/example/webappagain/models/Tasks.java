package com.example.webappagain.models;


import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;

@Entity
@Data
@Table(name = "tasks", schema = "little_company")
@Getter
@Setter
@NoArgsConstructor
public class Tasks {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long task_id;
    private Long customer_id;
    private Long author_id;
    private Long executor_id;
    private String goal;
    private String priority;
    private Timestamp creation;
    private Timestamp deadline;
    private Timestamp finaltime;

    public Tasks(Long customer_id, Long author_id, Long executor_id, String goal, String priority,
                 String deadline){
        this.customer_id = customer_id;
        this.author_id = author_id;
        this.executor_id = executor_id;
        this.goal = goal;
        this.priority = priority;
        this.creation = new Timestamp(System.currentTimeMillis());
        this.deadline = Timestamp.valueOf(deadline.replace('T', ' ') + ":00");
    }

}
