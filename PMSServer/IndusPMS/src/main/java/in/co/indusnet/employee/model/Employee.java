package in.co.indusnet.employee.model;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import in.co.indusnet.task.model.Task;
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
	@NotEmpty
	@NotNull
	private String employeeName;
	@NotEmpty
	@NotNull
	private String employeeEmail;
	@NotEmpty
	@NotNull
	private String employeeMobile;
	@NotEmpty
	@NotNull
	private String employeePassword;
	@NotEmpty
	@NotNull
	private String employeeDesignation;
	@NotEmpty
	@NotNull
	private String employeeAddress;
	
	private LocalDate createdTimeStamp;
	private LocalDate modidifiedTimeStamp;
	private String profilePic;
	@ManyToMany(cascade = CascadeType.ALL)
	private List<Task> task;
 
}