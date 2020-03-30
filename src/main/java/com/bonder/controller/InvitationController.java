package com.bonder.controller;

import com.bonder.domain.Invitation;
import com.bonder.dto.InvitationDTO;
import com.bonder.resource.InvitationAssembler;
import com.bonder.service.InvitationService;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping(path = "invitations")
public class InvitationController {

    private final InvitationAssembler assembler;
    private InvitationService service;

    public InvitationController(InvitationService service, InvitationAssembler assembler) {
        this.service = service;
        this.assembler = assembler;
    }

    @GetMapping
    public CollectionModel<EntityModel<Invitation>> getInvitations() {
        List<EntityModel<Invitation>> customers = service.getInvitations().stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());

        return new CollectionModel<>(customers,
                linkTo(methodOn(InvitationController.class).getInvitations()).withSelfRel());
    }

    @GetMapping(path = "/{id}")
    public EntityModel<Invitation> getInvitation(@PathVariable Long id) {
        Invitation invitation = service.getInvitation(id);

        return assembler.toModel(invitation);
    }

    @PostMapping
    public EntityModel<Invitation> createInvitation(@Valid @RequestBody InvitationDTO invitationDTO) {
        Invitation invitation = service.createInvitation(invitationDTO);

        return assembler.toModel(invitation);
    }
}
