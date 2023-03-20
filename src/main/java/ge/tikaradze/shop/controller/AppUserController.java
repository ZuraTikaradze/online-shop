package ge.tikaradze.shop.controller;

import ge.tikaradze.shop.dto.AppUserDto;
import ge.tikaradze.shop.dto.SaveUserDto;
import ge.tikaradze.shop.service.AppUserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/app-user")
public class AppUserController {


    private final AppUserService appUserService;

    public AppUserController(AppUserService appUserService) {
        this.appUserService = appUserService;
    }

    @GetMapping("users")
    public List<AppUserDto> users() {
        return appUserService.users();
    }

    @GetMapping("users/{id}")
    public AppUserDto getUser(@PathVariable Long id) {
        return appUserService.getUser(id);
    }

    @PostMapping("users")
    public AppUserDto saveUser(@RequestBody SaveUserDto saveUserDto) {
        return appUserService.saveUser(saveUserDto);
    }

    @PutMapping("users/{id}")
    public AppUserDto updateUser(@PathVariable Long id, @RequestBody AppUserDto appUserDto) {
        return appUserService.updateUser(id, appUserDto);
    }

    @DeleteMapping("users/{id}")
    public void deleteUser(@PathVariable Long id) {
        appUserService.deleteUser(id);
    }

    @PutMapping("/change-user-roles/users/{id}")
    public void changeUserRole(@PathVariable Long id, @RequestBody List<String> roles) {
        appUserService.changeUserRole(id, roles);
    }
}
