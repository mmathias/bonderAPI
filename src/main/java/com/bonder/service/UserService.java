package com.bonder.service;

import com.bonder.domain.User;
import com.bonder.dto.UserDTO;
import com.bonder.repository.UserRepository;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

@Component
public class UserService {

    private UserRepository repository;

    public UserService(
            UserRepository repository) {
        this.repository = repository;
    }

    public User get(Long id) {
        return repository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
    }

    public List<User> getAll() {
        return repository.findAll();
    }

    public User createUser(UserDTO userDTO) {
        User newUser = new User();
        newUser.setEmail(userDTO.getEmail());
        newUser.setJoinDate(LocalDate.now());
        newUser.setName(userDTO.getName());
        newUser.setPassword(userDTO.getPassword());

        return repository.save(newUser);
    }

    public User update(Long id, UserDTO userDTO) {
        //TODO
        return null;
    }
}
