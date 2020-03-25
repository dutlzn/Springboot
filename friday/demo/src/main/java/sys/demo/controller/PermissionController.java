package sys.demo.controller;

import com.alibaba.fastjson.JSONArray;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import sys.demo.Service.PermissionService;
import sys.demo.base.result.Results;
import sys.demo.dao.PermissionDao;
import sys.demo.dto.RoleDto;
import sys.demo.model.SysPermission;

import java.util.List;

@Controller
@RequestMapping("permission")
@Slf4j
public class PermissionController {
    @Autowired
    private PermissionService permissionService;
    @Autowired
    private PermissionDao permissionDao;


    @RequestMapping(value = "/listAllPermission", method = RequestMethod.GET)
    @ResponseBody
    public Results<JSONArray> listAllPermission() {
        return permissionService.listAllPermission();
    }

    @RequestMapping(value = "/listAllPermissionByRoleId", method = RequestMethod.GET)
    @ResponseBody
    public Results<SysPermission> listAllPermissionByRoleId(RoleDto roleDto) {
        log.info(getClass().getName() + " : param =  " + roleDto);
        return permissionService.listByRoleId(roleDto.getId().intValue());
    }

    @GetMapping("/menuAll")
    @ResponseBody
    public Results getMenuAll() {
        return permissionService.getMenuAll();
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String addPermission(Model model) {
        model.addAttribute("sysPermission",new SysPermission());
        return "permission/permission-add";
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public Results<SysPermission> savePermission(@RequestBody SysPermission permission) {
        return permissionService.save(permission);
    }

    @RequestMapping(value = "/edit", method = RequestMethod.GET)
    public String editPermission(Model model, @RequestParam("id") Integer id){
        model.addAttribute("sysPermission",permissionService.getSysPermissionById(id) );
        return "permission/permission-add";
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    @ResponseBody
    public Results updatePermission(@RequestBody  SysPermission permission) {
        return permissionService.updateSysPermission(permission);
    }


    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    @ResponseBody
    public Results deletePermission(@RequestParam("id") Integer id) {
        return permissionService.delete(id);
    }

    /**
     * 查找所有pid
     */
    @GetMapping("/testPid")
    @ResponseBody
    public void testPid(){
        List<Integer> data = permissionDao.findAllPid();
        for(int i =0 ;i<data.size();i++){
            System.out.println(data.indexOf(i));
        }
    }
}

