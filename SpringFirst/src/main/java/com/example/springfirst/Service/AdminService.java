package com.example.springfirst.Service;

import com.example.springfirst.Model.company;
import com.example.springfirst.Model.User;
import com.example.springfirst.Model.Hire;
import com.example.springfirst.repository.CompanyRepository;
import com.example.springfirst.repository.HireRepository;
import com.example.springfirst.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private HireRepository repo;

    @Autowired
    private CompanyRepository companyRepository;

// ----------------------------------------------------- for company ------------------------------
    public List<company> getAllComReads() {
        return companyRepository.findAll();
    }

    public void deleteCom(Long id) {
        companyRepository.deleteById(id);
    }


    // ------------------------------------------------- for Seekers ------------------------------
    public List<User> getAllUser() {
        return userRepository.findAll();
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }


    // ----------------------------------------------------- Hires ---------------------------


    public Hire findById(Long id) {
        return repo.findById(id).orElse(null);
    }

    public void saveOrUpdate(Hire hire) {
        repo.save(hire);
    }


}
