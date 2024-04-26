package com.example.springfirst.controller;

import com.example.springfirst.Model.Hire;
import com.example.springfirst.Model.HireDto;
import com.example.springfirst.repository.HireRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;



import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Date;
import java.util.List;



@Controller
@RequestMapping("/templates/hires")
public class HireController {
    @Autowired
    private HireRepository repo;


    private Long editID;
    private Long CompanyID;

    //connect HTML file
    //show data
    @GetMapping({"", "/"})
    public String showHireList(Model model) {
        List<Hire> hires = repo.findAll(Sort.by(Sort.Direction.DESC, "id"));
        model.addAttribute("hires", hires);
        return "hires/index";
    }

    @GetMapping({ "/JobsByCom" })
    public String showHireList(Model model, @RequestParam(required = false) Long comId) {
        List<Hire> hires;
        if (comId != null) {

            CompanyID = comId;
            hires = repo.findByComId(comId);
        } else {
            hires = repo.findAll(Sort.by(Sort.Direction.DESC, "id"));
        }
        model.addAttribute("hires", hires);
        return "hires/CompanyDashboard";
    }


    //create data
    @GetMapping({"/create"})
    public String showCreateHirePage(Model model) {
        HireDto hireDto = new HireDto();
        model.addAttribute("hireDto", hireDto);
        return "hires/PostAds";
    }


    @PostMapping({"/create"})
    public String createHire(
            @Valid @ModelAttribute HireDto hireDto,
            BindingResult result

    ) {
        if (hireDto.getImageFile().isEmpty()) {
            result.addError(new FieldError("hireDto", "imageFile", "The image file is required"));
        }
        if (result.hasErrors()) {
            return "hires/PostAds";
        }


//save data to database
        //save image file
        MultipartFile image = hireDto.getImageFile();
        Date createdAt = new Date();
        String storageFileName = createdAt.getTime() + "_" + image.getOriginalFilename();

        try {
            String uploadDir = "public/images/";
            Path uploadPath = Paths.get(uploadDir);

            if (!Files.exists(uploadPath)) {
                Files.createDirectories(uploadPath);
            }

            try (InputStream inputstream = image.getInputStream()) {
                Files.copy(inputstream, Paths.get(uploadDir + storageFileName),
                        StandardCopyOption.REPLACE_EXISTING);
            }
        } catch (Exception ex) {
            System.out.println("Exception:" + ex.getMessage());
        }

        Hire hire = new Hire(); //create object

        hire.setPosition(hireDto.getPosition());
        hire.setJobType(hireDto.getJobType());
        hire.setEmail(hireDto.getEmail());
        hire.setDescription(hireDto.getDescription());
        hire.setRequirements(hireDto.getRequirements());
        hire.setExpireDate(hireDto.getExpireDate());
        hire.setComId(hireDto.getComId());
        hire.setCreatedAt(new Date());
        hire.setImageFileName(storageFileName);

        System.out.println(hireDto.getComId());


        repo.save(hire);

        return "redirect:/templates/hires/JobsByCom?comId="+ CompanyID;

    }


    @GetMapping("/edit")
    public String showEditHirePage(
            Model model,
            @RequestParam Long id
    ) {

        try {
            editID = id;
            Hire hire = repo.findById(id).get();
            model.addAttribute("hire", hire);

            HireDto hireDto = new HireDto();

            hireDto.setPosition(hire.getPosition());
            hireDto.setJobType(hire.getJobType());
            hireDto.setEmail(hire.getEmail());
            hireDto.setDescription(hire.getDescription());
            hireDto.setRequirements(hire.getRequirements());
            hire.setExpireDate(hireDto.getExpireDate());


            model.addAttribute("hireDto", hireDto);

        } catch (Exception ex) {
            System.out.println("Exceptions:" + ex.getMessage());
            return "redirect:/templates/hires/JobsByCom?comId="+ CompanyID;

        }
        return "hires/EditPost";
    }

    @PostMapping("/edit")
    public String updateAd(
            Model model,
            @Valid @ModelAttribute HireDto hireDto,
            BindingResult result
    ) {
        try {
            Hire hire = repo.findById(editID).orElseThrow(() -> new IllegalArgumentException("Invalid hire ID: " + editID));

            model.addAttribute("hire", hire);

            //check errors
            if (result.hasErrors()) {
                return "hires/EditPost";
            }


            if (hireDto.getImageFile().isEmpty()) {

                String uploadDir = "public/images/";
                Path oldImagePath = Paths.get(uploadDir + hire.getImageFileName());
                try {
                    Files.delete(oldImagePath);
                } catch (Exception ex) {
                    System.out.println("Exception " + ex.getMessage());

                }

                //save new image file

                MultipartFile image = hireDto.getImageFile();
                Date createdAt = new Date();
                String storageFileName = createdAt.getTime() + "_" + image.getOriginalFilename();


                try (InputStream inputstream = image.getInputStream()) {
                    Files.copy(inputstream, Paths.get(uploadDir + storageFileName),
                            StandardCopyOption.REPLACE_EXISTING);
                }


                hire.setImageFileName(storageFileName);
            }
            hire.setPosition(hireDto.getPosition());
            hire.setJobType(hireDto.getJobType());
            hire.setEmail(hireDto.getEmail());
            hire.setDescription(hireDto.getDescription());
            hire.setRequirements(hireDto.getRequirements());
            hire.setExpireDate(hireDto.getExpireDate());


            repo.save(hire);




        } catch (Exception ex) {
            System.out.println("Exception2: " + ex.getMessage());

        }

        if (CompanyID == null){
            return "redirect:/templates/hires/AdminControl";
        }

        return "redirect:/templates/hires/JobsByCom?comId="+ CompanyID;
    }

    @GetMapping("/delete")
    public String deleteAd(
            @RequestParam Long id
    ) {
        try {

            Hire hire = repo.findById(id).get();


            Path imagePath = Paths.get("public/images/" + hire.getImageFileName());

            try {
                Files.delete(imagePath);
            } catch (Exception ex) {
                System.out.println("Exception : " + ex.getMessage());
            }

            //delete the Ad
            repo.delete(hire);
        } catch (Exception ex) {
            System.out.println("Exception : " + ex.getMessage());
        }

        return "redirect:/templates/hires/JobsByCom?comId="+ CompanyID;

    }


}