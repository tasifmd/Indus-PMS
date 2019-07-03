package in.co.indusnet.project.service;

import java.util.List;

import org.springframework.stereotype.Service;

import in.co.indusnet.project.dto.ProjectDTO;
import in.co.indusnet.response.Response;

@Service
public interface ProjectService {

	public Response addProject(int employeeId, ProjectDTO projectDTO);

	public Response addTask(int employeeId, int projectId, String task);

	public List<String> getTask(int employeeId, int projectId);

	public Response removeTask(int employeeId, int projectId , String task);
	
}
