package htwberlin.webtech.service;

import htwberlin.webtech.model.Employee;
import htwberlin.webtech.repo.EmployeeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepo employeeRepository;


    public Employee getEmployeeById(Long id) {
        return employeeRepository.findById(id).orElse(null);
    }

    public Employee addEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    public Employee updateEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }


    public void deleteEmployee(Long id) {
        employeeRepository.deleteById(id);
    }
}
