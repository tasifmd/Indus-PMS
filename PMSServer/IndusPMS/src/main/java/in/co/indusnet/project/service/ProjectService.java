package in.co.indusnet.project.service;

import org.springframework.stereotype.Service;

import in.co.indusnet.project.dto.ProjectDTO;
import in.co.indusnet.response.Response;

@Service
public interface ProjectService {
	public Response addProject(int employeeId , ProjectDTO projectDTO); 
}
