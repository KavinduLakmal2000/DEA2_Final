package com.example.springfirst.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.springfirst.Model.User;
public interface UserRepository extends JpaRepository<User, Long> {
}
