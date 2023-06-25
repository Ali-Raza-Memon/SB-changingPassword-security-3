package com.UserManagementApp.controller;

import com.UserManagementApp.model.User;
import com.UserManagementApp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@Controller
@RequestMapping("/user")
public class MyController {

    @Autowired
    private UserRepository userRepository;


    @Autowired
    private BCryptPasswordEncoder passwordEncoder;


    @ModelAttribute
    private void userDetails(Model m, Principal p){
        String email = p.getName();
        User user = userRepository.findByEmail(email);
        m.addAttribute("user",user);
    }

    @GetMapping("/")
    public String home(){
        return "user/home";
    }

    @GetMapping("/changePassword")
    public String loadChangePassword(){
        return "user/changePass";
    }

    @PostMapping("/updatePass")
    public String changePassword(Principal p, @RequestParam("oldPass") String oldPass,
                                 @RequestParam("newPass") String newPass){

        String email = p.getName();
        User loginUser = userRepository.findByEmail(email);

        boolean f =passwordEncoder.matches(oldPass,loginUser.getPassword());


        if(f){
            System.out.println("Correct old password");
        }else{
            System.out.println("Wrong Old password");
        }

        return "/user/changePass";
    }


}
