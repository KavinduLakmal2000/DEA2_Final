package com.example.springfirst.Service;

import com.example.springfirst.Model.User;
import com.example.springfirst.Model.company;
import com.example.springfirst.repository.CompanyRepository;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class companyService {

    @Autowired
    private CompanyRepository companyRepository;


    // save data in company
    public company save(company company) {
        return companyRepository.save(company);
    }

    //login as company
    public boolean authenticate(Long id, String password) {
        Optional<company> optionalUser = companyRepository.findById(id);
        return optionalUser.isPresent() && optionalUser.get().getPassword().equals(password);
    }

    // get by id

    public Optional<company> getCompanyReadById(Long id) {
        return companyRepository.findById(id);
    }

    // Update company profile
    public company updateCompanyProfile(company companyModel) {
        Optional<company> existingCompany = companyRepository.findById(companyModel.getId());
        if (existingCompany.isPresent()) {
            company existing = existingCompany.get();
            existing.setCompanyName(companyModel.getCompanyName());
            existing.setEmail(companyModel.getEmail());
            existing.setPhone(companyModel.getPhone());
            existing.setContactPersonFirstName(companyModel.getContactPersonFirstName());
            existing.setContactPersonLastName(companyModel.getContactPersonLastName());
            existing.setAnnualRevenue(companyModel.getAnnualRevenue());
            existing.setIndustry(companyModel.getIndustry());
            existing.setPassword(companyModel.getPassword());
            return companyRepository.save(existing);
        } else {
            // Company not found
            return null;
        }
    }

    // Delete company by ID
    public void deleteCompany(Long id) {
        companyRepository.deleteById(id);
    }


}
