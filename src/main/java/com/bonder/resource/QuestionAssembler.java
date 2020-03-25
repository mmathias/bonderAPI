package com.bonder.resource;

import com.bonder.controller.QuestionController;
import com.bonder.domain.Question;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class QuestionAssembler implements RepresentationModelAssembler<Question, EntityModel<Question>> {

    @Override
    public EntityModel<Question> toModel(Question question) {
        return new EntityModel<>(
                question,
                WebMvcLinkBuilder.linkTo(methodOn(QuestionController.class).getQuestion(question.getId())).withSelfRel());
    }
}
