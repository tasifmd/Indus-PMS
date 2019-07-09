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
public class ProjectServiceImpl implements ProjectService {

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
		if (!projectManager.get().getEmployeeDesignation().equals("Project Manager")) {
			throw new ProjectException(environment.getProperty("unauthorisedAccess"),
					Integer.parseInt(environment.getProperty("projectExceptionCode")));
		}
		Project project = modelMapper.map(projectDTO, Project.class);
		project.setCreatedTimeStamp(LocalDate.now());
		project.setModifiedTimeStamp(LocalDate.now());
		projectRepository.save(project);
		response = ResponseHelper.statusInfo(environment.getProperty("projectCreated"),
				Integer.parseInt(environment.getProperty("successCode")));
		return response;
	}

	@Override
	public Response updateProject(int employeeId, int projectId, ProjectDTO projectDTO) {
		Response response = new Response();
		Optional<Employee> projectManager = employeeRepository.findById(employeeId);
		if (!projectManager.get().getEmployeeDesignation().equals("Project Manager")) {
			throw new ProjectException(environment.getProperty("unauthorisedAccess"),
					Integer.parseInt(environment.getProperty("projectExceptionCode")));
		}
		Optional<Project> project = projectRepository.findById(projectId);
		if (!project.isPresent()) {
			throw new ProjectException(environment.getProperty("projectNotExist"),
					Integer.parseInt(environment.getProperty("projectExceptionCode")));
		}
		project.get().setProjectName(projectDTO.getProjectName());
		project.get().setProjectDescription(projectDTO.getProjectDescription());
		project.get().setModifiedTimeStamp(LocalDate.now());
		projectRepository.save(project.get());
		response = ResponseHelper.statusInfo(environment.getProperty("projectCreated"),
				Integer.parseInt(environment.getProperty("successCode")));
		return response;
	}

	@Override
	public List<Project> getProject(int employeeId){
		Optional<Employee> projectManager = employeeRepository.findById(employeeId);
//		if (!projectManager.get().getEmployeeDesignation().equals("Project Manager")) {
//			throw new ProjectException(environment.getProperty("unauthorisedAccess"),
//					Integer.parseInt(environment.getProperty("projectExceptionCode")));
//		}
		List<Project> projects = projectRepository.findAll();
		return projects;
	}
	
	@Override
	public Response deleteProject(int employeeId,int projectId) {
		Response response = new Response();
		Optional<Employee> projectManager = employeeRepository.findById(employeeId);
		if (!projectManager.get().getEmployeeDesignation().equals("Project Manager")) {
			throw new ProjectException(environment.getProperty("unauthorisedAccess"),
					Integer.parseInt(environment.getProperty("projectExceptionCode")));
		}
		Optional<Project> project = projectRepository.findById(projectId);
		if (!project.isPresent()) {
			throw new ProjectException(environment.getProperty("projectNotExist"),
					Integer.parseInt(environment.getProperty("projectExceptionCode")));
		}
		projectRepository.delete(project.get());
		response = ResponseHelper.statusInfo(environment.getProperty("projectDeleted"),
				Integer.parseInt(environment.getProperty("successCode")));
		return response;
	}

	@Override
	public Response addProjectToEmployee(int employeeId, int projectId, int memberId) {
		Response response = new Response();
		Optional<Employee> projectManager = employeeRepository.findById(employeeId);
		if (!projectManager.get().getEmployeeDesignation().equals("Project Manager")) {
			throw new ProjectException(environment.getProperty("unauthorisedAccess"),
					Integer.parseInt(environment.getProperty("projectExceptionCode")));
		}
		Optional<Project> project = projectRepository.findById(projectId);
		if (!project.isPresent()) {
			throw new ProjectException(environment.getProperty("projectNotExist"),
					Integer.parseInt(environment.getProperty("projectExceptionCode")));
		}
		
		Optional<Employee> employee = employeeRepository.findById(memberId);
		project.get().getEmployee().add(employee.get());
		projectRepository.save(project.get());
		response = ResponseHelper.statusInfo(environment.getProperty("projectAssigned"),
				Integer.parseInt(environment.getProperty("successCode")));
		return response;
	}

}
