package com.starnet.web.admin;

import com.starnet.po.User;
import com.starnet.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import javax.servlet.http.HttpSession;


@Controller
@RequestMapping("/admin")
public class LoginController {

    @Autowired
    private UserService userService;

    @GetMapping
    public String loginPage() {
        return "admin/login";
    }


   @PostMapping("/login")
   public String login(
                       @RequestParam String username,
                    @RequestParam String password,
                      HttpSession session, RedirectAttributes attributes) {

        User user = userService.checkUser(username, password);
       if(user == null){
           attributes.addFlashAttribute("message","用户名和密码错误");
           return "redirect:/admin";
       }else{
           if(user.getType().equals("超级管理员")){
               user.setPassword(null);
               session.setAttribute("user",user);
               return "admin/SuperUserIndex";
           }else{
               return "admin/CommonUserIndex";
           }
       }

    }
}

