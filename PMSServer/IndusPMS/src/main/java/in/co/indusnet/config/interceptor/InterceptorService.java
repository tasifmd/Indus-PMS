package in.co.indusnet.config.interceptor;


//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
//
//import in.co.indusnet.util.JWTTokenHelper;

@Component
public class InterceptorService implements HandlerInterceptor {
//	
//	@Autowired
//	private JWTTokenHelper jwtTokenHelper;
//	
//	@Override
//	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
//			throws Exception {
//		String token = request.getHeader("token");
//		long employeeId = jwtTokenHelper.decodeToken(token);
//		request.setAttribute("employeeId", employeeId);
//		return true;
//	}

}
