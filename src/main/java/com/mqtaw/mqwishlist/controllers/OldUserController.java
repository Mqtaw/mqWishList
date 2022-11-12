//package com.mqtaw.mqwishlist.controllers;
//
//import com.mqtaw.mqwishlist.entity.User;
//import com.mqtaw.mqwishlist.service.UserServiceImpl;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.validation.BindingResult;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//
//import javax.validation.Valid;
//
//@Controller
//@RequestMapping("/usersOld")
//public class OldUserController {
//
//    @Autowired
//    private UserServiceImpl userService;
//
//    @GetMapping(value = {"/", ""})
//    public String showUserList(Model model) {
//        model.addAttribute("users", userService.findAllUsers());
//        return "all-users";
//    }
//
//
//    @GetMapping("/edit/{id}")
//    public String showUpdateForm(@PathVariable("id") int id, Model model) {
//        User user = userService.findUserById(id);
//        if (user == null) {
//            throw new IllegalArgumentException("Invalid user Id:" + id);
//        }
//        model.addAttribute("user", user);
//        return "update-user";
//    }
//
//    @GetMapping("/signup")
//    public String showSignUpForm(User user) {
//        return "add-user";
//    }
//
//    @PostMapping(value = {"/", ""})
//    public String addUser(@Valid User user, BindingResult result, Model model) {
//        if (result.hasErrors()) {
//            return "add-user";
//        }
//        System.out.println(user);
//        userService.save(user);
//        return "redirect:/users/";
//    }
//
//    @PostMapping("/update/{id}")
//    public String updateUser(@PathVariable("id") int id, @Valid User user,
//                             BindingResult result, Model model) {
//        if (result.hasErrors()) {
//            user.setId(id);
//            return "update-user";
//        }
//        userService.save(user);
//        return "redirect:/users";
//    }
//
//    @GetMapping("/delete/{id}")
//    public String deleteUser(@PathVariable("id") int id, Model model) {
//        User user = userService.findUserById(id);
//        if (user == null) {
//            throw new IllegalArgumentException("Invalid user Id:" + id);
//        }
//        userService.deleteById(id);
//        return "redirect:/users/";
//    }
//
//
//}
