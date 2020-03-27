package com.bonder.service;

import com.bonder.domain.Option;
import com.bonder.repository.OptionRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class OptionService {

    private OptionRepository repository;

    public OptionService(OptionRepository repository) {
        this.repository = repository;
    }

    public List<Option> getOptions() {
        return repository.findAll();
    }

    public Option getOption(Long id) {
        return repository.findById(id).orElseThrow(() -> new RuntimeException("Not found"));
    }
}
