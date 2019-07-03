package in.co.indusnet.exception;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ProjectException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	private int statusCode;

	public ProjectException(String message, int statusCode) {
		super(message);
		this.statusCode = statusCode;
	}
}
