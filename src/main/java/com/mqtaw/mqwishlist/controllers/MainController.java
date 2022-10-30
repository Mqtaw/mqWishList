package com.mqtaw.mqwishlist.controllers;

import com.mqtaw.mqwishlist.entity.Wish;
import com.mqtaw.mqwishlist.entity.WishList;
import com.mqtaw.mqwishlist.service.WishService;
import com.mqtaw.mqwishlist.service.WishListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
public class MainController {

    @Value("${spring.application.name}")
    String appName;

    @Autowired
    private WishListService wishListService;

    @Autowired
    private WishService wishService;


    @GetMapping("/")
    public String homePage(Model model) {
        model.addAttribute("appName", appName);
        return "home";
    }

    @PostMapping("/wish-list/wishes")
    public String saveWish(@ModelAttribute("wishListId") int wishListId, @ModelAttribute("action") String action, @Valid Wish wish, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "/wish-list/add-wish";
        }
        System.out.println(1111);
        System.out.println(action);
        System.out.println(wish);
        if (action.equals("create")) {
            WishList wishList = wishListService.findWishListById(wishListId);
            wish.setWishList(wishList);
        }

        WishList wishList = wishListService.findWishListById(wishListId);
        wish.setWishList(wishList);
        wishService.save(wish);
        return "redirect:/wish-list/" + wishListId;
    }


    @PostMapping("/wish-list/wishes/create")
    public String createNewWish(@ModelAttribute("wishListId") int wishListId, Model model) {
        Wish wish = new Wish();
        model.addAttribute("wish", wish);
        model.addAttribute("wishListId", wishListId);
        model.addAttribute("action", "create");
        return "add-wish";
    }

    @PostMapping("/wish-list/wishes/update")
    public String updateWish(@ModelAttribute("wishId") int wishId,
                             @ModelAttribute("wishListId") int wishListId,
                             Model model) {
        Wish wish = wishService.findWishById(wishId);
        model.addAttribute("wish", wish);
        model.addAttribute("wishListId", wishListId);
        model.addAttribute("action", "update");
        return "update-wish";
    }

    @PostMapping("/wish-list/wishes/reserve")
    public String reserveWish(@ModelAttribute("wishId") int wishId,
                              @ModelAttribute("wishListId") int wishListId) {
        Wish wish = wishService.findWishById(wishId);
        wish.setIsLocked(true);
        wishService.save(wish);
        return "redirect:/wish-list/" + wishListId;
    }

    @PostMapping("/wish-list/wishes/cancel-reserve")
    public String unReserveWish(@ModelAttribute("wishId") int wishId,
                              @ModelAttribute("wishListId") int wishListId) {
        Wish wish = wishService.findWishById(wishId);
        wish.setIsLocked(false);
        wishService.save(wish);
        return "redirect:/wish-list/" + wishListId;
    }

    @PostMapping("/wish-list/wishes/delete")
    public String deleteWish(@ModelAttribute("wishId") int wishId,
                             @ModelAttribute("wishListId") int wishListId) {
        wishService.deleteById(wishId);
        return "redirect:/wish-list/" + wishListId;
    }


    @GetMapping("/wish-list/{wishlistId}")
    public String wishListPage(@PathVariable int wishlistId, Model model) {
        WishList wishList = wishListService.findWishListById(wishlistId);
        model.addAttribute("wishList", wishList);
        return "wishlist";
    }
}
