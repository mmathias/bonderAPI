package com.bonder.controller;

import com.bonder.domain.User;
import com.bonder.dto.UserDTO;
import com.bonder.resource.UserAssembler;
import com.bonder.service.UserService;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping(path = "users")
public class UserController {

    private final UserAssembler assembler;
    private final UserService userService;

    public UserController(UserAssembler assembler, UserService userService) {
        this.assembler = assembler;
        this.userService = userService;
    }

    @GetMapping("/{id}")
    public EntityModel<User> getUser(@PathVariable Long id) {
        User user = userService.get(id);

        return assembler.toModel(user);
    }

    @GetMapping
    public CollectionModel<EntityModel<User>> getUsers() {
        List<EntityModel<User>> rentals = userService.getAll().stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());

        return new CollectionModel<>(rentals,
                linkTo(methodOn(UserController.class).getUsers()).withSelfRel());
    }

    @PostMapping
    public ResponseEntity<EntityModel<User>> createUser(@RequestBody UserDTO userDTO) {
        User user = userService.createUser(userDTO);

        return ResponseEntity
                .created(linkTo(methodOn(UserController.class).getUser(user.getId())).toUri())
                .body(assembler.toModel(user));
    }

    @PutMapping("/{id}")
    public ResponseEntity<EntityModel<User>> updateUser(@PathVariable Long id, @RequestBody UserDTO userDTO) {
        User user = userService.update(id, userDTO);

        return ResponseEntity
                .created(linkTo(methodOn(UserController.class).getUser(id)).toUri())
                .body(assembler.toModel(user));
    }
}
