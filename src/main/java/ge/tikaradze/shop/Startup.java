package ge.tikaradze.shop;

import ge.tikaradze.shop.entity.AppUser;
import ge.tikaradze.shop.entity.Role;
import ge.tikaradze.shop.repository.AppUserRepository;
import ge.tikaradze.shop.repository.RoleRepository;
import org.springframework.boot.context.event.ApplicationReadyEvent;

import org.springframework.context.event.EventListener;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Component
public class Startup {


    private final AppUserRepository appUserRepository;

    private final RoleRepository roleRepository;

    private final BCryptPasswordEncoder encoder;

    public Startup(AppUserRepository appUserRepository, RoleRepository roleRepository, BCryptPasswordEncoder encoder) {
        this.appUserRepository = appUserRepository;
        this.roleRepository = roleRepository;
        this.encoder = encoder;
    }

    @EventListener(ApplicationReadyEvent.class)
    public void createRole() {
        if (roleRepository.findRoleByName("ADMIN") != null) {
            return;
        }
        Role role = new Role();
        role.setName("ADMINISTRATOR");
        roleRepository.save(role);

    }

    @Transactional
    @EventListener(ApplicationReadyEvent.class)
    public void createUser() {
        if (appUserRepository.findByUsername("root") != null) {
            return;
        }
        AppUser appUser = new AppUser();
        appUser.setUsername("root");
        appUser.setPassword(encoder.encode("123")); // @ToDo add pwd as envr variable
        List<Role> roles = new ArrayList<>();
        roles.add(roleRepository.findRoleByName("ADMINISTRATOR"));
        appUser.setRoles(roles);
        appUserRepository.save(appUser);
    }
}