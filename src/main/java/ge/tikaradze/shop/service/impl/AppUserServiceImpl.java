package ge.tikaradze.shop.service.impl;

import ge.tikaradze.shop.dto.AppUserDto;
import ge.tikaradze.shop.dto.SaveUserDto;
import ge.tikaradze.shop.entity.AppUser;
import ge.tikaradze.shop.entity.Role;
import ge.tikaradze.shop.exception.NotFoundException;
import ge.tikaradze.shop.repository.AppUserRepository;
import ge.tikaradze.shop.service.AppUserService;
import ge.tikaradze.shop.service.RoleService;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AppUserServiceImpl implements AppUserService {

    private final AppUserRepository appUserRepository;

    private final RoleService roleService;


    private final ModelMapper modelMapper;


    private final BCryptPasswordEncoder encoder;


    public AppUserServiceImpl(AppUserRepository appUserRepository, ModelMapper modelMapper, RoleService roleService, BCryptPasswordEncoder encoder) {
        this.appUserRepository = appUserRepository;
        this.modelMapper = modelMapper;
        this.roleService = roleService;
        this.encoder = encoder;
    }

    @Override
    public List<AppUserDto> users() {
        List<AppUser> users = appUserRepository.findAll();
        if (users.isEmpty()) {
            throw new NotFoundException("Users Not Found");
        }
        return users.stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public AppUserDto getUser(Long id) {
        AppUser appUser = appUserRepository.findAppUserById(id);
        if (appUser == null) {
            throw new NotFoundException("User Not Found");
        }
        return toDto(appUser);
    }

    @Override
    public AppUserDto saveUser(SaveUserDto saveUserDto) {
        AppUser appUser = new AppUser();
        appUser.setUsername(saveUserDto.getUsername());
        appUser.setPassword(encoder.encode(saveUserDto.getPassword()));
        List<Role> roles = new ArrayList<>();
        for (String roleName : saveUserDto.getRoles()) {
            Role role = roleService.getRoleByName(roleName);
            roles.add(role);
        }
        appUser.setRoles(roles);

        return toDto(appUserRepository.save(appUser));
    }

    @Override
    public AppUserDto updateUser(Long id, AppUserDto appUserDto) {
        AppUser appUser = appUserRepository.findAppUserById(id);
        appUser.setUsername(appUserDto.getUsername());
        appUser.setPassword(appUserDto.getPassword());
        return toDto(appUser);
    }

    @Override
    public void deleteUser(Long id) {
        appUserRepository.deleteById(id);
    }

    @Override
    public void changeUserRole(Long userId, List<String> roleNames) {
        AppUser appUser = appUserRepository.findAppUserById(userId);
        List<Role> roles = new ArrayList<>();
        for (String roleName : roleNames) {
            roles.add(roleService.getRoleByName(roleName));
        }
        appUser.setRoles(roles);
        appUserRepository.save(appUser);
    }

    private AppUserDto toDto(AppUser appUser) {
        return modelMapper.map(appUser, AppUserDto.class);
    }

    private AppUser toEntity(AppUserDto appUserDto) {
        return modelMapper.map(appUserDto, AppUser.class);
    }
}
