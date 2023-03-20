package ge.tikaradze.shop.controller;


import ge.tikaradze.shop.dto.RoleDto;
import ge.tikaradze.shop.service.RoleService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/role")
public class RoleController {

    private final RoleService roleService;

    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    @GetMapping("roles")
    public List<RoleDto> roles() {
        return roleService.roles();
    }

    @GetMapping("roles/{id}")
    public RoleDto getRole(@PathVariable Long id) {
        return roleService.getRole(id);
    }

    @PostMapping("roles")
    public RoleDto saveRole(@RequestBody RoleDto roleDto) {
        return roleService.saveRole(roleDto);
    }

    @PutMapping("roles/{id}")
    public RoleDto updateRole(@PathVariable Long id, @RequestBody RoleDto roleDto) {
        return roleService.updateRole(id, roleDto);
    }

    @DeleteMapping("roles/{id}")
    public void deleteRole(@PathVariable Long id) {
        roleService.deleteRole(id);
    }
}

