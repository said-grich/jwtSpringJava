package com.example.jwtSpringJava.Api;

import com.example.jwtSpringJava.entity.Role;
import com.example.jwtSpringJava.entity.User;
import com.example.jwtSpringJava.service.UserService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/userControl")
public class UserController {
  private final UserService userService;

  @PostMapping("/user/save")
  public ResponseEntity<User> saveUser(@RequestBody User user) {
    URI uri =
        URI.create(
            ServletUriComponentsBuilder.fromCurrentContextPath().path("/ap/user/save").toString());
    return ResponseEntity.created(uri).body(userService.saveUser(user));
  }

  @PostMapping("/role/save")
  public ResponseEntity<Role> saveRole(@RequestBody Role role) {
    URI uri =
        URI.create(
            ServletUriComponentsBuilder.fromCurrentContextPath().path("/ap/role/save").toString());

    return ResponseEntity.ok().body(userService.saveRole(role));
  }

  @GetMapping("/user/{username}")
  public ResponseEntity<User> getUser(@PathVariable("username") String username) {
    return ResponseEntity.ok().body(userService.getUser(username));
  }

  @GetMapping("/users")
  public ResponseEntity<List<User>> getUsers() {
    return ResponseEntity.ok().body(userService.getUsers());
  }

  @PutMapping("")
  public ResponseEntity<?> addRoleToUser(@RequestBody UserAndRole userAndRole) {
    userService.addRoleToUser(userAndRole.username, userAndRole.roleName);
    return ResponseEntity.ok().build();
  }

  @PutMapping("/changePassword")
  public int changePassword(@RequestBody UserPassword userPassword) {
    return userService.changePassword(
        userPassword.username, userPassword.passwordOld, userPassword.passwordNew);
  }

  @Data
  class UserAndRole {
    String username;
    String roleName;
  }
}
