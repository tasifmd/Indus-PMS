package in.co.indusnet.employee.model;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table
@Getter
@Setter
@ToString
public class Employee {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int employeeId;
	private String employeeName;
	private String employeeEmail;
	private String employeeMobile;
	private String employeePassword;
	private String employeeDesignation;
	private String employeeAddress;
	private LocalDate createdTimeStamp;
	private LocalDate modidifiedTimeStamp;

}