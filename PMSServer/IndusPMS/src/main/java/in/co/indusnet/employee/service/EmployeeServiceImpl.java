package in.co.indusnet.employee.service;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import in.co.indusnet.employee.dto.EmployeeDTO;
import in.co.indusnet.employee.dto.LoginDTO;
import in.co.indusnet.employee.dto.PasswordDTO;
import in.co.indusnet.employee.dto.UpdateEmployeeDTO;
import in.co.indusnet.employee.model.Employee;
import in.co.indusnet.employee.repository.EmployeeRepository;
import in.co.indusnet.exception.EmployeeException;
import in.co.indusnet.response.LoginResponse;
import in.co.indusnet.response.Response;
import in.co.indusnet.task.model.Task;
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

	private final Path fileLocation = Paths.get("D:\\Tasif Workspace\\Indus PMS\\profilepic");

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
//		if (!projectManager.get().getEmployeeDesignation().equals("Project Manager")) {
//			throw new ProjectException(environment.getProperty("unauthorisedAccess"),
//					Integer.parseInt(environment.getProperty("projectExceptionCode")));
//		}
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
//		if (!projectManager.get().getEmployeeDesignation().equals("Project Manager")) {
//			throw new ProjectException(environment.getProperty("unauthorisedAccess"),
//					Integer.parseInt(environment.getProperty("projectExceptionCode")));
//		}
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
	public Response updateMember(int employeeId, int memberId, UpdateEmployeeDTO employeeDTO) {
		Response response = new Response();
//		Optional<Employee> projectManager = employeeRepository.findById(employeeId);
//		if (!projectManager.get().getEmployeeDesignation().equals("Project Manager")) {
//			throw new ProjectException(environment.getProperty("unauthorisedAccess"),
//					Integer.parseInt(environment.getProperty("projectExceptionCode")));
//		}
//		if (!projectManager.isPresent()) {
//			throw new EmployeeException(environment.getProperty("unauthorisedAccess"),
//					Integer.parseInt(environment.getProperty("employeeExceptionCode")));
//		}
		Optional<Employee> member = employeeRepository.findById(memberId);
		member.get().setEmployeeName(employeeDTO.getEmployeeName());
		member.get().setEmployeeEmail(employeeDTO.getEmployeeEmail());
		member.get().setEmployeeMobile(employeeDTO.getEmployeeMobile());
		member.get().setEmployeeDesignation(employeeDTO.getEmployeeDesignation());
		member.get().setEmployeeAddress(employeeDTO.getEmployeeAddress());
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

	@Override
	public Response deleteMember(int employeeId, int memberId) {
		Response response = new Response();
//		Optional<Employee> projectManager = employeeRepository.findById(employeeId);
//		if (!projectManager.get().getEmployeeDesignation().equals("Project Manager")) {
//			throw new ProjectException(environment.getProperty("unauthorisedAccess"),
//					Integer.parseInt(environment.getProperty("projectExceptionCode")));
//		}
		Optional<Employee> member = employeeRepository.findById(memberId);
		employeeRepository.delete(member.get());
		response = ResponseHelper.statusInfo(environment.getProperty("memberDeleted"),
				Integer.parseInt(environment.getProperty("successCode")));
		return response;
	}

	@Override
	public List<Employee> getAllAssignedMembers() {
		List<Employee> allMembers = employeeRepository.findAll();
		List<Employee> assignedmembers = new ArrayList<>();
		for (Employee employee : allMembers) {
			if (!employee.getTask().isEmpty()) {
				assignedmembers.add(employee);
			}
		}
		return assignedmembers;
	}

	@Override
	public Response changePassword(int employeeId, PasswordDTO passwordDTO) {
		Response response = new Response();
		Optional<Employee> employee = employeeRepository.findById(employeeId);
		if (passwordDTO.getNewPassword().equals(passwordDTO.getConfirmPassword())) {
			employee.get().setEmployeePassword(passwordEncoder.encode(passwordDTO.getNewPassword()));
			employeeRepository.save(employee.get());
			response = ResponseHelper.statusInfo(environment.getProperty("passwordChanged"),
					Integer.parseInt(environment.getProperty("successCode")));
			return response;
		}
		return null;
	}

	@Override
	public Response uploadImage(int employeeId, MultipartFile file) {
		Response response = new Response();
		Optional<Employee> employee = employeeRepository.findById(employeeId);
		UUID uuid = UUID.randomUUID();
		String uniqueId = uuid.toString();
		try {
			Files.copy(file.getInputStream(), fileLocation.resolve(uniqueId), StandardCopyOption.REPLACE_EXISTING);
		} catch (IOException e) {
			System.out.println("Inavlid file");
		}
		employee.get().setProfilePic(uniqueId);
		employeeRepository.save(employee.get());
		response = ResponseHelper.statusInfo(environment.getProperty("picUploded"),
				Integer.parseInt(environment.getProperty("successCode")));
		return response;
	}

	@Override
	public Resource getUploadedImage(int employeeId) {
		Optional<Employee> employee = employeeRepository.findById(employeeId);
		Path imagePath = fileLocation.resolve(employee.get().getProfilePic());
		try {
			Resource resource = new UrlResource(imagePath.toUri());
			if (resource.exists() || resource.isReadable()) {
				return resource;
			}
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}

		return null;
	}

}
