package com.sebn.backend.security.user.controllers;

import com.sebn.backend.dtos.UserDTO;
import com.sebn.backend.security.user.User;
import com.sebn.backend.security.user.sercvices.UserService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("api/security/users")
@CrossOrigin("*")
public class UserController {
    private UserService userService;

    @GetMapping
    public List<UserDTO> getUsers(){
        return userService.getUsers();
    }

    @GetMapping("/{userId}")
    public UserDTO getUserById(@PathVariable Integer userId){
        return userService.getUserById(userId);
    }

    @PutMapping("/{userId}")
    public UserDTO updateUser(@PathVariable Integer userId, @RequestBody UserDTO user){
        return userService.updateUser(userId, user);
    }

    @GetMapping("/search")
    public List<UserDTO> searchUsers(@RequestParam String keyword) {
        return userService.searchUsers(keyword);
    }

    @DeleteMapping("/{userId}")
    public void deleteUser (@PathVariable Integer userId){
        userService.deleteUser(userId);
    }

}
