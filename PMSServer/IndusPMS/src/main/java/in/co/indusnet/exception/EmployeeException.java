package in.co.indusnet.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class EmployeeException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	private int statusCode;

	public EmployeeException(String message, int statusCode) {
		super(message);
		this.statusCode = statusCode;
	}
}
