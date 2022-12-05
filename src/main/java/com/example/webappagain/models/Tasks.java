package com.example.webappagain.models;


import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.sql.Timestamp;

@Entity
@Data
@Table(name = "tasks", schema = "little_company")
@Getter
@Setter
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
    private Timestamp finalTime;
}
