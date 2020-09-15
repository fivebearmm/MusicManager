package com.starnet.web.admin;

import com.starnet.dao.UserManagerRepository;
import com.starnet.po.User;
import com.starnet.service.UserManagerService;
import com.starnet.vo.UserQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/admin")
public class UserManagerController {

    private static final String INPUT = "admin/SuperUserManager-input";
    private static final String LIST1 = "admin/SuperUserManager";
    private static final String REDIRECT_LIST1 = "redirect:/admin/SuperUserManager";
    private static final String LIST2 = "admin/CommonUserManager";
    private static final String REDIRECT_LIST2 = "redirect:/admin/CommonUserManager";

    @Autowired
    private UserManagerService userManagerService;

    @GetMapping("/SuperUserManager")
    public String SuperUserManager(@PageableDefault(size = 100) Pageable pageable, UserQuery user, Model model){
        model.addAttribute("page",userManagerService.listUser(pageable,user));
        return LIST1;
    }

    @GetMapping("/CommonUserManager")
    public String CommonUserManager(@PageableDefault(size = 100) Pageable pageable, UserQuery user, Model model){
        model.addAttribute("page",userManagerService.listUser(pageable,user));
        return LIST2;
    }

    @PostMapping("/SuperUserManager/search")
    public String search(@PageableDefault(size = 100) Pageable pageable, UserQuery user, Model model){
        model.addAttribute("page",userManagerService.listUser(pageable,user));
        return "admin/SuperUserManager :: SuperUserManagerList";
    }

    @PostMapping("/CommonUserManager/search2")
    public String search2(@PageableDefault(size = 100) Pageable pageable,UserQuery user, Model model){
        model.addAttribute("page",userManagerService.listUser(pageable,user));
        return "admin/CommonUserManager :: CommonUserManagerList";
    }

    @GetMapping("/SuperUserManager/input")
    public String input(Model model){
        model.addAttribute("user",new User());
        return INPUT;
    }

    @GetMapping("/SuperUserManager/{id}/input")
    public String editInput(@PathVariable Long id,Model model){
        model.addAttribute("user",userManagerService.getUser(id));
        return INPUT;
    }

    @PostMapping("/SuperUserManager")
    public String post(User user,RedirectAttributes attributes,HttpSession session){
            userManagerService.saveUser(user);
        return "redirect:/admin/SuperUserManager";
    }

    @PostMapping("/SuperUserManager/{id}")
    public String editPost(User user,RedirectAttributes attributes,@PathVariable Long id,HttpSession session){

        User t = userManagerService.updateUser(id,user);
        if(t==null){
            attributes.addFlashAttribute("message", "更新失败");
        }else{
            attributes.addFlashAttribute("message", "更新成功");
        }
        return "redirect:/admin/SuperUserManager";
    }

    @GetMapping("/SuperUserManager/{id}/delete")
    public String delete(@PathVariable Long id,RedirectAttributes attributes) {
        userManagerService.deleteUser(id);
        attributes.addFlashAttribute("message", "删除成功");
        return "redirect:/admin/SuperUserManager";
    }

}
