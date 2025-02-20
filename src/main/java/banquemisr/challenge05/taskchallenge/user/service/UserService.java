package banquemisr.challenge05.taskchallenge.user.service;

import banquemisr.challenge05.taskchallenge.Exceptions.CustomAppException;
import banquemisr.challenge05.taskchallenge.user.entities.AppUser;
import banquemisr.challenge05.taskchallenge.user.entities.DTO.FullDetailsUserDto;
import banquemisr.challenge05.taskchallenge.user.entities.DTO.PublicUserDto;
import banquemisr.challenge05.taskchallenge.user.entities.DTO.UserCreationDTO;
import banquemisr.challenge05.taskchallenge.user.repositories.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    public AppUser createAppUser(UserCreationDTO user) {
        AppUser appUser = convertUserDTOToDb(user);
        return userRepository.save(appUser);
    }

    public Page<AppUser> getusers(Pageable pageable) throws Exception{
        Page<AppUser> users = userRepository.findAll(pageable);
        return users;
    }

    public Object getUserById(Long id, AppUser loggedUser) {
        return userRepository.findById(id)
                .map(user -> {
                    if (user.getId().equals(loggedUser.getId())) {
                        FullDetailsUserDto requestedUser = privateUserConverter(user);
                        return requestedUser;
                    }
                    PublicUserDto requestedUser = publicUserConverter(user);
                    return requestedUser;
                })
                .orElseThrow(() -> new EntityNotFoundException("User with ID " + id + " not found"));
    }

    @Override
    public UserDetails loadUserByUsername(String userEmail) {
        Optional<AppUser> user = userRepository.findByEmail(userEmail);
        return user.orElse(null);
    }

    private AppUser convertUserDTOToDb(UserCreationDTO user) {
        AppUser appUser = new AppUser();
        appUser.setEmail(user.getEmail());
        appUser.setFullName(user.getFullName());
        appUser.setPassword(user.getPassword());
        return appUser;
    }

    public FullDetailsUserDto publicUserConverter(AppUser user) {
        FullDetailsUserDto userDTO  = new FullDetailsUserDto();
        userDTO.setEmail(user.getEmail());
        userDTO.setFullName(user.getFullName());
        userDTO.setId(user.getId());
        return userDTO;
    }

    public FullDetailsUserDto privateUserConverter(AppUser user) {
        FullDetailsUserDto userDTO = publicUserConverter(user);
        userDTO.setCreatedAt(user.getCreatedAt());
        userDTO.setUpdatedAt(user.getUpdatedAt());
        return userDTO;
    }

}
