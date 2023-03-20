package ge.tikaradze.shop.service.impl;

import ge.tikaradze.shop.dto.RoleDto;
import ge.tikaradze.shop.entity.Role;
import ge.tikaradze.shop.exception.NotFoundException;
import ge.tikaradze.shop.repository.RoleRepository;
import ge.tikaradze.shop.service.RoleService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;

    private final ModelMapper modelMapper;

    public RoleServiceImpl(RoleRepository roleRepository, ModelMapper modelMapper) {
        this.roleRepository = roleRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<RoleDto> roles() {
        List<Role> roles = roleRepository.findAll();
        if (roles.isEmpty()) {
            throw new NotFoundException("Users Not Found");
        }
        return roles.stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public RoleDto getRole(Long id) {
        Role role = roleRepository.findRoleById(id);
        if (role == null) {
            throw new NotFoundException("User Not Found");
        }
        return toDto(role);
    }

    @Override
    public RoleDto saveRole(RoleDto roleDto) {
        return toDto(roleRepository.save(toEntity(roleDto)));
    }

    @Override
    public RoleDto updateRole(Long id, RoleDto roleDto) {
        return null;
    }

    @Override
    public void deleteRole(Long id) {
        roleRepository.deleteById(id);
    }

    @Override
    public Role getRoleByName(String name){
        return roleRepository.findRoleByName(name);
    }

    private RoleDto toDto(Role role) {
        return modelMapper.map(role, RoleDto.class);
    }

    private Role toEntity(RoleDto vacancy) {
        return modelMapper.map(vacancy, Role.class);
    }
}

