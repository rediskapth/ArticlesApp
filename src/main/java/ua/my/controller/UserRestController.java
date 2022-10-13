package ua.my.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import ua.my.exception.UserAlreadyExistsException;
import ua.my.model.UserRole;
import ua.my.model.dto.UserDto;
import ua.my.service.UserService;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(path = "/users")
public class UserRestController {
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserRestController(UserService userService, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping(path = "/registration")
    public ResponseEntity<?> registerUser(@RequestBody UserDto userDto) {
        try {
            userDto.setUserRole(UserRole.ROLE_USER);
            userDto.setPassword(passwordEncoder.encode(userDto.getPassword()));
            userService.saveOrUpdate(userDto);
        } catch (UserAlreadyExistsException ex) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<UserDto>> findAllUsers() {
        List<UserDto> users = userService.findAllUsers();
        return users != null && !users.isEmpty()
                ? new ResponseEntity<>(users, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping
    public ResponseEntity<?> createUser(@RequestBody UserDto userDto) {
        try {
            userDto.setPassword(passwordEncoder.encode(userDto.getPassword()));
            userDto.setUserRole(UserRole.ROLE_USER);
            userService.saveOrUpdate(userDto);
        } catch (UserAlreadyExistsException ex) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<?> updateUser(@PathVariable("id") UUID id, @RequestBody UserDto userDto) {
        try {
            UserDto userToUpdate = userService.findUserById(id);
            if (userDto.getUsername() == null) {
                userToUpdate.setUsername(userToUpdate.getUsername());
            } else {
                userToUpdate.setUsername(userDto.getUsername());
            }
            if (userDto.getPassword() == null) {
                userToUpdate.setPassword(userToUpdate.getPassword());
            } else {
                userToUpdate.setPassword(passwordEncoder.encode(userDto.getPassword()));
            }
            if (userDto.getUserRole() == null) {
                userToUpdate.setUserRole(userToUpdate.getUserRole());
            } else {
                userToUpdate.setUserRole(userDto.getUserRole());
            }
            userToUpdate.setId(userToUpdate.getId());
            if (userDto.getUsername().equals("adminadmin")) {
                throw new Exception("User adminadmin is superadmin. You can't update it.");
            }
            if (userToUpdate.getUsername().equals(userDto.getUsername())) {
                userService.delete(userDto);
            }
            userService.saveOrUpdate(userToUpdate);
        } catch (Exception ex) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable("id") UUID id) {
        UserDto userDto = userService.findUserById(id);
        try {
            if (userDto.getUsername().equals("adminadmin")) {
                throw new Exception();
            }
            userService.delete(userDto);
        } catch (Exception ex) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
