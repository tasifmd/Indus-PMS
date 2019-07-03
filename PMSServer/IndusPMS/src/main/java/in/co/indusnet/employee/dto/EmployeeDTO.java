package in.co.indusnet.employee.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class EmployeeDTO {
	private String employeeName;
	private String employeeEmail;
	private String employeeMobile;
	private String employeeDesignation;
	private String employeeAddress;
}
