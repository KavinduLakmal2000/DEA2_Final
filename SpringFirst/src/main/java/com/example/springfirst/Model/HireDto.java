package com.example.springfirst.Model;

import jakarta.persistence.Table;
import jakarta.validation.constraints.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;
public class HireDto {
    @NotEmpty(message = "position required")
    private String position;
    @NotEmpty(message = "jobType required")
    private String jobType;

    @NotEmpty(message = "email required")
    private String email;

    @NotEmpty()
    private String expireDate;



    @Size(min = 10, message = "should be at least 10 char")
    @Size(max = 2000, message = "The description cannot exceed 2000")
    private String description;

    @Size(min = 10, message = "should be at least 10 char")
    @Size(max = 2000, message = "The description cannot exceed 2000")
    private String Requirements;


    private MultipartFile imageFile;


    private Long ComId;


    public Long getComId() {
        return ComId;
    }

    public void setComId(Long comId) {
        ComId = comId;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getJobType() {
        return jobType;
    }

    public void setJobType(String jobType) {
        this.jobType = jobType;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getExpireDate() {
        return expireDate;
    }


    public void setExpireDate(String expireDate) {
        this.expireDate = expireDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    public String getRequirements() {
        return Requirements;
    }

    public void setRequirements(String requirements) {
        Requirements = requirements;
    }

    public MultipartFile getImageFile() {
        return imageFile;
    }

    public void setImageFile(MultipartFile imageFile) {
        this.imageFile = imageFile;
    }
}
