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

//    @NonNull
//    @Override
//    Optional<Task> findById(Long aLong);

//    Optional<Task> findById(UUID id);
    Optional<Task> findById(UUID id);

    Page<Task> findAll(Pageable pageable);


    //    Page<Task> findByPriority(Task.Priority priority, Pageable pageable);
//    Page<Task> findByCompleted(Boolean completed, Pageable pageable);
//    Page<Task> findByPriorityAndCompleted(Task.Priority priority, Boolean completed, Pageable pageable);


//    @Override
//    <S extends Task> List<S> findAll(Example<S> example);
}
