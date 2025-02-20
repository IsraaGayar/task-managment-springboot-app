package banquemisr.challenge05.taskchallenge.user.repositories;

import banquemisr.challenge05.taskchallenge.user.entities.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
//public interface UserRepository extends CrudRepository<AppUser, Long> {
public interface UserRepository extends JpaRepository<AppUser, Long> {
    Optional<AppUser> findByEmail(String email);



}

