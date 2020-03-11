package study.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import study.demo.entity.User;

import javax.validation.Valid;

@Controller
public class TestVaildator {
    @GetMapping("/test")
    public String showForm(User user){
        return "form";
    }

    @GetMapping("/results")
    public String results() {
        return "results";
    }

    @PostMapping("/test")
    public String checkUser(@Valid User user,
                            BindingResult bindingResult,
                            RedirectAttributes attributes){
        // 实体钟所有的属性都必须通过验证，否则不会成功
        if(bindingResult.hasErrors()){
            return "form";
        }
        attributes.addFlashAttribute("user", user);
        return "redirect:/results";
    }
}
