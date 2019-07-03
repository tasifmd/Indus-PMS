package in.co.indusnet.exception.exceptionhandler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import in.co.indusnet.exception.EmployeeException;
import in.co.indusnet.exception.JWTTokenException;
import in.co.indusnet.exception.ProjectException;
import in.co.indusnet.response.Response;
import in.co.indusnet.util.ResponseHelper;

@RestControllerAdvice
public class ExceptionHandler {
	
	@Autowired
	Response response;

	@org.springframework.web.bind.annotation.ExceptionHandler(value = JWTTokenException.class)
	public ResponseEntity<Response> jwtTokenExceptionHandler(JWTTokenException e) {
		response = ResponseHelper.statusInfo(e.getMessage(), e.getStatusCode());
		return new ResponseEntity<Response>(response, HttpStatus.OK);
	}
	
	@org.springframework.web.bind.annotation.ExceptionHandler(value = EmployeeException.class)
	public ResponseEntity<Response> employeeExceptionHandler(EmployeeException e) {
		response = ResponseHelper.statusInfo(e.getMessage(), e.getStatusCode());
		return new ResponseEntity<Response>(response, HttpStatus.OK);
	}
	
	@org.springframework.web.bind.annotation.ExceptionHandler(value = ProjectException.class)
	public ResponseEntity<Response> projectExceptionHandler(ProjectException e) {
		response = ResponseHelper.statusInfo(e.getMessage(), e.getStatusCode());
		return new ResponseEntity<Response>(response, HttpStatus.OK);
	}
}
