package banquemisr.challenge05.taskchallenge.tasks.Entity;

import banquemisr.challenge05.taskchallenge.user.entities.AppUser;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.util.Date;
import java.util.UUID;

@Entity
@Table
@NoArgsConstructor
@AllArgsConstructor
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(columnDefinition = "uuid")
    private UUID id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = true, columnDefinition = "TEXT")
    private String description;

    @ManyToOne  // Many tasks to one user (owner)
    @JoinColumn(name = "owner_id", nullable = false) // Foreign key column
    private AppUser owner; // The user who owns this task

//    @Pattern(regexp = "PENDING|IN_PROGRESS|COMPLETED", message = "Invalid status value") // Validation
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Status status = Status.TODO;


    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Priority priority = Priority.MEDIUM;

    @Column(name = "duedate")
    private Date dueDate;

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }


    public enum Priority {
        LOW, MEDIUM, HIGH
    }

    public AppUser getOwner() {
        return owner;
    }

    public void setOwner(AppUser owner) {
        this.owner = owner;
    }

    public enum Status {
        TODO, INPROGRESS, DONE
    }

    public UUID getId() {
        return id;
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

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
//        if (check if status in Status.values) {
//            throw new MyCustomException("invalid status"); // Create a custom exception
//        }
        this.status = status;
    }

    public Priority getPriority() {
        return priority;
    }

    public void setPriority(Priority priority) {
        this.priority = priority;
    }
}
