package com.bonder.service;

import com.bonder.domain.Question;
import com.bonder.dto.QuestionDTO;
import com.bonder.repository.QuestionRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class QuestionService {
    private QuestionRepository questionRepository;

    public QuestionService(QuestionRepository questionRepository) {
        this.questionRepository = questionRepository;
    }

    public Question getQuestion(Long id) {
        return questionRepository.getOne(id);
    }

    public List<Question> getAll() {
        return questionRepository.findAll();
    }

    public Question createQuestion(QuestionDTO questionDTO) {
        Question newQuestion = new Question();
        newQuestion.setQuestion(questionDTO.getQuestion());

        return questionRepository.save(newQuestion);
    }

    public Question update(Long id, QuestionDTO questionDTO) {
        Question question = questionRepository.findById(id).orElseThrow(() -> new RuntimeException("Couldn't find question with id " + id));
        question.setQuestion(questionDTO.getQuestion());

        return questionRepository.save(question);
    }

    public void delete(Long id) {
        questionRepository.deleteById(id);
    }
}
