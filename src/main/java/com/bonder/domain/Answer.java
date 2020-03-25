package com.bonder.domain;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "answers")
public class Answer {

    private @Id @GeneratedValue Long id;

    @ManyToOne
    private Option option;
    @ManyToOne
    private Question question;
    @ManyToOne
    private User user;
    @ManyToOne
    private User challengedUser;
}
