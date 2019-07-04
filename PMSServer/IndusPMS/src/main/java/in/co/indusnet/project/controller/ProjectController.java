package in.co.indusnet.project.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import in.co.indusnet.project.dto.ProjectDTO;
import in.co.indusnet.project.model.Project;
import in.co.indusnet.project.service.ProjectService;
import in.co.indusnet.response.Response;

@RestController
@RequestMapping("/project")
public class ProjectController {
	
	@Autowired
	private ProjectService projectService;
	
	@PostMapping
	public ResponseEntity<Response> addProject(HttpServletRequest request,@RequestHeader String token,@RequestBody ProjectDTO projectDTO) {
		int employeeId = Integer.parseInt(request.getAttribute("employeeId").toString());
		Response responseStatus = projectService.addProject(employeeId, projectDTO);
		return new ResponseEntity<Response> (responseStatus,HttpStatus.OK);
	}
	
	@PutMapping("/{projectId}")
	public ResponseEntity<Response> updateProject(HttpServletRequest request, @RequestHeader String token,@PathVariable int projectId,@RequestBody ProjectDTO projectDTO) {
		int employeeId = Integer.parseInt(request.getAttribute("employeeId").toString());
		Response responseStatus = projectService.updateProject(employeeId, projectId , projectDTO);
		return new ResponseEntity<Response>(responseStatus,HttpStatus.OK);
	}	
	
	@GetMapping
	public List<Project>  getProject(HttpServletRequest request,@RequestHeader String token) {
		int employeeId = Integer.parseInt(request.getAttribute("employeeId").toString());
		List<Project> projects = projectService.getProject(employeeId);
		return projects;
	}
	
	@DeleteMapping("/{projectId}")
	public ResponseEntity<Response> deleteProject(HttpServletRequest request,@RequestHeader String token,@PathVariable int projectId) {
		int employeeId = Integer.parseInt(request.getAttribute("employeeId").toString());
		Response responseStatus = projectService.deleteProject(employeeId, projectId);
		return new ResponseEntity<Response>(responseStatus,HttpStatus.OK);
	}
	
//	@PostMapping("/addtask/{projectId}")
//	public ResponseEntity<Response> addTask(HttpServletRequest request,@RequestHeader String token,@PathVariable int projectId,@RequestParam String task){
//		int employeeId = Integer.parseInt(request.getAttribute("employeeId").toString());
//		Response responseStatus = projectService.addTask(employeeId, projectId, task);
//		return new ResponseEntity<Response>(responseStatus,HttpStatus.OK);
//	}
//	
//	@GetMapping("/gettasks/{projectId}")
//	public List<String> getTasks(HttpServletRequest request,@RequestHeader String token,@PathVariable int projectId){
//		int employeeId = Integer.parseInt(request.getAttribute("employeeId").toString());
//		List<String> task = projectService.getTask(employeeId, projectId);
//		return task;
//	}
//	
//	@PutMapping("/removetask/{projectId}")
//	public ResponseEntity<Response> removeTask(HttpServletRequest request,@RequestHeader String token,@PathVariable int projectId,@RequestParam String task) {
//		int employeeId = Integer.parseInt(request.getAttribute("employeeId").toString());
//		Response responseStatus = projectService.removeTask(employeeId, projectId, task);
//		return new ResponseEntity<Response>(responseStatus,HttpStatus.OK);
//	}
}
