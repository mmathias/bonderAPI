package com.bonder.domain;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@Table(name = "questions")
public class Question {

    private @Id @GeneratedValue Long id;

    private String question;

    @OneToMany
    private List<Option> options;

}
