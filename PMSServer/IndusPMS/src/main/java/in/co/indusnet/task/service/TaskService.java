package in.co.indusnet.task.service;

import java.util.List;

import org.springframework.stereotype.Service;

import in.co.indusnet.response.Response;
import in.co.indusnet.task.dto.TaskDTO;
import in.co.indusnet.task.model.Task;

@Service
public interface TaskService {
	
	public Response addTask(int employeeId, int projectId, TaskDTO taskDTO);
	
	public Response updateTask(int employeeId, int taskId, TaskDTO taskDTO);
	
	public List<Task> getTask(int employeeId,int projectId);

	public Response deleteTask(int employeeId, int taskId);
	
	public Response addTaskToMember(int employeeId, int taskId,int memberId);

	public Response statusTask(int taskId);
	
	public List<Task> getAllTaskOfEmployee(int employeeId);
}
