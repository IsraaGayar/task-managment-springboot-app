package banquemisr.challenge05.taskchallenge.user.auth;

import banquemisr.challenge05.taskchallenge.user.entities.AppUser;
import banquemisr.challenge05.taskchallenge.user.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class UserIntializer implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    public UserIntializer(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) throws Exception {
        try{
            AppUser mainUser = new AppUser(
                    "User Main",
                    "main@user.com",
                    passwordEncoder.encode("password"));
            AppUser adminUser = new AppUser(
                "Admin Main",
                "admin@user.com",
                    passwordEncoder.encode("password"));
//            adminUser.setRoles("ADMIN,USER");
            userRepository.save(mainUser);
            userRepository.save(adminUser);
        }catch (Exception e) {
            System.out.println(e);
        }
    }
}
