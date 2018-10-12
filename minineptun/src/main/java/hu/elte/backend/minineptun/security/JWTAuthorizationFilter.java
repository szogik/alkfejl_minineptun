package hu.elte.backend.minineptun.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Map;

import static hu.elte.backend.minineptun.security.JWTAuthenticationFilter.secretKey;

public class JWTAuthorizationFilter extends BasicAuthenticationFilter {


    public JWTAuthorizationFilter(AuthenticationManager authManager) {
        super(authManager);
    }

    @Override
    protected void doFilterInternal(HttpServletRequest req,
                                    HttpServletResponse res,
                                    FilterChain chain) throws IOException, ServletException {
        String header = req.getHeader("Authorization");

        if (header == null || !header.startsWith("Bearer ")) {
            chain.doFilter(req, res);
            return;
        }
        UsernamePasswordAuthenticationToken authentication = getAuthentication(req);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        chain.doFilter(req, res);
    }

    private UsernamePasswordAuthenticationToken getAuthentication(HttpServletRequest request) {
        String token = request.getHeader("Authorization");
        if (token != null) {
            Claims body = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token.substring(7)).getBody();
            String user = body.getSubject();
            Date expiration = body.getExpiration();
            ArrayList<SimpleGrantedAuthority> auth = new ArrayList<>();
            auth.add(new SimpleGrantedAuthority(((Map) body.get("role")).get("authority").toString()));
            if (user != null && expiration.after(new Date(System.currentTimeMillis()))) {
                return new UsernamePasswordAuthenticationToken(user, null, auth);
            }
            return null;
        }
        return null;
    }
}
