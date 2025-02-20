package banquemisr.challenge05.taskchallenge.tasks.controller;


import banquemisr.challenge05.taskchallenge.Exceptions.CustomAppException;
import banquemisr.challenge05.taskchallenge.GenericFilterSpecification;
import banquemisr.challenge05.taskchallenge.tasks.Entity.DTO.TaskCreationDTO;
import banquemisr.challenge05.taskchallenge.tasks.Entity.DTO.TaskRetrievalDTO;
import banquemisr.challenge05.taskchallenge.tasks.Entity.DTO.TaskUpdateDTO;
import banquemisr.challenge05.taskchallenge.tasks.Entity.Task;
import banquemisr.challenge05.taskchallenge.tasks.service.TaskService;
import banquemisr.challenge05.taskchallenge.user.entities.AppUser;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
public class TaskController {
    @Autowired
    public TaskService taskService;

    @GetMapping("/tasks")
    public Page<TaskRetrievalDTO> ListTasks(
            @PageableDefault(page = 0, size = 10, sort = "title", direction = Sort.Direction.ASC) Pageable pageable,
            @RequestParam(required = false) Map<String, String> filters // Map of filter fields and values
    ) {
        List<String> keysToRemove = Arrays.asList("sort", "page", "size");
        filters.keySet().removeAll(keysToRemove);
        Specification<Task> spec = Specification.where(null);
        for (Map.Entry<String, String> entry : filters.entrySet()) {
            spec = spec.and(GenericFilterSpecification.filterBy(entry.getKey(), entry.getValue()));}
        Page<TaskRetrievalDTO> tasks = taskService.getGeniricTasks(spec, pageable);
        return tasks;
    }


    @GetMapping("/tasks/{id}")
    public ResponseEntity<?>  getTaks(
            @PathVariable UUID id) throws CustomAppException {
        return ResponseEntity.ok(taskService.getTask(id));
    }

    @PatchMapping("/tasks/{id}") // Update all fields in one endpoint
    public ResponseEntity<?>  patchTask(
            @PathVariable UUID id,
            @Valid @RequestBody TaskUpdateDTO taskUpdateDTO,
            @AuthenticationPrincipal AppUser loggedUser) throws CustomAppException{
        return ResponseEntity.ok(
                taskService.updateTask(id, taskUpdateDTO, loggedUser));


    }

    @PostMapping("/tasks/")
    public ResponseEntity<?> createTask (
            @Valid @RequestBody TaskCreationDTO task,
            @AuthenticationPrincipal AppUser loggedUser) throws CustomAppException{
        return ResponseEntity.ok(taskService.saveTask(task, loggedUser));
    }

    @PostMapping("/tasks/{id}/re-assign")
    public ResponseEntity<?> reAssignTask(
            @PathVariable UUID id,
            @Valid @RequestBody TaskUpdateDTO task,
            @AuthenticationPrincipal AppUser loggedUser) throws CustomAppException{
        if (loggedUser.getAdmin()){
            return ResponseEntity.ok(taskService.reAssignTask(id, task, loggedUser));
        }else{
            throw new CustomAppException("Only Admin Users are allowed to reassign tasks");
        }
    }

    @DeleteMapping("/tasks/{id}")
    public ResponseEntity<?> deleteTask(
            @PathVariable UUID id,
            @AuthenticationPrincipal AppUser loggedUser) throws CustomAppException{
        taskService.deleteTask(id, loggedUser);
        ResponseEntity res = new ResponseEntity(HttpStatus.NO_CONTENT);
        return ResponseEntity.noContent().build();
    }

}
