package ge.tikaradze.shop.controller;

import ge.tikaradze.shop.dto.SignUpUserDto;
import ge.tikaradze.shop.service.SignUpService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/sign-up")
public class SignUpController {

    private final SignUpService signUpService;

    public SignUpController(SignUpService signUpService) {
        this.signUpService = signUpService;
    }

    @PostMapping
    public void signUp(@RequestBody SignUpUserDto signUpUserDto) {
        signUpService.SignUp(signUpUserDto);
    }
}
