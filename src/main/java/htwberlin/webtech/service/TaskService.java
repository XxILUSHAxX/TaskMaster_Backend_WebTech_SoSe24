package htwberlin.webtech.service;

import htwberlin.webtech.model.Employee;
import htwberlin.webtech.model.Task;
import htwberlin.webtech.repo.EmployeeRepo;
import htwberlin.webtech.repo.TaskRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskService {

    @Autowired
    private TaskRepo taskRepo;

    @Autowired
    private EmployeeRepo employeeRepository;

    private final int DEFAULT_EMPLOYEE_ID = 1;

    public List<Task> getAllTasks() {
        return taskRepo.findAll();
    }

    public Task addTask(Task task) {
        if (task.getEmployee() == null) {
            Employee defaultEmployee = employeeRepository.findById((long) DEFAULT_EMPLOYEE_ID)
                    .orElseThrow(() -> new RuntimeException("Default employee not found"));
            task.setEmployee(defaultEmployee);
        }
        return taskRepo.save(task);
    }

    public void deleteTask(Long id) {
        taskRepo.deleteById(id);
    }

    public Task updateTask(Task task) {
        // Check if task status is being updated to "Done"
        Task existingTask = taskRepo.findById(task.getId()).orElseThrow(() -> new RuntimeException("Task not found"));
        if (!"Done".equals(existingTask.getStatus()) && "Done".equals(task.getStatus())) {
            // Award points to employee
            Employee employee = task.getEmployee();
            if (employee != null) {
                employee.setPointCount(employee.getPointCount() + 5);
                employeeRepository.save(employee);
            }
        }
        return taskRepo.save(task);
    }


    public Task getTaskById(Long id) {
        return taskRepo.findById(id).orElse(null);
    }
}




