package com.bonder.controller;

import com.bonder.domain.User;
import com.bonder.dto.UserDTO;
import com.bonder.resource.UserAssembler;
import com.bonder.service.UserService;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping(path = "users")
public class UserController {

    private final UserAssembler assembler;
    private final UserService service;

    public UserController(UserAssembler assembler, UserService service) {
        this.assembler = assembler;
        this.service = service;
    }

    @GetMapping("/{id}")
    public EntityModel<User> getUser(@PathVariable Long id) {
        User user = service.getUser(id);

        return assembler.toModel(user);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteUser(@PathVariable Long id) {
        service.deleteUser(id);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping
    public CollectionModel<EntityModel<User>> getUsers() {
        List<EntityModel<User>> rentals = service.getAll().stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());

        return new CollectionModel<>(rentals,
                linkTo(methodOn(UserController.class).getUsers()).withSelfRel());
    }

    @PostMapping
    public ResponseEntity<EntityModel<User>> createUser(@Valid @RequestBody UserDTO userDTO) {
        User user = service.createUser(userDTO);

        return ResponseEntity
                .created(linkTo(methodOn(UserController.class).getUser(user.getId())).toUri())
                .body(assembler.toModel(user));
    }

    @PutMapping("/{id}")
    public ResponseEntity<EntityModel<User>> updateUser(@PathVariable Long id, @Valid @RequestBody UserDTO userDTO) {
        User user = service.update(id, userDTO);

        return ResponseEntity
                .created(linkTo(methodOn(UserController.class).getUser(id)).toUri())
                .body(assembler.toModel(user));
    }
}
