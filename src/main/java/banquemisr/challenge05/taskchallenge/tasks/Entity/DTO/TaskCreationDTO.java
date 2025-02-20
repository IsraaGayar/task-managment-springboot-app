package banquemisr.challenge05.taskchallenge.tasks.Entity.DTO;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.*;

import java.util.Date;

public class TaskCreationDTO {

    @NotBlank(message = "title is required")
    @Size(max = 100, message = "title must be less than 100 characters")
    private String title;

    @NotBlank(message = "description is required")
    private String description;

//    @NotNull(message = "owner_id is required")
    private Long owner_id;

    @Pattern(regexp = "TODO|INPROGRESS|DONE", message = "Invalid status value")
    private String status = "TODO";

    @Pattern(regexp = "LOW|MEDIUM|HIGH", message = "Invalid status value")
    private String priority = "MEDIUM";

    @JsonFormat(pattern = "yyyy-MM-dd")
    @Future(message = "Due date must be in the future")
    private Date dueDate;

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getOwner_id() {
        return owner_id;
    }

    public void setOwner_id(Long owner_id) {
        this.owner_id = owner_id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }
}
