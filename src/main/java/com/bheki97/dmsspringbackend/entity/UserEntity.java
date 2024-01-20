package com.bheki97.dmsspringbackend.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "users")
@Inheritance(strategy = InheritanceType.JOINED)
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long userId;
    private String firstname;
    private String lastname;
    @Column(nullable = false,unique = true)
    private String email;
    private String password;
    @Column(name = "user_role")
    private String userRole;
    @Column(name = "cellphone_number")
    private String cellNo;

}
