package com.sebn.backend.security.user.sercvices;

import com.sebn.backend.dtos.UserDTO;
import com.sebn.backend.mappers.MapperImpl;
import com.sebn.backend.security.user.Role;
import com.sebn.backend.security.user.User;
import com.sebn.backend.security.user.repository.RoleRepository;
import com.sebn.backend.security.user.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class UserService {
    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private PasswordEncoder passwordEncoder;


    private MapperImpl mapper;

    public List<UserDTO> getUsers(){
        List<User> users = userRepository.findAll();
        return users.stream().map(mapper::toUserDTO).toList();
    }
    public UserDTO getUserById(Integer userId){
        User existingUser = userRepository.findById(userId).orElse(null);
        if (existingUser == null){
            throw new RuntimeException("User not found");
        }

        return mapper.toUserDTO(existingUser);
    }

    public UserDTO updateUser(Integer userId, UserDTO user){
        User existingUser = userRepository.findById(userId).orElse(null);
        Role existingRole = roleRepository.findByName(user.getRole());
        if (existingUser== null){
            throw new RuntimeException("User not exist !") ;
        }

        existingUser.setFirstName(user.getFirstName());
        existingUser.setLastName(user.getLastName());
        existingUser.setEmail(user.getEmail());
        existingUser.setRole(existingRole);
        existingUser.setPassword(passwordEncoder.encode(user.getPassword()));
        User updatedUser = userRepository.save(existingUser);

        return mapper.toUserDTO(updatedUser);

    }

    public void deleteUser(Integer userId){
        userRepository.deleteById(userId);
    }

    public List<UserDTO> searchUsers(String keyword) {
        List<User> users = userRepository.searchUsersByKeyword(keyword);
        return users.stream().map(mapper::toUserDTO).toList();
    }
}
