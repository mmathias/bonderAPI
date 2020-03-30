package com.bonder.controller;

import com.bonder.domain.Event;
import com.bonder.dto.EventDTO;
import com.bonder.resource.EventAssembler;
import com.bonder.service.EventService;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping(path = "events")
public class EventController {

    private final EventAssembler assembler;
    private EventService service;

    public EventController(EventService service, EventAssembler assembler) {
        this.service = service;
        this.assembler = assembler;
    }

    @GetMapping
    public CollectionModel<EntityModel<Event>> getEvents() {
        List<EntityModel<Event>> events = service.getEvents().stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());

        return new CollectionModel<>(events,
                linkTo(methodOn(EventController.class).getEvents()).withSelfRel());
    }

    @GetMapping(path = "/{id}")
    public EntityModel<Event> getEvent(@PathVariable Long id) {
        Event event = service.getEvent(id);

        return assembler.toModel(event);
    }

    @PostMapping
    public EntityModel<Event> createEvent(@Valid @RequestBody EventDTO eventDTO) {
        Event event = service.createEvent(eventDTO);

        return assembler.toModel(event);
    }
}
