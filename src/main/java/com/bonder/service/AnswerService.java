package com.bonder.service;

import com.bonder.domain.Answer;
import com.bonder.repository.AnswerRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AnswerService {

    private AnswerRepository repository;

    public AnswerService(AnswerRepository repository) {
        this.repository = repository;
    }

    public List<Answer> getAnswers() {
        return repository.findAll();
    }

    public Answer getAnswer(Long id) {
        return repository.findById(id).orElseThrow(() -> new RuntimeException("Answer not found"));
    }
}
