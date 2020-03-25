package com.bonder.domain;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Data
@Table(name = "events")
public class Event {

    private @Id @GeneratedValue Long id;

    private String name;
    private String description;
    @Column(name = "icon_url")
    private String iconURL;
    @Column(name = "image_url")
    private String imageURL;

    @Column(name = "invitation_date")
    private LocalDate invitationDate;
}
