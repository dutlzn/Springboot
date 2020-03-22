package sys.demo.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sys.demo.Service.RoleUserService;
import sys.demo.base.result.Results;

@RestController
@RequestMapping("roleuser")
@Slf4j
public  class RoleUserController {

    @Autowired
    private RoleUserService roleUserService;

    @PostMapping("/getRoleUserByUserId")
    public Results getRoleUserByUserId(Integer userId) {
        log.info("RoleUserController.getRoleUserByUserId: param = " + userId);
        return roleUserService.getSysRoleUserByUserId(userId);
    }
}
