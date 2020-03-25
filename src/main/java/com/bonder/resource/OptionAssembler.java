package com.bonder.resource;

import com.bonder.controller.OptionController;
import com.bonder.domain.Option;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class OptionAssembler implements RepresentationModelAssembler<Option, EntityModel<Option>> {

    @Override
    public EntityModel<Option> toModel(Option option) {
        return new EntityModel<>(
                option,
                WebMvcLinkBuilder.linkTo(methodOn(OptionController.class).getOption(option.getId())).withSelfRel());
    }
}
