package in.co.indusnet.employee.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/projectmanager")
public class EmployeeController {

	@GetMapping
	public String testDemo() {
		return "Successfully tested";
	}

}
