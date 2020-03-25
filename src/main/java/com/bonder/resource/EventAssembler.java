package com.bonder.resource;

import com.bonder.controller.EventController;
import com.bonder.domain.Event;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class EventAssembler implements RepresentationModelAssembler<Event, EntityModel<Event>> {

    @Override
    public EntityModel<Event> toModel(Event event) {
        return new EntityModel<>(
                event,
                WebMvcLinkBuilder.linkTo(methodOn(EventController.class).getEvent(event.getId())).withSelfRel());
    }
}
