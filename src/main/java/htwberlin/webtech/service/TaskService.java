package htwberlin.webtech.service;

import htwberlin.webtech.model.Task;
import htwberlin.webtech.repo.TaskRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskService {

    @Autowired
    private TaskRepo taskRepo;

    public List<Task> getAllTasks() {
        return taskRepo.findAll();
    }

    public Task addTask(Task task) {
        return taskRepo.save(task);
    }

    public void deleteTask(Long id) {
        taskRepo.deleteById(id);
    }

    public Task updateTask(Task task) {
        return taskRepo.save(task);
    }

    public Task getTaskById(Long id) {
        return taskRepo.findById(id).orElse(null);
    }
}
