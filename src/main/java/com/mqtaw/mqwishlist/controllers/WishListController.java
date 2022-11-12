package com.mqtaw.mqwishlist.controllers;

import com.mqtaw.mqwishlist.entity.User;
import com.mqtaw.mqwishlist.entity.Wish;
import com.mqtaw.mqwishlist.entity.WishList;
import com.mqtaw.mqwishlist.service.UserServiceImpl;
import com.mqtaw.mqwishlist.service.WishService;
import com.mqtaw.mqwishlist.service.WishListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
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
    public String createNewWishList(Authentication authentication, @ModelAttribute("userId") int userId, Model model) {
        WishList wishList = new WishList();
        model.addAttribute("wishList", wishList);
        model.addAttribute("userId", userId);
        return "add-wishList";
    }

    @PostMapping(value = {"/delete/{wishListId}"})
    public String deleteWishList(Authentication authentication, @ModelAttribute("userId") int userId, @PathVariable int wishListId) {
        wishListService.deleteById(wishListId);
        return "redirect:/";
    }

    @PostMapping(value = {"/", ""})
    public String saveNewWishList(Authentication authentication, @ModelAttribute("userId") int userId, @Valid WishList wishList, BindingResult result, Model model) {
        if (result.hasErrors()) {
            System.out.println(result);
            return "add-wishList";
        }
        User user = userService.findUserById(userId);
        wishList.setOwner(user);
        wishListService.save(wishList);
        return "redirect:/";
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
        System.out.println(wishId);
        wishService.deleteById(wishId);
        System.out.println(wishId);
        return "redirect:/wish-list/" + wishListId;
    }


    @GetMapping("/{wishlistId}")
    public String wishListPage(@PathVariable int wishlistId, Authentication authentication, Model model) {
        boolean isOwner = false;
        WishList wishList = wishListService.findWishListById(wishlistId);
        if (!(authentication == null)) {
            User user = userService.findUserByUsername(authentication.getName());
            if (user.getWishLists().contains(wishList)) {
                model.addAttribute("isOwner", true);
            }
            model.addAttribute("user", user);
        }
        model.addAttribute("wishList", wishList);
        return "wishlist";
    }
}
