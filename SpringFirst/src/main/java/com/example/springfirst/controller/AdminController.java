package com.example.springfirst.controller;

import com.example.springfirst.Model.Hire;
import com.example.springfirst.Model.HireDto;
import com.example.springfirst.Model.User;
import com.example.springfirst.Model.company;
import com.example.springfirst.Service.AdminService;
import com.example.springfirst.repository.HireRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/templates/hires")
public class AdminController {

    @Autowired
    private AdminService adminService;

    @Autowired
    private HireRepository HireRepo;

    private Long postId;

    @GetMapping("/AllSeekers")
    public ResponseEntity<List<User>> getAllUser() {
        List<User> userReads = adminService.getAllUser();
        return ResponseEntity.ok().body(userReads);
    }


    @DeleteMapping("/Seeker/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable("id") long id) {
        try {
            adminService.deleteUser(id);
            return ResponseEntity.ok("User has been removed");
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }


    @GetMapping("/AllCompanies")
    public ResponseEntity<List<company>> getAllCompany() {
        List<company> companyReads = adminService.getAllComReads();
        return ResponseEntity.ok().body(companyReads);
    }


    @DeleteMapping("/Company/{id}")
    public ResponseEntity<String> deleteCompany(@PathVariable Long id) {
        try {
            adminService.deleteCom(id);
            return ResponseEntity.ok("Company has been removed");
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }


    @GetMapping({"/AdminControl"})
    public String showJobSeekerList(Model model) {
        List<Hire> hires = HireRepo.findAll(Sort.by(Sort.Direction.DESC, "id"));
        model.addAttribute("hires", hires);
        return "/hires/AdminJobs";
    }


    @GetMapping("/AdminPostDelete")
    public String deleteAdAdmin(
            @RequestParam Long id
    ) {
        try {
            Hire hire = HireRepo.findById(id).get();


            Path imagePath = Paths.get("public/images/" + hire.getImageFileName());

            try {
                Files.delete(imagePath);
            } catch (Exception ex) {
                System.out.println("Exception : " + ex.getMessage());
            }


            HireRepo.delete(hire);
        } catch (Exception ex) {
            System.out.println("Exception : " + ex.getMessage());
        }

        return "redirect:/templates/hires/AdminControl";

    }



    @PostMapping("/AdminPostEdit")
    public String updateAdInAdmin(
            Model model,
            @Valid @ModelAttribute HireDto hireDto,
            BindingResult result
    ) {
        try {

            System.out.println(postId);
            Hire hire = adminService.findById(postId);
            model.addAttribute("hire", hire);

            if (result.hasErrors()) {
                return "hires/EditPost";
            }

            Hire hires = new Hire();

            hires.setPosition(hireDto.getPosition());
            hires.setJobType(hireDto.getJobType());
            hires.setEmail(hireDto.getEmail());
            hires.setDescription(hireDto.getDescription());
            hires.setRequirements(hireDto.getRequirements());
            hires.setExpireDate(hireDto.getExpireDate());
            hires.setComId(hireDto.getComId());
            hires.setCreatedAt(new Date());

            adminService.saveOrUpdate(hires);
        } catch (Exception ex) {
            System.out.println("Exception: " + ex.getMessage());
        }

        return "redirect:/templates/hires/AdminControl";
    }

    @GetMapping("/AdminPostEdit")
    public String GetEdit(
            Model model,
            @RequestParam Long id
    ) {
        try {
            postId = id;
            Hire hire = adminService.findById(id);
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
            System.out.println("Exception: " + ex.getMessage());
        }

        return "hires/EditPost";
    }
}