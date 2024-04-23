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

    @Column(nullable = false)
    private String status; // "To-Do", "Doing", "Done"


    //Default Constructor
    public Task(){

    }

    public Task(Employee employee,String description, String priority, String status) {
        this.employee = employee;
        this.description = description;
        this.priority = priority;
        this.status = status;

    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }



}

