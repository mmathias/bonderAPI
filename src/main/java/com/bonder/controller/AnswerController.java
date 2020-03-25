package com.bonder.controller;

import com.bonder.domain.Answer;
import com.bonder.resource.AnswerAssembler;
import com.bonder.service.AnswerService;
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
@RequestMapping(path = "answers")
public class AnswerController {

    private final AnswerAssembler assembler;
    private AnswerService service;

    public AnswerController(AnswerService service, AnswerAssembler assembler) {
        this.service = service;
        this.assembler = assembler;
    }

    @GetMapping
    public CollectionModel<EntityModel<Answer>> getAnswers() {
        List<EntityModel<Answer>> customers = service.getAnswers().stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());

        return new CollectionModel<>(customers,
                linkTo(methodOn(AnswerController.class).getAnswers()).withSelfRel());
    }

    @GetMapping(path = "/{id}")
    public EntityModel<Answer> getAnswer(@PathVariable Long id) {
        Answer answer = service.getAnswer(id);

        return assembler.toModel(answer);
    }
}
