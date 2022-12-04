package com.example.webappagain.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Indexed;

@Entity
@Data
@Table(name = "clients", schema = "little_company")
@Getter
@Setter
public class Clients {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long personal_id;

    private String name;

    private String phone;

    private String email;

    private String post_address;

    private String client_type;
}
