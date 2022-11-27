package com.example.webappagain.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Entity
@Data
@Table(name = "employee", schema = "little_company")
@Getter
@Setter
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long employeeId;
    private String name;
    private String phone;
    private String email;
    private String position;
    private String nickname;
    private String password;
    private String salt;

//    @ElementCollection(targetClass = Role.class, fetch = FetchType.EAGER)
//    @CollectionTable(name = "user_role",
//            joinColumns = @JoinColumn(name = "employeeId"))
//    @Enumerated(EnumType.STRING)
//    private Set<Role> roles;
}
