package com.bonder.domain;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Data
@Table(name = "user_login")
public class User {

    @Column(name = "user_id")
    private @Id @GeneratedValue Long id;

    @Column(name = "user_email")
    private String email;
    @Column(name = "user_password")
    private String password;
    @Column(name = "user_join_date")
    private LocalDate joinDate;
    private String name;
}
