package in.co.indusnet.member.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/projectmanager")
public class ProjectManagerController {
	
	@GetMapping
	public String testDemo() {
		return "Successfully tested";
	}
	
}
