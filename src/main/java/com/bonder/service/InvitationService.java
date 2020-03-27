package com.bonder.service;

import com.bonder.domain.Invitation;
import com.bonder.repository.InvitationRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class InvitationService {

    private InvitationRepository repository;

    public InvitationService(InvitationRepository repository) {
        this.repository = repository;
    }

    public Invitation getInvitation(Long id) {
        return repository.findById(id).orElseThrow(() -> new RuntimeException("Invitation not found"));
    }

    public List<Invitation> getInvitations() {
        return repository.findAll();
    }
}
