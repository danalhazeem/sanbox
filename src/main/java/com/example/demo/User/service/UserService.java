package com.example.demo.User.service;

import com.example.demo.User.UserEntity;
import com.example.demo.User.UserRepository;
import com.example.demo.User.bo.CreateUserRequest;
import com.example.demo.User.bo.UserResponse;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService  {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    public List<UserEntity> getAllUsers() {
        return userRepository.findAll();
    }


    public UserResponse createUser(CreateUserRequest request) {
        // Create a new UserEntity from the request
        UserEntity userEntity = new UserEntity();
        userEntity.setFirstName(request.getFirstName());
        userEntity.setLastName(request.getLastName());
        userEntity.setEmail(request.getEmail());
        userEntity.setPassword(request.getPassword());
        userEntity = userRepository.save(userEntity);

        UserResponse response = new UserResponse(userEntity.getId(),userEntity.getEmail(),userEntity.getFirstName(),userEntity.getLastName());

        return response;
    }


    public UserResponse updateUser(Long id, CreateUserRequest request) {
        // Find the user by id
        Optional<UserEntity> userOptional = userRepository.findById(id);

        if (userOptional.isPresent()) {
            UserEntity userEntity = userOptional.get();

            // Update fields
            if (request.getFirstName() != null) {
                userEntity.setFirstName(request.getFirstName());
            }
            if (request.getLastName() != null) {
                userEntity.setLastName(request.getLastName());
            }
            if (request.getEmail() != null) {
                userEntity.setEmail(request.getEmail());
            }
            if (request.getPassword() != null) {
                userEntity.setPassword(request.getPassword());
            }


            // Save the updated user
            userEntity = userRepository.save(userEntity);

            // Create and return UserResponse
            UserResponse response = new UserResponse(
                    userEntity.getId(),
                    userEntity.getEmail(),
                    userEntity.getFirstName(),
                    userEntity.getLastName()

            );

            return response;
        } else {
            // If user not found, you can handle it in various ways
            throw new IllegalArgumentException("User with ID " + id + " not found.");
        }
    }

    public UserResponse getUserById(Long id) {
        Optional<UserEntity> userOptional = userRepository.findById(id);

        if (userOptional.isPresent()) {
            UserEntity userEntity = userOptional.get();
            // Convert UserEntity to UserResponse
            return new UserResponse(
                    userEntity.getId(),
                    userEntity.getEmail(),
                    userEntity.getFirstName(),
                    userEntity.getLastName()

            );
        } else {
            throw new IllegalArgumentException("User with ID " + id + " not found.");
        }
    }

    public void deleteUserById(Long id) {
        // Check if the user exists
        Optional<UserEntity> userOptional = userRepository.findById(id);

        if (userOptional.isPresent()) {
            // User found, delete the user
            userRepository.deleteById(id);
        } else {
            throw new IllegalArgumentException("User with ID " + id + " not found.");
        }
    }


}

