package in.co.indusnet.project.controller;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import in.co.indusnet.project.dto.ProjectDTO;
import in.co.indusnet.project.model.Project;
import in.co.indusnet.project.service.ProjectService;
import in.co.indusnet.response.Response;
import in.co.indusnet.util.JWTTokenHelper;

@RestController
@CrossOrigin(allowedHeaders = "*" ,origins = "*")
@RequestMapping("/project")
public class ProjectController {
	
	@Autowired
	private JWTTokenHelper jwtTokenHelper;
	@Autowired
	private ProjectService projectService;
	
	@PostMapping
	public ResponseEntity<Response> addProject(@RequestHeader String token,@RequestBody ProjectDTO projectDTO) {
		//int employeeId = Integer.parseInt(request.getAttribute("employeeId").toString());
		int employeeId = jwtTokenHelper.decodeToken(token);
		Response responseStatus = projectService.addProject(employeeId, projectDTO);
		return new ResponseEntity<Response> (responseStatus,HttpStatus.OK);
	}
	
	@PutMapping("/{projectId}")
	public ResponseEntity<Response> updateProject(@RequestHeader String token,@PathVariable int projectId,@RequestBody ProjectDTO projectDTO) {
		//int employeeId = Integer.parseInt(request.getAttribute("employeeId").toString());
		int employeeId = jwtTokenHelper.decodeToken(token);
		Response responseStatus = projectService.updateProject(employeeId, projectId , projectDTO);
		return new ResponseEntity<Response>(responseStatus,HttpStatus.OK);
	}	
	
	@GetMapping
	public List<Project>  getProject(@RequestHeader String token) {
		//int employeeId = Integer.parseInt(request.getAttribute("employeeId").toString());
		int employeeId = jwtTokenHelper.decodeToken(token);
		List<Project> projects = projectService.getProject(employeeId);
		return projects;
	}
	
	@DeleteMapping("/{projectId}")
	public ResponseEntity<Response> deleteProject(@RequestHeader String token,@PathVariable int projectId) {
		//int employeeId = Integer.parseInt(request.getAttribute("employeeId").toString());
		int employeeId = jwtTokenHelper.decodeToken(token);
		Response responseStatus = projectService.deleteProject(employeeId, projectId);
		return new ResponseEntity<Response>(responseStatus,HttpStatus.OK);
	}
	
	@PostMapping("/addprojecttomember")
	public ResponseEntity<Response> addProjectToMember(@RequestHeader String token,@RequestParam int projectId,@RequestParam int memberId) {
		//int employeeId = Integer.parseInt(request.getAttribute("employeeId").toString());
		int employeeId = jwtTokenHelper.decodeToken(token);
		Response responseStatus = projectService.addProjectToEmployee(employeeId, projectId, memberId);
		return new ResponseEntity<Response>(responseStatus,HttpStatus.OK);
	}
 }
