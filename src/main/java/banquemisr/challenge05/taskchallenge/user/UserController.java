package banquemisr.challenge05.taskchallenge.user;

import banquemisr.challenge05.taskchallenge.user.entities.AppUser;
import banquemisr.challenge05.taskchallenge.user.entities.DTO.UserCreationDTO;
import banquemisr.challenge05.taskchallenge.user.service.UserService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {
    @Autowired
    public UserService userService;

    @GetMapping("/user")
//    public String user(Authentication authentication){
    public AppUser user(@AuthenticationPrincipal AppUser user){
//        return "Welcome " + userDetails.getUsername();
//        return "Welcome " + user.getFullName();
        return user;
    }
    @GetMapping("/new")
    public String home(Authentication authentication){
        return "Welcome " + authentication.getName();
    }

    @PostMapping("/users")
    public AppUser createUser(@Valid @RequestBody UserCreationDTO user){
        return userService.createAppUser(user);
    }

    @GetMapping("/user/{id}")
//    public String user(Authentication authentication){
    public ResponseEntity<Object> getUserById(
            @PathVariable Long id,
            @AuthenticationPrincipal AppUser loggedUser){
        try {
            Object user = userService.getUserById(id, loggedUser);
            return ResponseEntity.ok(user); // 200 OK
        } catch (EntityNotFoundException ex) {
            return ResponseEntity.notFound().build(); // 404 Not Found
        }
    }

}

