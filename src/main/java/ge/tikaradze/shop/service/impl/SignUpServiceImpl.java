package ge.tikaradze.shop.service.impl;

import ge.tikaradze.shop.dto.SignUpUserDto;
import ge.tikaradze.shop.entity.AppUser;
import ge.tikaradze.shop.entity.Role;
import ge.tikaradze.shop.repository.AppUserRepository;
import ge.tikaradze.shop.service.RoleService;
import ge.tikaradze.shop.service.SignUpService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SignUpServiceImpl implements SignUpService {


    private final AppUserRepository appUserRepository;

    private final BCryptPasswordEncoder encoder;

    private final RoleService roleService;

    public SignUpServiceImpl(BCryptPasswordEncoder encoder, RoleService roleService, AppUserRepository appUserRepository) {
        this.encoder = encoder;
        this.roleService = roleService;
        this.appUserRepository = appUserRepository;
    }


    @Override
    public void SignUp(SignUpUserDto signUpUserDto) {
        AppUser appUser = new AppUser();
        appUser.setUsername(signUpUserDto.getUsername());
        appUser.setPassword(encoder.encode(signUpUserDto.getPassword()));
        List<Role> roles = new ArrayList<>();
        Role role = roleService.getRoleByName("CLIENT");
        roles.add(role);
        appUser.setRoles(roles);
        appUserRepository.save(appUser);
    }
}
