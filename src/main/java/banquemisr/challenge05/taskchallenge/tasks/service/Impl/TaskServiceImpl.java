package banquemisr.challenge05.taskchallenge.tasks.service.Impl;

import banquemisr.challenge05.taskchallenge.Exceptions.CustomAppException;
import banquemisr.challenge05.taskchallenge.tasks.Entity.DTO.TaskCreationDTO;
import banquemisr.challenge05.taskchallenge.tasks.Entity.DTO.TaskRetrievalDTO;
import banquemisr.challenge05.taskchallenge.tasks.Entity.DTO.TaskUpdateDTO;
import banquemisr.challenge05.taskchallenge.tasks.Entity.Task;
import banquemisr.challenge05.taskchallenge.tasks.repository.TaskRepository;
import banquemisr.challenge05.taskchallenge.tasks.service.TaskService;
import banquemisr.challenge05.taskchallenge.user.entities.AppUser;
import banquemisr.challenge05.taskchallenge.user.repositories.UserRepository;
import banquemisr.challenge05.taskchallenge.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class TaskServiceImpl implements TaskService {

    @Autowired
    private TaskRepository taskRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserService userService;


    @Override
    public TaskRetrievalDTO saveTask(
            TaskCreationDTO taskDTO, AppUser loggedUser) throws CustomAppException {
        Task created_task = createTaskFromDTO(taskDTO, loggedUser);
        taskRepository.save(created_task);
        TaskRetrievalDTO task = convertTaskToDTO(created_task);
        return task;
    }

    @Override
    public TaskRetrievalDTO updateTask(
            UUID id, TaskUpdateDTO taskDTO, AppUser loggedUser) throws CustomAppException {
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new CustomAppException("Task not found with id:" + id));

        if (taskDTO.getPriority() != null) {
            task.setPriority(Task.Priority.valueOf(taskDTO.getPriority()));
        }
        if (taskDTO.getStatus() != null) {
            task.setStatus(Task.Status.valueOf(taskDTO.getStatus()));
        }
        if (taskDTO.getDescription() != null) {
            task.setDescription(taskDTO.getDescription());
        }
        taskRepository.save(task);
        TaskRetrievalDTO updatedTaskDTO = convertTaskToDTO(task);
        return updatedTaskDTO;
    }


    @Override
    public TaskRetrievalDTO getTask(UUID id) throws CustomAppException {
        Optional<Task> task = taskRepository.findById(id);
        if (task.isEmpty()){
            throw new CustomAppException("No Task found with id: " + id);
        }
        return convertTaskToDTO(task.get());
    }


    @Override
    public Page<TaskRetrievalDTO> getGeniricTasks(Specification<Task> spec, Pageable pageable) {
        Page<Task> tasks = taskRepository.findAll(spec, pageable);

        List<TaskRetrievalDTO> taskDTOs = tasks.getContent().stream()
                .map(this::convertTaskToDTO)
                .collect(Collectors.toList());
        Page<TaskRetrievalDTO> taskDTOPage = new PageImpl<>(taskDTOs, pageable, tasks.getTotalElements());
        return taskDTOPage;
    }

    @Override
    public void deleteTask(UUID id) throws CustomAppException {
        try {
            taskRepository.deleteById(id);
        }catch (Exception e){
            throw new CustomAppException("Cant delete Task -> " + e.getMessage());
        }
    }


    private Task createTaskFromDTO(TaskCreationDTO taskDTO, AppUser loggedUser) throws CustomAppException {
        Task task = new Task();
        task.setTitle(taskDTO.getTitle());
        task.setDescription(taskDTO.getDescription());
        task.setStatus(Task.Status.valueOf(taskDTO.getStatus()));
        task.setPriority(Task.Priority.valueOf(taskDTO.getPriority()));
        AppUser owner;
        if (loggedUser != null) {
            owner = loggedUser;
        } else {
            throw new CustomAppException("No active user found to create this task");

//            if (taskDTO.getOwner_id() != null) {
//                owner = userRepository.findById(taskDTO.getOwner_id())
//                        .orElseThrow(() -> new EntityNotFoundException("owner not found"));
//            } else {
//                throw new CustomAppException("No owner found");
//            }
        }
        task.setOwner(owner);
        return task;
    }

    private TaskRetrievalDTO convertTaskToDTO(Task createdTask) {
        TaskRetrievalDTO task = new TaskRetrievalDTO();
        task.setId(createdTask.getId());
        task.setTitle(createdTask.getTitle());
        task.setDescription(createdTask.getDescription());
        task.setStatus(createdTask.getStatus());
        task.setPriority(createdTask.getPriority());
        task.setOwner(userService.publicUserConverter(createdTask.getOwner()));
        return task;
    }


}
