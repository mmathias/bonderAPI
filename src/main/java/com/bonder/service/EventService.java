package com.bonder.service;

import com.bonder.domain.Event;
import com.bonder.repository.EventRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class EventService {
    private EventRepository repository;

    public EventService(EventRepository repository) {
        this.repository = repository;
    }

    public List<Event> getEvents() {
        return repository.findAll();
    }

    public Event getEvent(Long id) {
        return repository.findById(id).orElseThrow(() -> new RuntimeException("Event not found"));
    }
}
