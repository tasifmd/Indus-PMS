package in.co.indusnet.member.util;

import in.co.indusnet.member.response.LoginResponse;
import in.co.indusnet.member.response.Response;

public class ResponseHelper {
	
	public static Response statusInfo(String statusMessage, int statusCode) {
		Response response = new Response();
		response.setStatusCode(statusCode);
		response.setStatusMessage(statusMessage);
		return response;
	}
	public static LoginResponse statusResponseInfo(String statusMessage, int statusCode,String token,String userName,String email,String designation) {
		LoginResponse loginResponse = new LoginResponse();
		loginResponse.setStatusMessage(statusMessage);
		loginResponse.setStatusCode(statusCode);
		loginResponse.setToken(token);
		loginResponse.setUserName(userName);
		loginResponse.setEmail(email);
		loginResponse.setDesignation(designation);
		return loginResponse;
	} 
}