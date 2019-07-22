package in.co.indusnet.project.model;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import in.co.indusnet.employee.model.Employee;
import lombok.Data;
@Entity
@Table
@Data
public class Project {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int projectId;
	private String projectName;
	private String projectDescription;
	private LocalDate createdTimeStamp;
	private LocalDate modifiedTimeStamp;
	@ManyToMany
	private List<Employee> employee;
}
