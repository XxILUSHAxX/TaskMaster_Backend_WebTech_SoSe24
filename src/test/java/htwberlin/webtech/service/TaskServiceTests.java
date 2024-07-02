package htwberlin.webtech.service;

import htwberlin.webtech.model.Employee;
import htwberlin.webtech.model.Task;
import htwberlin.webtech.repo.EmployeeRepo;
import htwberlin.webtech.repo.TaskRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

class TaskServiceTests {

    @Mock
    private TaskRepo taskRepo;

    @Mock
    private EmployeeRepo employeeRepo;

    @InjectMocks
    private TaskService taskService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllTasks() {
        taskService.getAllTasks();
        verify(taskRepo).findAll();
    }


    @Test
    void testAddTaskWithoutDefaultEmployeeThrowsRuntimeException() {
        Task task = new Task();
        when(employeeRepo.findById(anyLong())).thenReturn(Optional.empty());

        RuntimeException exception = assertThrows(RuntimeException.class, () -> taskService.addTask(task));
        assertEquals("Default employee not found", exception.getMessage());
    }
    @Test
    void testDeleteTask() {
        Long taskId = 1L;
        taskService.deleteTask(taskId);
        verify(taskRepo).deleteById(taskId);
    }

    @Test
    void testUpdateTaskAwardPoints() {
        Employee employee = new Employee();
        employee.setPointCount(0);
        Task existingTask = new Task();
        existingTask.setId(1L);
        existingTask.setEmployee(employee);
        existingTask.setStatus("Not Done");

        Task updatedTask = new Task();
        updatedTask.setId(1L);
        updatedTask.setEmployee(employee);
        updatedTask.setStatus("Done");

        when(taskRepo.findById(1L)).thenReturn(Optional.of(existingTask));

        Task resultTask = taskService.updateTask(updatedTask);

        assertEquals(5, employee.getPointCount());
        verify(employeeRepo).save(employee);
        verify(taskRepo).save(updatedTask);
    }


    @Test
    void testUpdateTaskNotFound() {
        Task task = new Task();
        task.setId(1L);
        when(taskRepo.findById(1L)).thenReturn(Optional.empty());

        RuntimeException exception = assertThrows(RuntimeException.class, () -> taskService.updateTask(task));
        assertEquals("Task not found", exception.getMessage());
    }

    @Test
    void testGetTaskById() {
        Long taskId = 1L;
        Task task = new Task();
        when(taskRepo.findById(taskId)).thenReturn(Optional.of(task));

        Task foundTask = taskService.getTaskById(taskId);

        assertEquals(task, foundTask);
    }

    @Test
    void testGetTaskByIdNotFound() {
        Long taskId = 1L;
        when(taskRepo.findById(taskId)).thenReturn(Optional.empty());

        Task foundTask = taskService.getTaskById(taskId);

        assertNull(foundTask);
    }
}
