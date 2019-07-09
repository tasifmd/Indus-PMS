package in.co.indusnet.employee.controller;

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

import in.co.indusnet.employee.dto.EmployeeDTO;
import in.co.indusnet.employee.dto.LoginDTO;
import in.co.indusnet.employee.dto.UpdateEmployeeDTO;
import in.co.indusnet.employee.model.Employee;
import in.co.indusnet.employee.service.EmployeeService;
import in.co.indusnet.response.LoginResponse;
import in.co.indusnet.response.Response;
import in.co.indusnet.task.model.Task;
import in.co.indusnet.util.JWTTokenHelper;

@RestController
@CrossOrigin(allowedHeaders = "*", origins = "*")
@RequestMapping("/employee")
public class EmployeeController {

	@Autowired
	private EmployeeService employeeService;

	@Autowired
	private JWTTokenHelper jwtTokenHelper;

	@PostMapping("/addprojectmanager")
	public ResponseEntity<Response> addProjectManager(@RequestBody EmployeeDTO employeeDTO) {
		Response responseStatus = employeeService.addProjectManager(employeeDTO);
		return new ResponseEntity<Response>(responseStatus, HttpStatus.OK);
	}

	@PostMapping("/login")
	public ResponseEntity<LoginResponse> login(@RequestBody LoginDTO loginDTO) {
		LoginResponse responseStatus = employeeService.login(loginDTO);
		return new ResponseEntity<LoginResponse>(responseStatus, HttpStatus.OK);
	}

	@PostMapping("/projectmanager/addmember")
	public ResponseEntity<Response> addMember(@RequestHeader String token, @RequestBody EmployeeDTO employeeDTO) {
		// int employeeId =
		// Integer.parseInt(request.getAttribute("employeeId").toString());
		int employeeId = jwtTokenHelper.decodeToken(token);
		Response responseStatus = employeeService.addMember(employeeId, employeeDTO);
		return new ResponseEntity<Response>(responseStatus, HttpStatus.OK);
	}

	@GetMapping("/projectmanager/getmember")
	public List<Employee> getMembers(@RequestHeader String token) {
//		int employeeId = Integer.parseInt(request.getAttribute("employeeId").toString());
		int employeeId = jwtTokenHelper.decodeToken(token);
		List<Employee> members = employeeService.getMembers(employeeId);
		return members;
	}

	@PutMapping("/projectmanager/updatemember/{memberId}")
	public ResponseEntity<Response> updateMember(@RequestHeader String token, @PathVariable int memberId,
			@RequestBody UpdateEmployeeDTO employeeDTO) {
//		int employeeId = Integer.parseInt(request.getAttribute("employeeId").toString());
		int employeeId = jwtTokenHelper.decodeToken(token);
		Response responseStatus = employeeService.updateMember(employeeId, memberId, employeeDTO);
		return new ResponseEntity<Response>(responseStatus, HttpStatus.OK);
	}

	@DeleteMapping("/projectmanager/deletemember/{memberId}")
	public ResponseEntity<Response> deleteMember(@RequestHeader String token, @PathVariable int memberId) {
		int employeeId = jwtTokenHelper.decodeToken(token);
		Response responseStatus = employeeService.deleteMember(employeeId, memberId);
		return new ResponseEntity<Response>(responseStatus, HttpStatus.OK);
	}

	@GetMapping("/gettaskofmember")
	public List<Task> getTaskOfMember(@RequestParam int memberId) {
		List<Task> task = employeeService.getTaskOfMember(memberId);
		return task;
	}
}
