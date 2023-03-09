package com.codermy.myspringsecurity.controller;

import com.codermy.myspringsecurity.dto.PermissionDto;
import com.codermy.myspringsecurity.eneity.TbPermission;
import com.codermy.myspringsecurity.service.PermissionService;
import com.codermy.myspringsecurity.utils.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author codermy
 * @createTime 2020/6/26
 */
@RestController
@RequestMapping("/system/permission")
@Slf4j
@Api(tags = "菜单信息相关接口")
public class PermissionController {
    @Autowired
    private PermissionService permissionService;


    @GetMapping("/index")
    @PreAuthorize("hasAnyAuthority('menu:list')")
    @ApiOperation(value = "菜单列表")
    public Result getMenuAll(){
        return permissionService.getMenuAll();
    }

    @GetMapping("/build")
    @PreAuthorize("hasAnyAuthority('menu:list')")
    @ApiOperation(value = "绘制菜单树")
    public Result buildMenuAll(){
        List<PermissionDto> menuAll =permissionService.buildMenuAll();
        return Result.ok().data(menuAll);
    }
    @GetMapping("/ebuild/{roleId}")
    @PreAuthorize("hasAnyAuthority('menu:list')")
    @ApiOperation(value = "通过id绘制菜单树")
    public Result buildMenuAllByRoleId(@PathVariable Integer roleId){
        List<PermissionDto> menuAll =permissionService.buildMenuAllByRoleId(roleId);
        return Result.ok().data(menuAll);
    }

    @GetMapping(value = "/add")
    @PreAuthorize("hasAnyAuthority('menu:add')")
    @ApiOperation(value = "添加菜单页面")
    public String addPermission(Model model) {
        model.addAttribute("tbPermission",new TbPermission());
        return "/system/permission/permission-add";
    }

    @PostMapping(value = "/add")
    @PreAuthorize("hasAnyAuthority('menu:add')")
    @ApiOperation(value = "添加菜单")
    public Result<TbPermission> savePermission(@RequestBody TbPermission permission) {
        return permissionService.save(permission);
    }

    @GetMapping(value = "/edit")
    @PreAuthorize("hasAnyAuthority('menu:edit')")
    @ApiOperation(value = "修改菜单页面")
    public String editPermission(Model model, TbPermission permission) {
        model.addAttribute("tbPermission",permissionService.getTbPermissionById(permission.getId()));
        return "/system/permission/permission-add";
    }

    @PostMapping(value = "/edit")
    @PreAuthorize("hasAnyAuthority('menu:edit')")
    @ApiOperation(value = "通修改菜单")
    public Result updatePermission(@RequestBody  TbPermission permission) {
        return permissionService.updateTbPermission(permission);
    }

    @GetMapping(value = "/delete")
    @PreAuthorize("hasAnyAuthority('menu:del')")
    @ApiOperation(value = "删除菜单")
    public Result deletePermission(TbPermission tbPermission) {
        return permissionService.delete(tbPermission.getId());
    }

    @GetMapping(value = "/menu")
    @ApiOperation(value = "通过id获取菜单")
    public Result<TbPermission> getMenu(Integer userId) {
        return permissionService.getMenu(userId);
    }


}
