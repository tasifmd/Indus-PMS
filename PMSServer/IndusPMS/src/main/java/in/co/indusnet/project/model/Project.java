package in.co.indusnet.project.model;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import in.co.indusnet.employee.model.Employee;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
@Entity
@Table
@Getter
@Setter
@ToString
public class Project {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int projectId;
	private String projectName;
	private String projectDescription;
	private LocalDate createdTimeStamp;
	private LocalDate modifiedTimeStamp;
	@ElementCollection
	private List<String> tasks;
	@OneToMany(cascade = CascadeType.ALL)
	private List<Employee> members;
	
}
