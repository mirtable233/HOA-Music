package util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Map;

@Component
public class JwtUtil {
    private static String SECRET;

    @Value("${JWT_SECRET}")
    private String secret;

    @PostConstruct
    public void init() {
        SECRET = secret;
    }
    public static String generateToken(Map<String,Object> map) {
        return Jwts.builder()
                .signWith(SignatureAlgorithm.HS256,SECRET)
                .addClaims(map)
                .setExpiration(new Date(System.currentTimeMillis()+3600*1000))
                .compact();
    }

    public static Claims parseToken(String token) {
        return Jwts.parser()
                .setSigningKey(SECRET)
                .parseClaimsJws(token).getBody();
    }
    }
