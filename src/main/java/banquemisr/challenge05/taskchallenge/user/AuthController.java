package banquemisr.challenge05.taskchallenge.user;

import banquemisr.challenge05.taskchallenge.user.auth.JwtService;
import banquemisr.challenge05.taskchallenge.user.entities.AppUser;
import banquemisr.challenge05.taskchallenge.user.entities.DTO.LoginUserDTO;
import banquemisr.challenge05.taskchallenge.user.entities.DTO.RegisterUserDTO;
import banquemisr.challenge05.taskchallenge.user.entities.DTO.TokenResponse;
import banquemisr.challenge05.taskchallenge.user.service.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/auth")
@RestController
public class AuthController {
    private final JwtService jwtService;

    private final AuthService authService;

    public AuthController(JwtService jwtService, AuthService authService) {
        this.jwtService = jwtService;
        this.authService = authService;
    }

    @PostMapping("/signup")
    public ResponseEntity<AppUser> register(@RequestBody RegisterUserDTO registerUserDto) {
        AppUser registeredUser = authService.signup(registerUserDto);

        return ResponseEntity.ok(registeredUser);
    }

    @PostMapping("/login")
    public ResponseEntity<TokenResponse> authenticate(@RequestBody LoginUserDTO loginUserDto) {
        AppUser authenticatedUser = authService.authenticate(loginUserDto);
        String jwtToken = jwtService.generateToken(authenticatedUser);

        TokenResponse token = new TokenResponse();
        token.setToken(jwtToken);
        token.setExpiresIn(jwtService.getExpirationTime());

        return ResponseEntity.ok(token);
    }
}