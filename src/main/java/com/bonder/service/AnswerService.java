package com.bonder.service;

import com.bonder.domain.Answer;
import com.bonder.repository.AnswerRepository;
import org.springframework.stereotype.Component;

import java.util.BitSet;
import java.util.List;

@Component
public class AnswerService {

    private AnswerRepository answerRepository;

    public AnswerService(AnswerRepository answerRepository) {
        this.answerRepository = answerRepository;
    }

    public List<Answer> getAnswers() {
        return answerRepository.findAll();
    }

    public Answer getAnswer(Long id) {
        return answerRepository.getOne(id);
    }
}
