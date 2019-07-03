package in.co.indusnet.project.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import in.co.indusnet.employee.model.Employee;
import in.co.indusnet.employee.repository.EmployeeRepository;
import in.co.indusnet.exception.ProjectException;
import in.co.indusnet.project.dto.ProjectDTO;
import in.co.indusnet.project.model.Project;
import in.co.indusnet.project.repository.ProjectRepository;
import in.co.indusnet.response.Response;
import in.co.indusnet.util.ResponseHelper;

@Service("projectService")
@PropertySource("classpath:error.properties")
@PropertySource("classpath:message.properties")
public class ProjectServiceImpl implements ProjectService{

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private Environment environment;

	@Autowired
	private EmployeeRepository employeeRepository;
	
	@Autowired
	private ProjectRepository projectRepository;
	
	@Override
	public Response addProject(int employeeId, ProjectDTO projectDTO) {
		Response response = new Response();
		Optional<Employee> projectManager = employeeRepository.findById(employeeId);
		if(!projectManager.get().getEmployeeDesignation().equals("Project Manager")) {
			throw new ProjectException(environment.getProperty("unauthorisedAccess"), Integer.parseInt(environment.getProperty("projectExceptionCode")));
		}
		Project project =  modelMapper.map(projectDTO,Project.class);
		project.setCreatedTimeStamp(LocalDate.now());
		project.setModifiedTimeStamp(LocalDate.now());
		projectRepository.save(project);
		response = ResponseHelper.statusInfo(environment.getProperty("projectCreated"), Integer.parseInt(environment.getProperty("successCode")));
		return response;
	}

	@Override
	public Response addTask(int employeeId, int projectId, String task) {
		Response response = new Response();
		Optional<Employee> projectManager = employeeRepository.findById(employeeId);
		if(!projectManager.get().getEmployeeDesignation().equals("Project Manager")) {
			throw new ProjectException(environment.getProperty("unauthorisedAccess"), Integer.parseInt(environment.getProperty("projectExceptionCode")));
		}
		Optional<Project> project = projectRepository.findById(projectId);
		if(!project.isPresent()) {
			throw new ProjectException(environment.getProperty("projectNotExist"), Integer.parseInt(environment.getProperty("projectExceptionCode")));
		}
		project.get().getTasks().add(task);
		projectRepository.save(project.get());
		response = ResponseHelper.statusInfo(environment.getProperty("taskAdded"), Integer.parseInt(environment.getProperty("successCode")));
		return response;
	}

	@Override
	public List<String> getTask(int employeeId, int projectId) {
		Optional<Employee> projectManager = employeeRepository.findById(employeeId);
		if(!projectManager.get().getEmployeeDesignation().equals("Project Manager")) {
			throw new ProjectException(environment.getProperty("unauthorisedAccess"), Integer.parseInt(environment.getProperty("projectExceptionCode")));
		}
		Optional<Project> project = projectRepository.findById(projectId);
		if(!project.isPresent()) {
			throw new ProjectException(environment.getProperty("projectNotExist"), Integer.parseInt(environment.getProperty("projectExceptionCode")));
		}
		List<String> tasks = project.get().getTasks();
		return tasks;
	}

	@Override
	public Response removeTask(int employeeId, int projectId , String task) {
		Response response = new Response();
		Optional<Employee> projectManager = employeeRepository.findById(employeeId);
		if(!projectManager.get().getEmployeeDesignation().equals("Project Manager")) {
			throw new ProjectException(environment.getProperty("unauthorisedAccess"), Integer.parseInt(environment.getProperty("projectExceptionCode")));
		}
		Optional<Project> project = projectRepository.findById(projectId);
		if(!project.isPresent()) {
			throw new ProjectException(environment.getProperty("projectNotExist"), Integer.parseInt(environment.getProperty("projectExceptionCode")));
		}
		project.get().getTasks().remove(task);
		projectRepository.save(project.get());
		response = ResponseHelper.statusInfo(environment.getProperty("taskRemoved"), Integer.parseInt(environment.getProperty("successCode")));
		return response;
	}

}
