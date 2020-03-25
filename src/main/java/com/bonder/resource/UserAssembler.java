package com.bonder.resource;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import com.bonder.controller.UserController;
import com.bonder.domain.User;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Component;

@Component
public class UserAssembler implements RepresentationModelAssembler<User, EntityModel<User>> {

    @Override
    public EntityModel<User> toModel(User user) {
        return new EntityModel<>(
                user,
                WebMvcLinkBuilder.linkTo(methodOn(UserController.class).getUser(user.getId())).withSelfRel());
    }
}
