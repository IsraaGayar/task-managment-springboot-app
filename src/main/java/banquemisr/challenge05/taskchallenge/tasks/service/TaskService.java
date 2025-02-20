package banquemisr.challenge05.taskchallenge.tasks.service;


import banquemisr.challenge05.taskchallenge.Exceptions.CustomAppException;
import banquemisr.challenge05.taskchallenge.tasks.Entity.DTO.TaskCreationDTO;
import banquemisr.challenge05.taskchallenge.tasks.Entity.DTO.TaskRetrievalDTO;
import banquemisr.challenge05.taskchallenge.tasks.Entity.DTO.TaskUpdateDTO;
import banquemisr.challenge05.taskchallenge.tasks.Entity.Task;
import banquemisr.challenge05.taskchallenge.user.entities.AppUser;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import java.util.UUID;

public interface TaskService {

    public TaskRetrievalDTO saveTask(TaskCreationDTO task, AppUser loggedUser) throws CustomAppException;

    public TaskRetrievalDTO updateTask(
            UUID id, TaskUpdateDTO task, AppUser loggedUser) throws CustomAppException;

    public TaskRetrievalDTO reAssignTask(
            UUID id, TaskUpdateDTO task, AppUser loggedUser) throws CustomAppException;

    public TaskRetrievalDTO getTask(UUID id) throws CustomAppException;

    Page<TaskRetrievalDTO> getGeniricTasks(Specification<Task> spec, Pageable pageable, AppUser loggedUser);

    public void deleteTask(UUID id, AppUser loggedUser) throws CustomAppException;
}
