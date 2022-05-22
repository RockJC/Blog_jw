package com.awei.controller;

import com.awei.entity.User;
import com.awei.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;

/**
 * @author jiangwei
 * @create 2022-05-01 17:01
 */
@RestController
@RequestMapping("/user")
public class loginController {
    @Autowired
    private IUserService userService;

    @RequestMapping
    public ModelAndView loginPage(){
        return new ModelAndView("admin/login");
    }

    @RequestMapping ("/login")
    public ModelAndView login(@RequestParam String username, @RequestParam String password, HttpSession session, RedirectAttributes attributes){
        User user = userService.checkLogin(username,password);
        if (user != null){
            user.setPassword(null);
            session.setAttribute("user",user);
            return new ModelAndView("admin/index");
        }else {
            attributes.addFlashAttribute("message","用户名或密码错误");
            return new ModelAndView("redirect:/user");
        }
    }

    @RequestMapping("/logout")
    public ModelAndView logout(ModelAndView modelAndView,HttpSession session){
        modelAndView.clear();
        session.removeAttribute("user");
        return new ModelAndView("redirect:/user");
    }
}
