package ge.tikaradze.shop.dto;


import lombok.Data;

import java.util.List;


@Data
public class SaveUserDto {
    private String username;
    private String password;
    private List<String> roles;
}
