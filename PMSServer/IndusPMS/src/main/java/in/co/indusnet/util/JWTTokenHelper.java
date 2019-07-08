package in.co.indusnet.util;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import in.co.indusnet.exception.JWTTokenException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
@PropertySource("classpath:error.properties")
public class JWTTokenHelper {
	@Value("${tokenkey}")
	private String tokenKey;

	private final long EXPIRATIONTIME = 1000 * 60 * 60 * 24; 

	@Autowired
	private Environment environment;

	public String generateToken(long userId) {
		String token = Jwts.builder().setSubject(String.valueOf(userId))
				.setExpiration(new Date(System.currentTimeMillis() + EXPIRATIONTIME))
				.signWith(SignatureAlgorithm.HS512, tokenKey).compact();
		return token;
	}

	public int decodeToken(String token) {
		try {
			System.out.println("Token : " + token);
			int userId = Integer
					.parseInt((Jwts.parser().setSigningKey(tokenKey).parseClaimsJws(token).getBody().getSubject()));
			return userId;
		} catch (Exception e) {
			throw new JWTTokenException(environment.getProperty("jwtTokenExceptionMessage"),
					Integer.parseInt(environment.getProperty("jwtTokenExceptionCode")));
		}
	}
}
