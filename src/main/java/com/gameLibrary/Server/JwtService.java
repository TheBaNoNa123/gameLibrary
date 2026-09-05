package com.gameLibrary.Server;

import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.JWSAlgorithm;
import com.nimbusds.jose.JWSHeader;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jose.crypto.RSASSASigner;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import org.springframework.stereotype.Service;
import com.nimbusds.jose.jwk.RSAKey;

import javax.crypto.SecretKey;
import java.util.Date;

@Service
public class JwtService {
    private final SecretKey hmacKey;

    public JwtService(SecretKey hmacKey) {
        this.hmacKey = hmacKey;
    }

    public String generateToken(String username, String role){
        JWTClaimsSet claims = new JWTClaimsSet.Builder()
                .subject(username)
                .claim("role", role)
                .issueTime(new Date())
                .expirationTime(new Date(System.currentTimeMillis() + 3600_000))
                .build();

        SignedJWT signedJwt = new SignedJWT(
                new JWSHeader.Builder(JWSAlgorithm.HS256).build(),
                claims
        );
        try{
            signedJwt.sign(new MACSigner(hmacKey));

        } catch (JOSEException e) {
            throw new RuntimeException(e);
        }
        return signedJwt.serialize();
    }

}
