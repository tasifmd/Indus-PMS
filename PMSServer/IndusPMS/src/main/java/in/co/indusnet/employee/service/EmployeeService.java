package in.co.indusnet.employee.service;

import java.util.List;

import org.springframework.stereotype.Service;

import in.co.indusnet.employee.dto.EmployeeDTO;
import in.co.indusnet.employee.dto.LoginDTO;
import in.co.indusnet.employee.dto.PasswordDTO;
import in.co.indusnet.employee.dto.UpdateEmployeeDTO;
import in.co.indusnet.employee.model.Employee;
import in.co.indusnet.response.LoginResponse;
import in.co.indusnet.response.Response;
import in.co.indusnet.task.model.Task;

@Service("employeeService")
public interface EmployeeService {
	
	public Response addProjectManager(EmployeeDTO employeeDTO);

	public LoginResponse login(LoginDTO loginDTO);
	
	public Response addMember(int employeeId , EmployeeDTO employeeDTO);
	
	public Response changePassword(int employeeId,PasswordDTO passwordDTO);
	
	public List<Employee> getMembers(int employeeId);
	
	public Response updateMember(int employeeId,int memberId , UpdateEmployeeDTO employeeDTO);
	
	public List<Task> getTaskOfMember(int memberId);

	public Response deleteMember(int employeeId, int memberId);
	
	public List<Employee> getAllAssignedMembers();
}
