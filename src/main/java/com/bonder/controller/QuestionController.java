package com.bonder.controller;

import com.bonder.domain.Question;
import com.bonder.dto.QuestionDTO;
import com.bonder.resource.QuestionAssembler;
import com.bonder.service.QuestionService;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping(path = "questions")
public class QuestionController {

    private final QuestionAssembler assembler;
    private final QuestionService service;

    public QuestionController(QuestionAssembler assembler, QuestionService service) {
        this.assembler = assembler;
        this.service = service;
    }

    @GetMapping
    public CollectionModel<EntityModel<Question>> getQuestions() {
        List<EntityModel<Question>> questions = service.getAll().stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());

        return new CollectionModel<>(questions,
                linkTo(methodOn(QuestionController.class).getQuestions()).withSelfRel());
    }

    @GetMapping("/{id}")
    public EntityModel<Question> getQuestion(@PathVariable Long id) {
        Question question = service.getQuestion(id);

        return assembler.toModel(question);
    }

    @PostMapping
    public ResponseEntity<EntityModel<Question>> createQuestion(@RequestBody QuestionDTO questionDTO) {
        Question question = service.createQuestion(questionDTO);

        return ResponseEntity
                .created(linkTo(methodOn(QuestionController.class).getQuestion(question.getId())).toUri())
                .body(assembler.toModel(question));
    }

    @PutMapping("/{id}")
    public ResponseEntity<EntityModel<Question>> updateQuestion(@PathVariable Long id, @RequestBody QuestionDTO questionDTO) {
        Question question = service.update(id, questionDTO);

        return ResponseEntity
                .created(linkTo(methodOn(QuestionController.class).getQuestion(id)).toUri())
                .body(assembler.toModel(question));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteQuestion(@PathVariable Long id) {
        service.delete(id);

        return new ResponseEntity<>(id, HttpStatus.OK);
    }
}
