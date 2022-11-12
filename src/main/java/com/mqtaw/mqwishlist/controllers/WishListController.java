package com.mqtaw.mqwishlist.controllers;

import com.mqtaw.mqwishlist.entity.User;
import com.mqtaw.mqwishlist.entity.Wish;
import com.mqtaw.mqwishlist.entity.WishList;
import com.mqtaw.mqwishlist.service.UserServiceImpl;
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
@RequestMapping("/wish-list")
public class WishListController {

    @Value("${spring.application.name}")
    String appName;

    @Autowired
    private WishListService wishListService;

    @Autowired
    private WishService wishService;

    @Autowired
    private UserServiceImpl userService;

    @PostMapping(value = {"/create"})
    public String createNewWishList(@ModelAttribute("userId") int userId, Model model) {
        WishList wishList = new WishList();
//        User user = userService.findUserById(userId);
//        wishList.setOwner(user);
        model.addAttribute("wishList", wishList);
        model.addAttribute("userId", userId);
        return "add-wishList";
    }

    @PostMapping(value = {"/delete/{wishListId}"})
    public String createNewWishList(@PathVariable int wishListId, @ModelAttribute("userId") int userId) {
        wishListService.deleteById(wishListId);
        return "redirect:/users/" + userId;
    }

    @PostMapping(value = {"/", ""})
    public String saveNewWishList(@ModelAttribute("userId") int userId, @Valid WishList wishList, BindingResult result, Model model) {
        System.out.println(1);
        System.out.println(wishList);
        if (result.hasErrors()) {
            System.out.println(result);
            return "add-wishList";
        }
        System.out.println(1);
        User user = userService.findUserById(userId);
        wishList.setOwner(user);
        wishListService.save(wishList);
        System.out.println(1);
        return "redirect:/users/" + wishList.getOwner().getId();
    }

    @PostMapping("/wishes")
    public String saveWish(@ModelAttribute("wishListId") int wishListId, @ModelAttribute("action") String action, @Valid Wish wish, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "/wish-list/add-wish";
        }
        if (action.equals("create")) {
            WishList wishList = wishListService.findWishListById(wishListId);
            wish.setWishList(wishList);
        }

        WishList wishList = wishListService.findWishListById(wishListId);
        wishService.save(wish);
        return "redirect:/wish-list/" + wishListId;
    }


    @PostMapping("/wishes/create")
    public String createNewWish(@ModelAttribute("wishListId") int wishListId, Model model) {
        Wish wish = new Wish();
        model.addAttribute("wish", wish);
        model.addAttribute("wishListId", wishListId);
        model.addAttribute("action", "create");
        return "add-wish";
    }

    @PostMapping("/wishes/update")
    public String updateWish(@ModelAttribute("wishId") int wishId,
                             @ModelAttribute("wishListId") int wishListId,
                             Model model) {
        Wish wish = wishService.findWishById(wishId);
        model.addAttribute("wish", wish);
        model.addAttribute("wishListId", wishListId);
        model.addAttribute("action", "update");
        return "update-wish";
    }

    @PostMapping("/wishes/reserve")
    public String reserveWish(@ModelAttribute("wishId") int wishId,
                              @ModelAttribute("wishListId") int wishListId) {
        Wish wish = wishService.findWishById(wishId);
        wish.setIsLocked(true);
        wishService.save(wish);
        return "redirect:/wish-list/" + wishListId;
    }

    @PostMapping("/wishes/cancel-reserve")
    public String unReserveWish(@ModelAttribute("wishId") int wishId,
                              @ModelAttribute("wishListId") int wishListId) {
        Wish wish = wishService.findWishById(wishId);
        wish.setIsLocked(false);
        wishService.save(wish);
        return "redirect:/wish-list/" + wishListId;
    }

    @PostMapping("/wishes/delete")
    public String deleteWish(@ModelAttribute("wishId") int wishId,
                             @ModelAttribute("wishListId") int wishListId) {
        wishService.deleteById(wishId);
        return "redirect:/wish-list/" + wishListId;
    }


    @GetMapping("/{wishlistId}")
    public String wishListPage(@PathVariable int wishlistId, Model model) {
        WishList wishList = wishListService.findWishListById(wishlistId);
        model.addAttribute("wishList", wishList);
        return "wishlist";
    }
}
