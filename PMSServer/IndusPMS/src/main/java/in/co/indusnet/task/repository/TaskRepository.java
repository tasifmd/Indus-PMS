package in.co.indusnet.task.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import in.co.indusnet.task.model.Task;

@Repository
public interface TaskRepository extends JpaRepository<Task, Integer> {
	List<Task> findAllTaskByProjectId(int projectId);
}
