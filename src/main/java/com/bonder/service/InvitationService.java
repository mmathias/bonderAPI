package com.bonder.service;

import com.bonder.domain.Invitation;
import com.bonder.dto.InvitationDTO;
import com.bonder.repository.InvitationRepository;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

@Component
public class InvitationService {

    private InvitationRepository repository;
    private final UserService userService;
    private final EventService eventService;

    public InvitationService(InvitationRepository repository, UserService userService, EventService eventService) {
        this.repository = repository;
        this.userService = userService;
        this.eventService = eventService;
    }

    public Invitation getInvitation(Long id) {
        return repository.findById(id).orElseThrow(() -> new RuntimeException("Invitation not found"));
    }

    public List<Invitation> getInvitations() {
        return repository.findAll();
    }

    public Invitation createInvitation(InvitationDTO invitationDTO) {
        Invitation newInvitation = new Invitation();
        newInvitation.setEvent(eventService.getEvent(invitationDTO.getEventId()));
        newInvitation.setInvitationDate(LocalDate.now());
        newInvitation.setInvited(userService.getUser(invitationDTO.getInvitedId()));
        newInvitation.setInviter(userService.getUser(invitationDTO.getInviterId()));

        return repository.save(newInvitation);
    }
}
