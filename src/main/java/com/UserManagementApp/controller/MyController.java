package com.UserManagementApp.controller;

import com.UserManagementApp.model.User;
import com.UserManagementApp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@Controller
@RequestMapping("/user")
public class MyController {

    @Autowired
    private UserRepository userRepository;

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



}
