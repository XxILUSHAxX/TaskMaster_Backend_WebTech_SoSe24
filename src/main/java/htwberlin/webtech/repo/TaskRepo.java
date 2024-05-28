package htwberlin.webtech.repo;

import htwberlin.webtech.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepo extends JpaRepository<Task, Long> {
}


