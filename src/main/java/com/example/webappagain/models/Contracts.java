package com.example.webappagain.models;


import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Entity
@Data
@Table(name = "contracts", schema = "little_company")
@Getter
@Setter
public class Contracts {

    @Column
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long contract_id;

    @Column
    private Long task_id;

    @Column
    private String equipment_id;
}
