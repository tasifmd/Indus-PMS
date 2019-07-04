package in.co.indusnet.employee.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import in.co.indusnet.employee.dto.EmployeeDTO;
import in.co.indusnet.employee.dto.LoginDTO;
import in.co.indusnet.employee.model.Employee;
import in.co.indusnet.employee.service.EmployeeService;
import in.co.indusnet.response.LoginResponse;
import in.co.indusnet.response.Response;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

	@Autowired
	EmployeeService employeeService;

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
	public ResponseEntity<Response> addMember(HttpServletRequest request,@RequestHeader String token ,@RequestBody EmployeeDTO employeeDTO) {
		int employeeId = Integer.parseInt(request.getAttribute("employeeId").toString());
		Response responseStatus = employeeService.addMember(employeeId, employeeDTO);
		return new ResponseEntity<Response>(responseStatus, HttpStatus.OK);
	}
	
	@GetMapping("/projectmanager/getmember")
	public List<Employee> getMembers(HttpServletRequest request ,@RequestHeader String token) {
		int employeeId = Integer.parseInt(request.getAttribute("employeeId").toString());
		List<Employee> members = employeeService.getMembers(employeeId);
		return members;
	}
	
	@PutMapping("/projectmanager/updatemember/{memberId}")
	public ResponseEntity<Response> updateMember(HttpServletRequest request , @RequestHeader String token,@PathVariable int memberId  ,@RequestBody EmployeeDTO employeeDTO) {
		int employeeId = Integer.parseInt(request.getAttribute("employeeId").toString());
		Response responseStatus = employeeService.updateMember(employeeId, memberId, employeeDTO);
		return new ResponseEntity<Response>(responseStatus,HttpStatus.OK);
	}
}