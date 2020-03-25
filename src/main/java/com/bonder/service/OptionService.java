package com.bonder.service;

import com.bonder.domain.Option;
import com.bonder.repository.OptionRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class OptionService {

    private OptionRepository optionRepository;

    public OptionService(OptionRepository optionRepository) {
        this.optionRepository = optionRepository;
    }

    public List<Option> getOptions() {
        return optionRepository.findAll();
    }

    public Option getOption(Long id) {
        return optionRepository.getOne(id);
    }
}
