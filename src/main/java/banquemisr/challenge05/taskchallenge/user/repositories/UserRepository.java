package banquemisr.challenge05.taskchallenge.user.repositories;

import banquemisr.challenge05.taskchallenge.user.entities.AppUser;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface UserRepository extends JpaRepository<AppUser, Long> {
//    Optional<AppUser> findByEmail(String email);
    @Query("SELECT u FROM AppUser u JOIN FETCH u.roles WHERE u.email = :email")
    Optional<AppUser> findByEmail(@Param("email") String email);

    Page<AppUser> findAll(Pageable pageable);


}

