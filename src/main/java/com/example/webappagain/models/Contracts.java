package com.example.webappagain.models;


import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Data
@Table(name = "contracts", schema = "little_company")
@Getter
@Setter
@NoArgsConstructor
public class Contracts {

    @Column
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long contract_id;

    @Column
    private Long task_id;

    @Column
    private String equipment_id;

    public Contracts (Long task_id, String equipmentID){
       this.task_id = task_id;
       this.equipment_id = equipmentID;
    }
}
