package com.tsinsi.console.infrastructure;

import com.nimbusds.jose.JWSAlgorithm;
import com.nimbusds.jose.JWSHeader;
import com.nimbusds.jose.crypto.RSASSASigner;
import com.nimbusds.jose.crypto.RSASSAVerifier;
import com.nimbusds.jose.jwk.RSAKey;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import org.springframework.stereotype.Component;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.security.KeyStore;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.Optional;
import java.util.UUID;

@Component
public class JwtProvider {

    private RSAKey rsaKey;

    public JwtProvider() {
        try {
            File file = ResourceUtils.getFile("classpath:console.jks");
            rsaKey = RSAKey.load(KeyStore.getInstance(file, "XJlkSSLc7X9".toCharArray()), "com.tsinsi.console", "XJlkSSLc7X9".toCharArray());
        } catch (Exception exception) {
            rsaKey = null;
        }
    }

    public String generate(long uid, String username, String jti) throws Exception {
        JWSHeader header = new JWSHeader.Builder(JWSAlgorithm.RS256).keyID(rsaKey.getKeyID()).build();
        JWTClaimsSet.Builder builder = new JWTClaimsSet.Builder();
        builder.subject("" + uid);
        builder.issueTime(new Date());
        builder.issuer("tsinsi");
        builder.jwtID(jti);
        builder.expirationTime(Date.from(Instant.now().plus(7, ChronoUnit.DAYS)));
        builder.audience(username);
        JWTClaimsSet jwtClaimsSet = builder.build();

        SignedJWT signedJWT = new SignedJWT(header, jwtClaimsSet);
        RSASSASigner signer = new RSASSASigner(rsaKey);
        signedJWT.sign(signer);
        return signedJWT.serialize();
    }

    public Optional<JWTClaimsSet> parse(String token) {
        try {
            SignedJWT signedJWT = SignedJWT.parse(token);
            RSAKey publicRSAKey = rsaKey.toPublicJWK();
            RSASSAVerifier verifier = new RSASSAVerifier(publicRSAKey);
            if (!signedJWT.verify(verifier)) {
                return Optional.empty();
            }

            return Optional.of(signedJWT.getJWTClaimsSet());
        } catch (Exception exception) {
            return Optional.empty();
        }
    }
}
