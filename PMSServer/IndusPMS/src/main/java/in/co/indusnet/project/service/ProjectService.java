package in.co.indusnet.project.service;

import java.util.List;

import org.springframework.stereotype.Service;

import in.co.indusnet.project.dto.ProjectDTO;
import in.co.indusnet.project.model.Project;
import in.co.indusnet.response.Response;

@Service
public interface ProjectService {

	public Response addProject(int employeeId, ProjectDTO projectDTO);
	
	public Response updateProject(int employeeId, int projectId, ProjectDTO projectDTO );
	
	public List<Project> getProject(int employeeId);
	
	public Response deleteProject(int employeeId,int projectId);
	
}
