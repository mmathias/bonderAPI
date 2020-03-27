package com.bonder.service;

import com.bonder.domain.Question;
import com.bonder.dto.QuestionDTO;
import com.bonder.repository.QuestionRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class QuestionService {
    private QuestionRepository repository;

    public QuestionService(QuestionRepository repository) {
        this.repository = repository;
    }

    public Question getQuestion(Long id) {
        return repository.findById(id).orElseThrow(() -> new RuntimeException("Question not found"));
    }

    public List<Question> getAll() {
        return repository.findAll();
    }

    public Question createQuestion(QuestionDTO questionDTO) {
        Question newQuestion = new Question();
        newQuestion.setQuestion(questionDTO.getQuestion());

        return repository.save(newQuestion);
    }

    public Question update(Long id, QuestionDTO questionDTO) {
        Question question = repository.findById(id).orElseThrow(() -> new RuntimeException("Couldn't find question with id " + id));
        question.setQuestion(questionDTO.getQuestion());

        return repository.save(question);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}
