package com.mqtaw.mqwishlist.controllers;

import com.mqtaw.mqwishlist.entity.User;
import com.mqtaw.mqwishlist.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;


//@Controller
//public class MainController {
//    @GetMapping("/")
//    public String homePage() {
//        System.out.println();
////        model.addAttribute("user", user);
//        return "home";
//    }

    @Controller
    public class MainController {

        @Autowired
        private UserService userService;

        @GetMapping("/")
        public String homePage(Authentication authentication, Model model) {
            if (!(authentication == null)) {
                User user = userService.findUserByUsername(authentication.getName());
                model.addAttribute("user", user);
            }
            return "home";
        }

        @GetMapping("/registration")
        public String registration() {
            return "registration";
        }

        @PostMapping("/registration")
        public String createUser(User user, Model model) {
            System.out.println(user);
            if (!userService.createUser(user)) {
                model.addAttribute("errorMessage",
                        "user with username: " + user.getUsername()
                                + " уже существует");
                return "registration";
            }
            return "redirect:/login";
        }

        @GetMapping("/login")
        public String login() {
            return "login";
        }

}
