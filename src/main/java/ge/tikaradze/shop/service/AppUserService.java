package ge.tikaradze.shop.service;


import ge.tikaradze.shop.dto.AppUserDto;
import ge.tikaradze.shop.dto.SaveUserDto;

import java.util.List;

public interface AppUserService {

    List<AppUserDto> users();

    AppUserDto getUser(Long id);

    AppUserDto saveUser(SaveUserDto saveUserDto);

    AppUserDto updateUser(Long id, AppUserDto appUserDto);

    void deleteUser(Long id);

    void changeUserRole(Long userId, List<String> roleName);
}
