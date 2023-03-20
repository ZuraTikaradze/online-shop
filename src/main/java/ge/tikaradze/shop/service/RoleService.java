package ge.tikaradze.shop.service;



import ge.tikaradze.shop.dto.RoleDto;
import ge.tikaradze.shop.entity.Role;

import java.util.List;

public interface RoleService {
    List<RoleDto> roles();

    RoleDto getRole(Long id);

    RoleDto saveRole(RoleDto roleDto);

    RoleDto updateRole(Long id, RoleDto roleDto);

    void deleteRole(Long id);

    Role getRoleByName(String name);
}
