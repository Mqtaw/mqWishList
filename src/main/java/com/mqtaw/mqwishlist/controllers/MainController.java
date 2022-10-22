package com.mqtaw.mqwishlist.controllers;

import com.mqtaw.mqwishlist.entity.User;
import com.mqtaw.mqwishlist.entity.WishList;
import com.mqtaw.mqwishlist.service.UserService;
import com.mqtaw.mqwishlist.service.WishListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    @Value("${spring.application.name}")
    String appName;

    @Autowired
    private WishListService wishListService;

    @GetMapping("/")
    public String homePage(Model model) {
        model.addAttribute("appName", appName);
        return "home";
    }

    @GetMapping("/wishList")
    public String wishListPage(Model model) {
        WishList wishList = wishListService.findWishListById(1);
        System.out.println(wishList);
        System.out.println(wishList.getProducts());
        model.addAttribute("wishList", wishList);
        return "wishlist";
//        model.addAttribute("appName", appName);
//        return "home";


    }
}
