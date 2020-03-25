package com.bonder.controller;

import com.bonder.domain.Option;
import com.bonder.resource.OptionAssembler;
import com.bonder.service.OptionService;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping(path = "options")
public class OptionController {

    private final OptionAssembler assembler;
    private OptionService service;

    public OptionController(OptionService service, OptionAssembler assembler) {
        this.service = service;
        this.assembler = assembler;
    }

    @GetMapping
    public CollectionModel<EntityModel<Option>> getOptions() {
        List<EntityModel<Option>> customers = service.getOptions().stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());

        return new CollectionModel<>(customers,
                linkTo(methodOn(OptionController.class).getOptions()).withSelfRel());
    }

    @GetMapping(path = "/{id}")
    public EntityModel<Option> getOption(@PathVariable Long id) {
        Option option = service.getOption(id);

        return assembler.toModel(option);
    }
}
