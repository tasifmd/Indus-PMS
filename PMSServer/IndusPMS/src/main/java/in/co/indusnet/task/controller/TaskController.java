package in.co.indusnet.task.controller;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import in.co.indusnet.response.Response;
import in.co.indusnet.task.dto.TaskDTO;
import in.co.indusnet.task.model.Task;
import in.co.indusnet.task.service.TaskService;

@RestController
@RequestMapping("/task")
public class TaskController {

	@Autowired
	private TaskService taskService;
	
	@PostMapping("/{projectId}")
	public ResponseEntity<Response> addTask(HttpServletRequest request,@RequestHeader String token , @PathVariable int projectId , @RequestBody TaskDTO taskDTO) {
		int employeeId = Integer.parseInt(request.getAttribute("employeeId").toString());
		Response responseStatus = taskService.addTask(employeeId, projectId, taskDTO);
		return new ResponseEntity<Response>(responseStatus,HttpStatus.OK);
	}
	
	@PutMapping("/{taskId}")
	public ResponseEntity<Response> updateTask(HttpServletRequest request,@RequestHeader String token,@PathVariable int taskId,@RequestBody TaskDTO taskDTO) { 
		int employeeId = Integer.parseInt(request.getAttribute("employeeId").toString());
		Response responseStatus = taskService.updateTask(employeeId, taskId, taskDTO);
		return new ResponseEntity<Response>(responseStatus,HttpStatus.OK);
	}
	
	@GetMapping("/{projectId}")
	public List<Task> getTask(HttpServletRequest request,@RequestHeader String token, @PathVariable int projectId) {
		int employeeId = Integer.parseInt(request.getAttribute("employeeId").toString());
		List<Task> task = taskService.getTask(employeeId, projectId);
		return task;
	}
	
	@DeleteMapping("/{taskId}")
	public ResponseEntity<Response> deleteTask(HttpServletRequest request,@RequestHeader String token ,@PathVariable int taskId) {
		int employeeId = Integer.parseInt(request.getAttribute("employeeId").toString());
		Response responseStatus = taskService.deleteTask(employeeId,taskId);
		return new ResponseEntity<Response>(responseStatus,HttpStatus.OK);
	}
	
	@PostMapping("/addtasktomember")
	public ResponseEntity<Response> addTaskToMember(HttpServletRequest request , @RequestHeader String token,@RequestParam int taskId,@RequestParam int memberId) {
		int employeeId = Integer.parseInt(request.getAttribute("employeeId").toString());
		Response responseStatus = taskService.addTaskToMember(employeeId, taskId, memberId);
		return new ResponseEntity<Response>(responseStatus,HttpStatus.OK);
	}
	@PutMapping("/status")
	public ResponseEntity<Response> taskStatus(HttpServletRequest request , @RequestHeader String token,@RequestParam int taskId) {
		int employeeId = Integer.parseInt(request.getAttribute("employeeId").toString());
		Response responseStatus = taskService.statusTask(taskId);
		return new ResponseEntity<Response>(responseStatus,HttpStatus.OK);
	}
}
