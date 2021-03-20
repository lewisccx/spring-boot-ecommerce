package com.selflearn.ecommerce.appuser;

import com.selflearn.ecommerce.registration.token.Token;
import com.selflearn.ecommerce.registration.token.TokenService;
import lombok.AllArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@AllArgsConstructor
public class AppUserService implements UserDetailsService {

    private final static  String USER_NOT_FOUND = "user with email %s not found";
    private final AppUserRepository appUserRepository;
    private final TokenService tokenService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private static final Logger LOGGER = LogManager.getLogger(AppUserService.class.getName());
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return appUserRepository.findByUsername(username).orElseThrow(
                () ->  {
                    LOGGER.debug(USER_NOT_FOUND,username);
                   return new UsernameNotFoundException(String.format(USER_NOT_FOUND,username));
                }
        );
    }

    public String signUpUser(AppUser appUser){
        boolean userExists = appUserRepository.existsByUsername(appUser.getUsername());
        if(userExists){
            LOGGER.debug("username already taken");
            throw  new IllegalStateException("username already taken");
        }
        String encodedPassword = bCryptPasswordEncoder.encode(appUser.getPassword());

        appUser.setPassword(encodedPassword);

        appUserRepository.save(appUser);

        String token = UUID.randomUUID().toString();
        Token confirmationToken  = new Token(
                token,
                appUser
        );
        tokenService.saveToken(confirmationToken);
        LOGGER.debug("signUpUser: success");
        return token;
    }

    public int enableAppUser(String email) {
        LOGGER.debug("enableAppUser: success");
        return appUserRepository.enableAppUser(email);
    }
}
