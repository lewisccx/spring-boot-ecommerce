package com.selflearn.ecommerce.registration.token;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;
import java.util.Optional;

@AllArgsConstructor
@Service
public class TokenService {

    private final TokenRepository tokenRepository;

    public void saveToken(Token token){
        tokenRepository.save(token);
    }
    public Optional<Token> getToken(String token){
       return tokenRepository.findByToken(token);
    }

    public int setConfirmAt(String token){
        return tokenRepository.updateConfirmedAt(token, ZonedDateTime.now());
    }

}
