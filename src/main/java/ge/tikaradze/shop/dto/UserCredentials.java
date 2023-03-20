package ge.tikaradze.shop.dto;

import lombok.Data;

// მოდელი ავტორიზაციისთვის
@Data
public class UserCredentials {
    private String username;
    private String password;
}
