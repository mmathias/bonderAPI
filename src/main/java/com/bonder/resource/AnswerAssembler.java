package com.bonder.resource;

import com.bonder.controller.AnswerController;
import com.bonder.domain.Answer;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class AnswerAssembler implements RepresentationModelAssembler<Answer, EntityModel<Answer>> {

    @Override
    public EntityModel<Answer> toModel(Answer answer) {
        return new EntityModel<>(
                answer,
                WebMvcLinkBuilder.linkTo(methodOn(AnswerController.class).getAnswer(answer.getId())).withSelfRel());
    }
}
