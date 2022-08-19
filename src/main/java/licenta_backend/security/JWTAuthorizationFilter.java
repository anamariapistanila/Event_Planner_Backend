package licenta_backend.security;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import io.jsonwebtoken.Jwts;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import static java.util.Collections.emptyList;

public class JWTAuthorizationFilter  extends BasicAuthenticationFilter {

    private AuthService userDetailsService;

    public JWTAuthorizationFilter(AuthenticationManager authenticationManager,AuthService userDetailsService) {
        super(authenticationManager);
        this.userDetailsService = userDetailsService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        String header = request.getHeader(JWTConstants.HEADER);
        if (header == null || !header.startsWith(JWTConstants.TOKEN_PREFIX)) {
            chain.doFilter(request, response);
        } else {
            Authentication authentication = getAuthentication(request);
            SecurityContextHolder.getContext().setAuthentication(authentication);
            chain.doFilter(request, response);
        }
    }

    private Authentication getAuthentication(HttpServletRequest request) {

        String header = request.getHeader(JWTConstants.HEADER);
        if (header != null) {
            System.out.println("HEADER -->  "+ header );
            String user =  Jwts.parser()
                    .setSigningKey(JWTConstants.SECRET_KEY)
                    .parseClaimsJws(header.replace(JWTConstants.TOKEN_PREFIX, ""))
                    .getBody()
                    .getSubject();
System.out.println(user);
            UserDetails userDetails = userDetailsService.loadUserByUsername(user);

            if (user != null) {
                return new UsernamePasswordAuthenticationToken(user, null, userDetails.getAuthorities() );
            }
        }

        return null;
    }

}
