package com.example.springfirst.repository;

import com.example.springfirst.Model.Hire;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
public interface HireRepository extends JpaRepository<Hire, Long> {
    List<Hire> findByComId(Long comId);

}