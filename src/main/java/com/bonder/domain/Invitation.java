package com.bonder.domain;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Data
@Table(name = "invitations")
public class Invitation {

    private @Id @GeneratedValue Long id;

    @ManyToOne
    private Event event;
    @ManyToOne
    private User invited;
    @ManyToOne
    private User inviter;
    @Column(name = "invitation_date")
    private LocalDate invitationDate;
}
