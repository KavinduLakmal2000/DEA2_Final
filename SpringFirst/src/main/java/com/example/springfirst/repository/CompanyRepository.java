package com.example.springfirst.repository;

import com.example.springfirst.Model.company;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanyRepository extends JpaRepository<company, Long> {
}
