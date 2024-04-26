package com.example.springfirst.Service;

import org.springframework.stereotype.Service;

import com.example.springfirst.Model.User;
import com.example.springfirst.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;


    // save data to seekers
    public User save(User user) {
        return userRepository.save(user);
    }


    // login for seekers
    public boolean authenticate(Long id, String password) {
        Optional<User> optionalUser = userRepository.findById(id);
        return optionalUser.isPresent() && optionalUser.get().getPassword().equals(password);
    }


    // check seeker by id
    public Optional<User> getUserReadById(Long id) {
        return userRepository.findById(id);
    }

    // update user
    public User updateUser(User updatedUser) {
        User existingUser = userRepository.findById(updatedUser.getId()).orElse(null);
        if (existingUser != null) {
            existingUser.setUsername(updatedUser.getUsername());
            existingUser.setEmail(updatedUser.getEmail());
            existingUser.setPassword(updatedUser.getPassword());
            existingUser.setPhone(updatedUser.getPhone());
            existingUser.setFirstName(updatedUser.getFirstName());
            existingUser.setLastName(updatedUser.getLastName());
            existingUser.setSalaryExpectations(updatedUser.getSalaryExpectations());
            existingUser.setAvailability(updatedUser.getAvailability());
            return userRepository.save(existingUser);
        } else {

            return null;
        }
    }


    // delete user
    public void deleteUserById(long id) {
        Optional<User> userOptional = userRepository.findById(id);
        if (userOptional.isPresent()) {
            userRepository.deleteById(id);
        } else {
            throw new RuntimeException("User not found with id: " + id);
        }
    }

}
