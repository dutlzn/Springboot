package study.demo.controller;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.util.Date;
import java.util.List;
import study.demo.DAO.UserRepository;
import study.demo.entity.User2;
import study.demo.param.User2Param;

import javax.validation.Valid;
import java.util.Date;
import java.util.List;


/**
 * 用户增删改查
 */
@Controller
public class User2Controller {
    @Autowired
    private UserRepository userRepository;

    @RequestMapping("/")
    public String index() {
        return "redirect:/list";
    }

    /**
     * 用户列表做分页，默认每页6个用户，并且按照用户注册的倒叙来排列
     * @return
     */
    @RequestMapping("/list")
    public String list(Model model,@RequestParam(value = "page", defaultValue = "0") Integer page,
                       @RequestParam(value = "size", defaultValue = "6") Integer size) {
        Sort sort=new Sort(Sort.Direction.ASC,"id");
        Pageable pageable = PageRequest.of(page, size, sort);
        Page<User2> users=userRepository.findList(pageable);
        model.addAttribute("users", users);
        return "user/list";
    }

    @RequestMapping("/toAdd")
    public String toAdd() {
        return "user/userAdd";
    }

    /**
     * 添加用户
     * @param user2Param
     * @param result
     * @param model
     * @return
     */
    @RequestMapping("/add")
    public String add(@Valid User2Param user2Param,
                      BindingResult result,
                      Model model){
        String errorMsg = "";
        // 参数校验
        if(result.hasErrors()){
            List<ObjectError> list = result.getAllErrors();
            for(ObjectError error: list){
                errorMsg = errorMsg+error.getCode()+"-"+error.getDefaultMessage()+";";
            }
            model.addAttribute("errorMsg",errorMsg);
            return "user/userAdd";
        }
        // 判断是否重复添加
        User2 u = userRepository.findByUserName(user2Param.getUserName());
        if(u!=null){
            model.addAttribute("errorMsg", "用户已经存在!");
            return "user/userAdd";
        }
        User2 user = new User2();
        BeanUtils.copyProperties(user2Param, user);
        user.setRegTime(new Date());
        return "redirect:/list";
    }
}
