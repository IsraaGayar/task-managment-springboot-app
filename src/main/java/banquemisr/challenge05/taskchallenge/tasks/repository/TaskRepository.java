package banquemisr.challenge05.taskchallenge.tasks.repository;

import banquemisr.challenge05.taskchallenge.tasks.Entity.Task;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;


@Repository
public interface TaskRepository extends JpaRepository<Task, UUID> , JpaSpecificationExecutor<Task> {
    Optional<Task> findById(UUID id);
    Page<Task> findAll(Pageable pageable);
}
