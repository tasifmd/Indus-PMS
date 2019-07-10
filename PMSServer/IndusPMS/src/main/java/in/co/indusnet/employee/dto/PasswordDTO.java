package in.co.indusnet.employee.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class PasswordDTO {
	private String newPassword;
	private String confirmPassword;
}
