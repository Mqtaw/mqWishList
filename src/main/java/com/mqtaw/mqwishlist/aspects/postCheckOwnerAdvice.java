//package com.mqtaw.mqwishlist.aspects;
//
//import com.mqtaw.mqwishlist.entity.User;
//import com.mqtaw.mqwishlist.service.UserService;
//import org.aspectj.lang.ProceedingJoinPoint;
//import org.aspectj.lang.annotation.Around;
//import org.aspectj.lang.annotation.Aspect;
//import org.aspectj.lang.reflect.MethodSignature;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.security.core.Authentication;
//import org.springframework.stereotype.Component;
//
//import java.util.Map;
//import java.util.Objects;
//
//@Component
//@Aspect
//public class postCheckOwnerAdvice {
//
//    @Autowired
//    private UserService userService;
//
//    @Around("execution(* com.mqtaw.mqwishlist.controllers.WishListController.createNewWishList(..)) ||" +
//            "execution(* com.mqtaw.mqwishlist.controllers.WishListController.deleteWishList(..)) ||" +
//            "execution(* com.mqtaw.mqwishlist.controllers.WishListController.saveNewWishList(..))")
//    public String checkUserIdAsOwner(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
//        Authentication authentication = (Authentication) proceedingJoinPoint.getArgs()[0];
//        int userId = (int) proceedingJoinPoint.getArgs()[1];
//        if (authentication == null) {
//            return "YouShallNotPass";
//        }
//        if (!(authentication == null)) {
//            User user = userService.findUserByUsername(authentication.getName());
//            if (user.getId() != userId) {
//                return "YouShallNotPass";
//            }
//        }
//        return (String) proceedingJoinPoint.proceed();
//    }
//
//}
