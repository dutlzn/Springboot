package sys.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("")
public class TestController {
    @GetMapping("vvv")
    /*
    ?name= 是直接可以解析的
     */
    public String testIndex(String name,String var2) {
        System.out.println(var2);
        System.out.println(name);
        return "test"+name+var2;
    }
}
