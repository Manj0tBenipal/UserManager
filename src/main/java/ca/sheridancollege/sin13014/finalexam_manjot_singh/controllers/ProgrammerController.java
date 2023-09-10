package ca.sheridancollege.sin13014.finalexam_manjot_singh.controllers;

import ca.sheridancollege.sin13014.finalexam_manjot_singh.beans.Programmer;
import ca.sheridancollege.sin13014.finalexam_manjot_singh.beans.User;
import ca.sheridancollege.sin13014.finalexam_manjot_singh.repos.ProgrammerRepo;
import ca.sheridancollege.sin13014.finalexam_manjot_singh.repos.UserRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@Controller
public class ProgrammerController {
    private final Logger log = LoggerFactory.getLogger(ProgrammerController.class);
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private ProgrammerRepo progRepo;

    @GetMapping("/")
    public String home(){
        return "home.html";
    }@GetMapping("/login")
    public String login(){
        return "login.html";
    }
    @GetMapping("/register")
    public String registerUser(Model model) {
        model.addAttribute(new User());

        return "register";
    }

    @PostMapping("/addUser")
    public String addNewUser(@ModelAttribute User user, BCryptPasswordEncoder passwordEncoder) {
        userRepo.addUser(user.getUserName(), passwordEncoder.encode(user.getEncryptedPassword()));
        log.info("User added");
        return "redirect:/login";
    }
    @GetMapping("/add-programmer")
    public String addProgrammer(Model model){
        Programmer programmer = new Programmer();
        model.addAttribute("programmer", programmer);
        return "add-programmer";
    }

    @PostMapping("/add-programmer")
    public String addProgrammer(@ModelAttribute Programmer programmer){
        progRepo.addProgrammer(programmer);
        return "redirect:/add-programmer";
    }

    @GetMapping("/view-programmers")
    public String viewProgrammers(Model model){
        ArrayList<Programmer> programmers = progRepo.getProgrammers();
        model.addAttribute("programmers", programmers);
        return "view-programmers";
    }
    @GetMapping("/delete-programmer/{id}")
    public String deleteProgrammer(@PathVariable int id){
       progRepo.deleteProgrammer(id);

        return "redirect:/view-programmers";
    }


}
