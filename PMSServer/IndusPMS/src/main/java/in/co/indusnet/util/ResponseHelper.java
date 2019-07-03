package in.co.indusnet.util;

import in.co.indusnet.response.LoginResponse;
import in.co.indusnet.response.Response;

public class ResponseHelper {
	
	public static Response statusInfo(String statusMessage, int statusCode) {
		Response response = new Response();
		response.setStatusCode(statusCode);
		response.setStatusMessage(statusMessage);
		return response;
	}
	public static LoginResponse statusResponseInfo(String statusMessage, int statusCode,String token,String employeeName,String employeeEmail,String employeeDesignation) {
		LoginResponse loginResponse = new LoginResponse();
		loginResponse.setStatusMessage(statusMessage);
		loginResponse.setStatusCode(statusCode);
		loginResponse.setToken(token);
		loginResponse.setEmployeeName(employeeName);;
		loginResponse.setEmployeeEmail(employeeEmail);;
		loginResponse.setEmployeeDesignation(employeeDesignation);;
		return loginResponse;
	} 
}
