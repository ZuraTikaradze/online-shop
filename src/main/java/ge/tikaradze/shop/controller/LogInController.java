package ge.tikaradze.shop.controller;


import ge.tikaradze.shop.dto.UserCredentials;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.net.InetAddress;

@RestController
@RequestMapping("/api/login")
public class LogInController {
    private final Environment environment;

    public LogInController(Environment environment) {
        this.environment = environment;
    }

    @PostMapping
    public String generateJWT(@RequestBody UserCredentials userCredentials) {
        String url = "http://" + InetAddress.getLoopbackAddress().getHostAddress() + ":" + environment.getProperty("local.server.port") + "/auth";//"http://localhost:8080/security/auth";
        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<UserCredentials> request = new HttpEntity<>(userCredentials);
        ResponseEntity<UserCredentials> response = restTemplate.exchange(url, HttpMethod.POST, request, UserCredentials.class);
        return response.getHeaders().get("Authorization").toString();
    }
}
