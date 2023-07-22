package com.ivan.secureScaleApi.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "users")
public class User {
    @Id
    private Long userId;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private boolean isLocked;

    //roles

}
