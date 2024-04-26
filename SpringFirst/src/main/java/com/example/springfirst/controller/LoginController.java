package com.example.springfirst.controller;

import com.example.springfirst.Model.User;
import com.example.springfirst.Model.company;
import com.example.springfirst.Service.UserService;
import com.example.springfirst.Service.companyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class LoginController {

    @Autowired
    private UserService userService;

    @Autowired
    private companyService companyService;


    // get seeker by id

    @GetMapping("/userRead/{id}")
    public ResponseEntity<User> getUserReadById(@PathVariable Long id) {
        Optional<User> userReadOptional = userService. getUserReadById(id);


        if (userReadOptional.isPresent()) {
            return ResponseEntity.ok().body(userReadOptional.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }


// login for seekers

    @PostMapping("/login/seeker")
    public ResponseEntity<String> login(@RequestBody User loginModel) {
        boolean isAuthenticated = userService.authenticate(loginModel.getId(), loginModel.getPassword());

        System.out.println(loginModel.getId());
        System.out.println(loginModel.getPassword());

        if (isAuthenticated) {
            return ResponseEntity.ok().body("Welcome To the seeking!");
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Login failed");
        }
    }



// data insert for seeker

    @PostMapping("/insert/seeker")
    public ResponseEntity<String> insertData(@RequestBody User loginModel) {

        Optional<User> existingUser = userService.getUserReadById(loginModel.getId());
        if (existingUser.isPresent()) {
           // return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("ID already registered");
            return ResponseEntity.ok().body("ID already registered");
        }

       User savedModel = userService.save(loginModel);

        if (savedModel != null) {
            return ResponseEntity.ok().body("Registration successful");
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to insert data");
        }
    }
// update seeker details
    @PutMapping("/update/user")
    public ResponseEntity<String> updateUserProfile(@RequestBody User updatedUser) {
        User updatedUserProfile = userService.updateUser(updatedUser);
        if (updatedUserProfile != null) {
            return ResponseEntity.ok().body("User profile updated successfully");
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Delete User
    @DeleteMapping("/delete/users/{id}")
    public ResponseEntity<String> deleteUserById(@PathVariable("id") long id) {
        try {
            userService.deleteUserById(id);
            return ResponseEntity.ok("User deleted successfully");
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }


    ///------------------------------------------------------------ company parts ------------------------------------------------------

   // company login

    @PostMapping("/login/company")
    public ResponseEntity<String> login(@RequestBody company companyModel) {
        boolean isAuthenticated = companyService.authenticate(companyModel.getId(), companyModel.getPassword());

        System.out.println(companyModel.getId());
        System.out.println(companyModel.getPassword());

        if (isAuthenticated) {
            return ResponseEntity.ok().body("Welcome Company");
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Login failed");
        }
    }

    // company save

    @PostMapping("/insert/company")
    public ResponseEntity<String> insertData(@RequestBody company comMod) {

        Optional<company> existingUser = companyService.getCompanyReadById(comMod.getId());
        if (existingUser.isPresent()) {
            //return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("ID already registered");
            return ResponseEntity.ok().body("ID already registered");
        }


        company saveMod = companyService.save(comMod);

        if (saveMod != null) {
            return ResponseEntity.ok().body("Registration successful");
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to insert data");
        }
    }

    // get company by id

    @GetMapping("/companyRead/{id}")
    public ResponseEntity<company> getCompanyReadById(@PathVariable Long id) {
        Optional<company> comReadOptional = companyService. getCompanyReadById(id);


        if (comReadOptional.isPresent()) {
            return ResponseEntity.ok().body(comReadOptional.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Update company profile
    @PutMapping("/update/company")
    public ResponseEntity<String> updateCompanyProfile(@RequestBody company companyModel) {
        company updatedCompany = companyService.updateCompanyProfile(companyModel);
        if (updatedCompany != null) {
            return ResponseEntity.ok().body("Company profile updated successfully");
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Delete company profile by ID
    @DeleteMapping("/delete/company/{id}")
    public ResponseEntity<String> deleteCompanyProfile(@PathVariable Long id) {
        Optional<company> existingCompany = companyService.getCompanyReadById(id);
        if (existingCompany.isPresent()) {
            companyService.deleteCompany(id);
            return ResponseEntity.ok().body("Company profile deleted successfully");
        } else {
            return ResponseEntity.notFound().build();
        }
    }



}
