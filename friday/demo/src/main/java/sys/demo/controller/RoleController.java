package sys.demo.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import sys.demo.Service.RoleService;
import sys.demo.base.result.Results;
import sys.demo.model.SysRole;

@Controller
@RequestMapping("role")
@Slf4j
public class RoleController {
    @Autowired
    private RoleService roleService;
    @GetMapping("all")
    @ResponseBody
    public Results<SysRole> getAll() {
        log.info("RoleController.getAll()");
        return roleService.getAllRoles();
    }
}
