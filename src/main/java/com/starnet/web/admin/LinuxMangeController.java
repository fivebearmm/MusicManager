package com.starnet.web.admin;

import com.starnet.po.Linux;
import com.starnet.po.Phone;
import com.starnet.service.LinuxManageService;
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
public class LinuxMangeController {

    @Autowired
    private LinuxManageService linuxManageService;

    @Autowired
    private PhoneManageService phoneManageService;

    @GetMapping("/SuperLinuxManage")
    public String SuperLinuxManager(@PageableDefault(size = 100) Pageable pageable, Linux linux, Model model){
        model.addAttribute("phones",phoneManageService.listPhone());
        model.addAttribute("page",linuxManageService.listLinux(pageable,linux));
        return "admin/SuperLinuxManage";
    }

    @GetMapping("/CommonLinuxManage")
    public String CommonLinuxManage(@PageableDefault(size = 100) Pageable pageable, Linux linux, Model model){
        model.addAttribute("page",linuxManageService.listLinux(pageable,linux));
        return "admin/CommonLinuxManage";
    }

    @PostMapping("/SuperLinuxManage/search")
    public String search(@PageableDefault(size = 100) Pageable pageable,Linux linux, Model model){
        model.addAttribute("page",linuxManageService.listLinux(pageable,linux));
        return "admin/SuperLinuxManage :: SuperLinuxManageList";
    }

    @PostMapping("/CommonLinuxManage/search2")
    public String search2(@PageableDefault(size = 100) Pageable pageable,Linux linux, Model model){
        model.addAttribute("page",linuxManageService.listLinux(pageable,linux));
        return "admin/CommonLinuxManage :: CommonLinuxManageList";
    }

    @GetMapping("/SuperLinuxManage/input")
    public String input(Model model){
        model.addAttribute("linux",new Linux());
        return "admin/SuperLinuxManage-input";
    }

    @GetMapping("/SuperLinuxManage/{id}/input")
    public String editInput(@PathVariable Long id, Model model){
        model.addAttribute("linux",linuxManageService.getLinux(id));
        return "admin/SuperLinuxManage-input";
    }

    @GetMapping("/SuperLinuxManage/{id}/input2")
    public String editInput2(@PathVariable Long id, Model model){
        model.addAttribute("linux",linuxManageService.getLinux(id));
        return "admin/SuperLinuxManage-input2";
    }

    @GetMapping("/CommonLinuxManage/{id}/input2")
    public String editInput3(@PathVariable Long id, Model model){
        model.addAttribute("linux",linuxManageService.getLinux(id));
        return "admin/SuperLinuxManage-input2";
    }

    @PostMapping("/SuperLinuxManage")
    public String post(Linux linux, RedirectAttributes attributes, HttpSession session){
        linuxManageService.saveLinux(linux);
        return "redirect:/admin/SuperLinuxManage";
    }

    @PostMapping("/SuperLinuxManage/{id}")
    public String editPost(Linux linux,RedirectAttributes attributes,@PathVariable Long id,HttpSession session){

        Linux l = linuxManageService.updateLinux(id,linux);
        if(l == null){
            attributes.addFlashAttribute("message", "更新失败");
        }else{
            attributes.addFlashAttribute("message", "更新成功");
        }
        return "redirect:/admin/SuperLinuxManage";
    }


    @GetMapping("/SuperLinuxManage/{id}/delete")
    public String delete(@PathVariable Long id, RedirectAttributes attributes) {
        linuxManageService.deleteLinux(id);
        attributes.addFlashAttribute("message", "删除成功");
        return "redirect:/admin/SuperLinuxManage";
    }
}
