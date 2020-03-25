package com.bonder.resource;

import com.bonder.controller.InvitationController;
import com.bonder.controller.UserController;
import com.bonder.domain.Invitation;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class InvitationAssembler implements RepresentationModelAssembler<Invitation, EntityModel<Invitation>> {

    @Override
    public EntityModel<Invitation> toModel(Invitation invitation) {
        return new EntityModel<>(
                invitation,
                WebMvcLinkBuilder.linkTo(methodOn(InvitationController.class).getInvitation(invitation.getId())).withSelfRel(),
                WebMvcLinkBuilder.linkTo(methodOn(UserController.class).getUser(invitation.getInvited().getId())).withRel("invited"),
                WebMvcLinkBuilder.linkTo(methodOn(UserController.class).getUser(invitation.getInviter().getId())).withRel("inviter"));
    }
}
