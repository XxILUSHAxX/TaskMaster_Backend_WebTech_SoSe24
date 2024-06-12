package htwberlin.webtech.repo;

import htwberlin.webtech.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepo extends JpaRepository<Employee, Long> {
}


