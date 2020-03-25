package com.bonder.domain;

import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Data
@Table(name = "options")
public class Option {

    private @Id @GeneratedValue Long id;

    private String option;

    @ManyToOne
    private Question question;

}
