package com.starnet.web.admin;

import com.starnet.po.Music;
import com.starnet.po.Phone;
import com.starnet.service.MusicManageService;
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
public class MusicManageController {

    @Autowired
    private MusicManageService musicManageService;

    @GetMapping("/SuperMusicManage")
    public String SuperPhoneManage(@PageableDefault(size = 100) Pageable pageable, Music music, Model model){
        model.addAttribute("page",musicManageService.listMusic(pageable,music));
        return "admin/SuperMusicManage";
    }

    @GetMapping("/CommonMusicManage")
    public String CommonPhoneManage(@PageableDefault(size = 100) Pageable pageable,Music music, Model model){
        model.addAttribute("page",musicManageService.listMusic(pageable,music));
        return "admin/CommonMusicManage";
    }


    @PostMapping("/SuperMusicManage/search")
    public String search(@PageableDefault(size = 100) Pageable pageable,Music music, Model model){
        model.addAttribute("page",musicManageService.listMusic(pageable,music));
        return "admin/SuperMusicManage :: SuperMusicManageList";
    }

    @PostMapping("/CommonMusicManage/search2")
    public String search2(@PageableDefault(size = 100) Pageable pageable,Music music, Model model){
        model.addAttribute("page",musicManageService.listMusic(pageable,music));
        return "admin/CommonMusicManage :: CommonMusicManageList";
    }

    @GetMapping("/SuperMusicManage/input")
    public String input(Model model){
        model.addAttribute("music",new Music());
        return "admin/SuperMusicManage-input";
    }

    @GetMapping("/SuperMusicManage/{id}/input")
    public String editInput(@PathVariable Long id, Model model){
        model.addAttribute("music",musicManageService.getMusic(id));
        return "admin/SuperMusicManage-input";
    }

    @GetMapping("/SuperMusicManage/{id}/input2")
    public String editInput2(@PathVariable Long id, Model model){
        model.addAttribute("music",musicManageService.getMusic(id));
        return "admin/SuperMUsicManage-input2";
    }

    @GetMapping("/CommonMusicManage/{id}/input2")
    public String editInput3(@PathVariable Long id, Model model){
        model.addAttribute("music",musicManageService.getMusic(id));
        return "admin/SuperMusicManage-input2";
    }

    @PostMapping("/SuperMusicManage")
    public String post(Music music, RedirectAttributes attributes, HttpSession session){
       musicManageService.saveMusic(music);
        return "redirect:/admin/SuperMusicManage";
    }

    @PostMapping("/SuperMusicManage/{id}")
    public String editPost(Music music,RedirectAttributes attributes,@PathVariable Long id,HttpSession session){

        Music music1 = musicManageService.updateMusic(id,music);
        if(music1 == null){
            attributes.addFlashAttribute("message", "更新失败");
        }else{
            attributes.addFlashAttribute("message", "更新成功");
        }
        return "redirect:/admin/SuperMusicManage";
    }

    @GetMapping("/SuperMusicManage/{id}/delete")
    public String delete(@PathVariable Long id,RedirectAttributes attributes) {
        musicManageService.deleteMusic(id);
        attributes.addFlashAttribute("message", "删除成功");
        return "redirect:/admin/SuperMusicManage";
    }

}
