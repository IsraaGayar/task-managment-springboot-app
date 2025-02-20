package banquemisr.challenge05.taskchallenge.tasks.Entity.DTO;

import banquemisr.challenge05.taskchallenge.tasks.Entity.Task;
import banquemisr.challenge05.taskchallenge.user.entities.DTO.PublicUserDto;

import java.util.UUID;

public class TaskRetrievalDTO {
    private UUID id;
    private String title;
    private String description;
    private PublicUserDto owner;
    private Task.Status status;
    private Task.Priority priority;

    public Task.Priority getPriority() {
        return priority;
    }

    public void setPriority(Task.Priority priority) {
        this.priority = priority;
    }

    public Task.Status getStatus() {
        return status;
    }

    public void setStatus(Task.Status status) {
        this.status = status;
    }

    public PublicUserDto getOwner() {
        return owner;
    }

    public void setOwner(PublicUserDto owner) {
        this.owner = owner;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }
}
