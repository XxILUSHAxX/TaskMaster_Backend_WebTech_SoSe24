package htwberlin.webtech.model;

import jakarta.persistence.*;

@Entity
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false,updatable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name="employee_id")
    private Employee employee;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private String priority; // "Low", "Medium", "High"

    @Enumerated(EnumType.STRING)
    @Column(nullable = false,columnDefinition = "ENUM('TODO','DOING','DONE')")
    private TaskStatus status; // "To-Do", "Doing", "Done"


    //Default Constructor
    public Task(){

    }

    public Task(Employee employee,String description, String priority, TaskStatus status) {
        this.employee = employee;
        this.description = description;
        this.priority = priority;
        this.status = status;

    }

    public Employee getEmployee() {
        return employee;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public TaskStatus getStatus() {
        return status;
    }

    public void setStatus(TaskStatus status) {
        this.status = status;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }



}



