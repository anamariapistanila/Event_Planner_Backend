package licenta_backend.security;
import licenta_backend.entities.Users;
import licenta_backend.repositories.UserRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AuthService  implements UserDetailsService {

    private final UserRepository userRepository;

    public AuthService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        Users user = userRepository.findByUsername(s);
        if (user == null)
            throw new UsernameNotFoundException("User not found");

        List<GrantedAuthority> authoritites = new ArrayList<>();

        GrantedAuthority grantedAuthority = new GrantedAuthority() {
            @Override
            public String getAuthority() {
                return "ROLE_" + user.getRole().toString();
            }

        };

        authoritites.add(grantedAuthority);

        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), authoritites);
    }

}
