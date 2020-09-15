package com.starnet.web.admin;

import com.starnet.po.Phone;
import com.starnet.service.PhoneManageService;
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
public class PhoneMangeController {

    @Autowired
    private PhoneManageService phoneManageService;

    @GetMapping("/SuperPhoneManage")
    public String SuperPhoneManage(@PageableDefault(size = 100) Pageable pageable, Phone phone,Model model){
        model.addAttribute("page",phoneManageService.listPhone(pageable,phone));
        return "admin/SuperPhoneManage";
    }

    @GetMapping("/CommonPhoneManage")
    public String CommonPhoneManage(@PageableDefault(size = 100) Pageable pageabl,Phone phone, Model model){
        model.addAttribute("page",phoneManageService.listPhone(pageabl,phone));
        return "admin/CommonPhoneManage";
    }


    @PostMapping("/SuperPhoneManage/search")
    public String search(@PageableDefault(size = 100) Pageable pageable,Phone phone, Model model){
        model.addAttribute("page",phoneManageService.listPhone(pageable,phone));
        return "admin/SuperPhoneManage :: SuperPhoneManageList";
    }

    @PostMapping("/CommonPhoneManage/search2")
    public String search2(@PageableDefault(size = 100) Pageable pageable,Phone phone, Model model){
        model.addAttribute("page",phoneManageService.listPhone(pageable,phone));
        return "admin/CommonPhoneManage :: CommonPhoneManageList";
    }

    @GetMapping("/SuperPhoneManage/input")
    public String input(Model model){
        model.addAttribute("phone",new Phone());
        return "admin/SuperPhoneManage-input";
    }

    @GetMapping("/SuperPhoneManage/{id}/input")
    public String editInput(@PathVariable Long id, Model model){
        model.addAttribute("phone",phoneManageService.getPhone(id));
        return "admin/SuperPhoneManage-input";
    }

    @GetMapping("/SuperPhoneManage/{id}/input2")
    public String editInput2(@PathVariable Long id, Model model){
        model.addAttribute("phone",phoneManageService.getPhone(id));
        return "admin/SuperPhoneManage-input2";
    }

    @GetMapping("/CommonPhoneManage/{id}/input2")
    public String editInput3(@PathVariable Long id, Model model){
        model.addAttribute("phone",phoneManageService.getPhone(id));
        return "admin/SuperPhoneManage-input2";
    }

    @PostMapping("/SuperPhoneManage")
    public String post(Phone phone, RedirectAttributes attributes, HttpSession session){
        phoneManageService.savePhone(phone);
        return "redirect:/admin/SuperPhoneManage";
    }

    @PostMapping("/SuperPhoneManage/{id}")
    public String editPost(Phone phone,RedirectAttributes attributes,@PathVariable Long id,HttpSession session){

        Phone p = phoneManageService.updatePhone(id,phone);
        if(p == null){
            attributes.addFlashAttribute("message", "更新失败");
        }else{
            attributes.addFlashAttribute("message", "更新成功");
        }
        return "redirect:/admin/SuperPhoneManage";
    }

    @GetMapping("/SuperPhoneManage/{id}/delete")
    public String delete(@PathVariable Long id,RedirectAttributes attributes) {
        phoneManageService.deletePhone(id);
        attributes.addFlashAttribute("message", "删除成功");
        return "redirect:/admin/SuperPhoneManage";
    }

}
