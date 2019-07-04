package in.co.indusnet.task.service;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import in.co.indusnet.employee.model.Employee;
import in.co.indusnet.employee.repository.EmployeeRepository;
import in.co.indusnet.exception.ProjectException;
import in.co.indusnet.response.Response;
import in.co.indusnet.task.dto.TaskDTO;
import in.co.indusnet.task.model.Task;
import in.co.indusnet.task.repository.TaskRepository;
import in.co.indusnet.util.ResponseHelper;

@Service("taskService")
public class TaskServiceImpl implements TaskService {

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private Environment environment;

	@Autowired
	private EmployeeRepository employeeRepository;

	@Autowired
	private TaskRepository taskRepository;
	
	@Override
	public Response addTask(int employeeId, int projectId, TaskDTO taskDTO) {
		Response response = new Response();
		Optional<Employee> projectManager = employeeRepository.findById(employeeId);
		if (!projectManager.get().getEmployeeDesignation().equals("Project Manager")  || !projectManager.get().getEmployeeDesignation().equals("Team Lead") ) {
			throw new ProjectException(environment.getProperty("unauthorisedAccess"),
					Integer.parseInt(environment.getProperty("projectExceptionCode")));
		}
		Task task = modelMapper.map(taskDTO, Task.class);
		task.setProjectId(projectId);
		taskRepository.save(task);
		response = ResponseHelper.statusInfo(environment.getProperty("taskAdded"),
				Integer.parseInt(environment.getProperty("successCode")));
		return response;
	}

	@Override
	public Response updateTask(int employeeId, int taskId, TaskDTO taskDTO) {
		Response response = new Response();
		Optional<Employee> projectManager = employeeRepository.findById(employeeId);
		if (!projectManager.get().getEmployeeDesignation().equals("Project Manager") || !projectManager.get().getEmployeeDesignation().equals("Team Lead")) {
			throw new ProjectException(environment.getProperty("unauthorisedAccess"),
					Integer.parseInt(environment.getProperty("projectExceptionCode")));
		}		
		Optional<Task> task = taskRepository.findById(taskId);
		task.get().setTaskDescription(taskDTO.getTaskDescription());
		task.get().setTaskName(taskDTO.getTaskName());
		taskRepository.save(task.get());
		response = ResponseHelper.statusInfo(environment.getProperty("taskUpdate"),
				Integer.parseInt(environment.getProperty("successCode")));
		return response;
	}

	@Override
	public List<Task> getTask(int employeeId, int projectId) {
		Optional<Employee> projectManager = employeeRepository.findById(employeeId);
		if (!projectManager.get().getEmployeeDesignation().equals("Project Manager") || !projectManager.get().getEmployeeDesignation().equals("Team Lead")) {
			throw new ProjectException(environment.getProperty("unauthorisedAccess"),
					Integer.parseInt(environment.getProperty("projectExceptionCode")));
		}	
		List<Task> task = taskRepository.findAllTaskByProjectId(projectId);
		return task;
	}

	@Override
	public Response deleteTask(int employeeId, int taskId) {
		Response response = new Response();
		Optional<Employee> projectManager = employeeRepository.findById(employeeId);
		if (!projectManager.get().getEmployeeDesignation().equals("Project Manager") || !projectManager.get().getEmployeeDesignation().equals("Team Lead")) {
			throw new ProjectException(environment.getProperty("unauthorisedAccess"),
					Integer.parseInt(environment.getProperty("projectExceptionCode")));
		}	
		
		Optional<Task> task = taskRepository.findById(taskId);
		taskRepository.delete(task.get());
		response = ResponseHelper.statusInfo(environment.getProperty("taskDelete"),
				Integer.parseInt(environment.getProperty("successCode")));
		return response;
	}

	@Override
	public Response addTaskToMember(int employeeId, int taskId, int memberId) {
		Response response = new Response();
		Optional<Employee> projectManager = employeeRepository.findById(employeeId);
		if (!projectManager.get().getEmployeeDesignation().equals("Project Manager") || !projectManager.get().getEmployeeDesignation().equals("Team Lead")) {
			throw new ProjectException(environment.getProperty("unauthorisedAccess"),
					Integer.parseInt(environment.getProperty("projectExceptionCode")));
		}	
		Optional<Task> task = taskRepository.findById(taskId);
		Optional<Employee> member = employeeRepository.findById(memberId);
		member.get().getTask().add(task.get());
		response = ResponseHelper.statusInfo(environment.getProperty("taskAssigned"),
				Integer.parseInt(environment.getProperty("successCode")));
		return response;
	}

}