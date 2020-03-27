package com.bonder.domain;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "options")
public class Option {

    private @Id @GeneratedValue Long id;

    private String description;

    @ManyToOne
    private Question question;
}
