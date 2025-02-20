package banquemisr.challenge05.taskchallenge.user.service;

import banquemisr.challenge05.taskchallenge.user.entities.AppUser;
import banquemisr.challenge05.taskchallenge.user.entities.DTO.LoginUserDTO;
import banquemisr.challenge05.taskchallenge.user.entities.DTO.RegisterUserDTO;
import banquemisr.challenge05.taskchallenge.user.repositories.UserRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final AuthenticationManager authenticationManager;

    public AuthService(
            UserRepository userRepository,
            AuthenticationManager authenticationManager,
            PasswordEncoder passwordEncoder
    ) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public AppUser signup(RegisterUserDTO input) {
        AppUser user = new AppUser();
        user.setFullName(input.getFullName());
        user.setEmail(input.getEmail());
        user.setPassword(passwordEncoder.encode(input.getPassword()));
        return userRepository.save(user);
    }

    public AppUser authenticate(LoginUserDTO input) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(input.getEmail(), input.getPassword())
            );

            AppUser myuser = userRepository.findByEmail(input.getEmail())
                    .orElseThrow();

            return myuser;

        } catch (Exception e) {
            System.out.println(e);
            return null;
            // ...
        }
    }
}

//    public User authenticate(LoginUserDTO input) {
//        try {
//            Authentication authentication = authenticationManager.authenticate(
//                    new UsernamePasswordAuthenticationToken(input.getEmail(), input.getPassword())
//            );
//            SecurityContextHolder.getContext().setAuthentication(authentication);
//            return (User) authentication.getPrincipal(); // Now safe to cast!
//        }catch (Exception e) {
//           System.out.println(e);
//           return null;
//        }
//    }
//}