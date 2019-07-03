package in.co.indusnet.project.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import in.co.indusnet.project.dto.ProjectDTO;
import in.co.indusnet.project.service.ProjectService;
import in.co.indusnet.response.Response;

@RestController
@RequestMapping("/project")
public class ProjectController {
	
	@Autowired
	private ProjectService projectService;
	
	@PostMapping()
	public ResponseEntity<Response> addProject(HttpServletRequest request,@RequestHeader String token,@RequestBody ProjectDTO projectDTO) {
		int employeeId = Integer.parseInt(request.getAttribute("employeeId").toString());
		Response responseStatus = projectService.addProject(employeeId, projectDTO);
		return new ResponseEntity<Response> (responseStatus,HttpStatus.OK);
	}
}
