package banquemisr.challenge05.taskchallenge.user;

import banquemisr.challenge05.taskchallenge.tasks.Entity.DTO.TaskRetrievalDTO;
import banquemisr.challenge05.taskchallenge.user.entities.AppUser;
import banquemisr.challenge05.taskchallenge.user.entities.DTO.UserCreationDTO;
import banquemisr.challenge05.taskchallenge.user.service.UserService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {
    @Autowired
    public UserService userService;

    @GetMapping("/home")
    public String home(){
        return "Welcome Home";
    }

//    @PostMapping("/users")
//    public AppUser createUser(@Valid @RequestBody UserCreationDTO user){
//        return userService.createAppUser(user);
//    }

    @PreAuthorize("hasAuthority('ADMIN_ROLE')")// More common and preferred way
    @GetMapping("/user")
    public ResponseEntity<?> getUserById(
            @PageableDefault(page = 0, size = 10, sort = "fullName", direction = Sort.Direction.ASC) Pageable pageable,
            @AuthenticationPrincipal AppUser loggedUser) throws Exception{
        return ResponseEntity.ok(userService.getusers(pageable));

//        try {
//            Object user = userService.getUserById(id, loggedUser);
//            return ResponseEntity.ok(user); // 200 OK
//        } catch (EntityNotFoundException ex) {
//            return ResponseEntity.notFound().build(); // 404 Not Found
//        }
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<?> getUserById(
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

