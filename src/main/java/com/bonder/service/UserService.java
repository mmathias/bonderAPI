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

    public UserService(UserRepository repository) {
        this.repository = repository;
    }

    public User getUser(Long id) {
        return repository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
    }

    public List<User> getAll() {
        return repository.findAll();
    }

    public User createUser(UserDTO userDTO) {
        User newUser = new User();
        setUserProperties(newUser, userDTO);
        newUser.setJoinDate(LocalDate.now());

        return repository.save(newUser);
    }

    public User update(Long id, UserDTO userDTO) {
        User userToUpdate = getUser(id);
        setUserProperties(userToUpdate, userDTO);

        return repository.save(userToUpdate);
    }

    private void setUserProperties(User user, UserDTO userDTO) {
        user.setEmail(userDTO.getEmail());
        user.setName(userDTO.getName());
        user.setPassword(userDTO.getPassword());
    }

    public void deleteUser(Long id) {
        repository.deleteById(id);
    }
}
