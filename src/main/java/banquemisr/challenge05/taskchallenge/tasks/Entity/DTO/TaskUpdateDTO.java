package banquemisr.challenge05.taskchallenge.tasks.Entity.DTO;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

import java.util.Date;

public class TaskUpdateDTO {

    private String description;

    @JsonFormat(pattern = "yyyy-MM-dd")
    @Future(message = "Due date must be in the future")
    private Date dueDate;

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    //    @NotNull(message = "owner_id is required")
    private Long owner_id;

    @Pattern(regexp = "TODO|INPROGRESS|DONE", message = "Invalid status value")
    private String status = "TODO";

    @Pattern(regexp = "LOW|MEDIUM|HIGH", message = "Invalid status value")
    private String priority = "MEDIUM";

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Long getOwner_id() {
        return owner_id;
    }

    public void setOwner_id(Long owner_id) {
        this.owner_id = owner_id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


}
