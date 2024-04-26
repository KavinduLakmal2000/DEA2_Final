package com.example.springfirst.Service;

import com.example.springfirst.Model.Hire;
import com.example.springfirst.repository.HireRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HireService {

    @Autowired
    private HireRepository repo;

    public Hire save(Hire hire){
        return repo.save(hire);
    }
}
