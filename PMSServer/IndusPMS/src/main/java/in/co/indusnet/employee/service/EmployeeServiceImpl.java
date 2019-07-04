package in.co.indusnet.employee.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import in.co.indusnet.employee.dto.EmployeeDTO;
import in.co.indusnet.employee.dto.LoginDTO;
import in.co.indusnet.employee.model.Employee;
import in.co.indusnet.employee.repository.EmployeeRepository;
import in.co.indusnet.exception.EmployeeException;
import in.co.indusnet.exception.ProjectException;
import in.co.indusnet.response.LoginResponse;
import in.co.indusnet.response.Response;
import in.co.indusnet.task.model.Task;
import in.co.indusnet.task.repository.TaskRepository;
import in.co.indusnet.util.JWTTokenHelper;
import in.co.indusnet.util.ResponseHelper;

@Service("employeeService")
@PropertySource("classpath:error.properties")
@PropertySource("classpath:message.properties")
public class EmployeeServiceImpl implements EmployeeService {

	private Logger logger = LoggerFactory.getLogger(EmployeeServiceImpl.class);

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private Environment environment;

	@Autowired
	private JWTTokenHelper jwtTokenHelper;

	@Autowired
	private EmployeeRepository employeeRepository;

	@Autowired
	private TaskRepository taskRepository;

	@Override
	public Response addProjectManager(EmployeeDTO employeeDTO) {
		Response response = new Response();
		logger.info((employeeDTO.toString()));
		Optional<Employee> employeeAvailability = employeeRepository
				.findByEmployeeEmail(employeeDTO.getEmployeeEmail());

		if (employeeAvailability.isPresent()) {
			logger.error("Employee already exist {}", employeeAvailability.get());
			throw new EmployeeException(environment.getProperty("employeeExistMessage"),
					Integer.parseInt(environment.getProperty("employeeExceptionCode")));
		}
		employeeDTO.setEmployeePassword(passwordEncoder.encode(employeeDTO.getEmployeePassword()));
		Employee employee = modelMapper.map(employeeDTO, Employee.class);
		employee.setCreatedTimeStamp(LocalDate.now());
		employee.setModidifiedTimeStamp(LocalDate.now());
		employeeRepository.save(employee);
		response = ResponseHelper.statusInfo(environment.getProperty("addedMember"),
				Integer.parseInt(environment.getProperty("successCode")));
		return response;
	}

	@Override
	public LoginResponse login(LoginDTO loginDTO) {
		LoginResponse loginResponse = new LoginResponse();
		Optional<Employee> employeeAvailability = employeeRepository.findByEmployeeEmail(loginDTO.getEmployeeEmail());
		if (!employeeAvailability.isPresent()) {
			logger.error("Employee does not exist {}", loginDTO.getEmployeeEmail());
		}

		if (passwordEncoder.matches(loginDTO.getEmployeePassword(), employeeAvailability.get().getEmployeePassword())) {
			String token = jwtTokenHelper.generateToken(employeeAvailability.get().getEmployeeId());
			String employeeName = employeeAvailability.get().getEmployeeName();
			String employeeEmail = employeeAvailability.get().getEmployeeEmail();
			String employeeDesignation = employeeAvailability.get().getEmployeeDesignation();
			loginResponse = ResponseHelper.statusResponseInfo(environment.getProperty("loggedIn"),
					Integer.parseInt(environment.getProperty("successCode")), token, employeeName, employeeEmail,
					employeeDesignation);
			return loginResponse;
		}
		loginResponse = ResponseHelper.statusResponseInfo(environment.getProperty("loggedInFailed"),
				Integer.parseInt(environment.getProperty("failedCode")), null, null, null, null);
		return loginResponse;
	}

	@Override
	public Response addMember(int employeeId, EmployeeDTO employeeDTO) {
		Response response = new Response();
		Optional<Employee> projectManager = employeeRepository.findById(employeeId);
		if (!projectManager.get().getEmployeeDesignation().equals("Project Manager")) {
			throw new ProjectException(environment.getProperty("unauthorisedAccess"),
					Integer.parseInt(environment.getProperty("projectExceptionCode")));
		}
		if (projectManager.isPresent()) {
			employeeDTO.setEmployeePassword(passwordEncoder.encode(employeeDTO.getEmployeePassword()));
			Employee employee = modelMapper.map(employeeDTO, Employee.class);
			employee.setCreatedTimeStamp(LocalDate.now());
			employee.setModidifiedTimeStamp(LocalDate.now());
			employeeRepository.save(employee);
			response = ResponseHelper.statusInfo(environment.getProperty("addedMember"),
					Integer.parseInt(environment.getProperty("successCode")));
			return response;
		}
		response = ResponseHelper.statusInfo(environment.getProperty("memberNotAdded"),
				Integer.parseInt(environment.getProperty("failedCode")));
		return response;
	}

	@Override
	public List<Employee> getMembers(int employeeId) {
		Optional<Employee> projectManager = employeeRepository.findById(employeeId);
		if (!projectManager.get().getEmployeeDesignation().equals("Project Manager")) {
			throw new ProjectException(environment.getProperty("unauthorisedAccess"),
					Integer.parseInt(environment.getProperty("projectExceptionCode")));
		}
		if (projectManager.isPresent()) {
			List<Employee> member = employeeRepository.findAll();
			List<Employee> allMembers = new ArrayList<Employee>();
			for (Employee employee : member) {
				if (employee.getEmployeeId() != employeeId) {
					allMembers.add(employee);
				}
			}
			return allMembers;
		}
		return null;
	}

	@Override
	public Response updateMember(int employeeId, int memberId, EmployeeDTO employeeDTO) {
		Response response = new Response();
		Optional<Employee> projectManager = employeeRepository.findById(employeeId);
		if (!projectManager.get().getEmployeeDesignation().equals("Project Manager")) {
			throw new ProjectException(environment.getProperty("unauthorisedAccess"),
					Integer.parseInt(environment.getProperty("projectExceptionCode")));
		}
		if (!projectManager.isPresent()) {
			throw new EmployeeException(environment.getProperty("unauthorisedAccess"),
					Integer.parseInt(environment.getProperty("employeeExceptionCode")));
		}
		Optional<Employee> member = employeeRepository.findById(memberId);
		member.get().setEmployeeName(employeeDTO.getEmployeeName());
		member.get().setEmployeeEmail(employeeDTO.getEmployeeEmail());
		member.get().setEmployeeMobile(employeeDTO.getEmployeeMobile());
		member.get().setEmployeeDesignation(employeeDTO.getEmployeeDesignation());
		member.get().setEmployeeAddress(employeeDTO.getEmployeeAddress());
		member.get().setEmployeePassword(passwordEncoder.encode(employeeDTO.getEmployeePassword()));
		member.get().setModidifiedTimeStamp(LocalDate.now());
		employeeRepository.save(member.get());
		response = ResponseHelper.statusInfo(environment.getProperty("memberUpdated"),
				Integer.parseInt(environment.getProperty("successCode")));
		return response;
	}

	@Override
	public List<Task> getTaskOfMember(int memberId) {
		Optional<Employee> employee = employeeRepository.findById(memberId);
		List<Task> task = employee.get().getTask();
		return task;
	}

}
