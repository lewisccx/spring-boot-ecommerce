package com.selflearn.ecommerce.registration;

import com.selflearn.ecommerce.appuser.AppUser;
import com.selflearn.ecommerce.appuser.AppUserRole;
import com.selflearn.ecommerce.appuser.AppUserService;
import com.selflearn.ecommerce.registration.token.Token;
import com.selflearn.ecommerce.registration.token.TokenService;
import lombok.AllArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.ZonedDateTime;


@AllArgsConstructor
@Service
public class RegistrationService {

    private final EmailValidator emailValidator;
    private final AppUserService appUserService;
    private final TokenService tokenService;
    private static final Logger LOGGER = LogManager.getLogger(RegistrationService.class.getName());

    public String register(RegisterDto request) {
        boolean isValidEmail = emailValidator.test(request.getEmail());
        if(!isValidEmail){
            LOGGER.debug("email not valid");
            throw new IllegalStateException("email not valid");
        }
        return appUserService.signUpUser(
            new AppUser(
                    request.getFirstName().concat(" ").concat(request.getLastName()),
                    request.getUsername(),
                    request.getEmail(),
                    request.getPassword(),
                    AppUserRole.USER
            )
        );

    }

    @Transactional
    public String confirmToken(String token){
        Token confirmationToken = tokenService.getToken(token).orElseThrow(

                ()-> {
                    LOGGER.debug("token not found");
                   return new IllegalStateException("token not found");
                }
        );

        if(confirmationToken.getConfirmedAt() != null){
            LOGGER.debug("email already confirmed");
            new IllegalStateException("email already confirmed");
        }
        ZonedDateTime expiredAt = confirmationToken.getExpiredAt();
        if (expiredAt.isBefore(ZonedDateTime.now())){
            LOGGER.debug("token expired");
            new IllegalStateException("token expired");
        }

        tokenService.setConfirmAt(token);
        appUserService.enableAppUser(confirmationToken.getAppUser().getEmail());
        LOGGER.debug("confirmed");
        return "confirmed";
    }
}
