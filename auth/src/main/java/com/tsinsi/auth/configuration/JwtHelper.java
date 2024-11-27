package com.tsinsi.auth.configuration;

import com.nimbusds.jose.*;
import com.nimbusds.jose.crypto.RSASSASigner;
import com.nimbusds.jose.crypto.RSASSAVerifier;
import com.nimbusds.jose.jwk.RSAKey;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import com.tsinsi.auth.configuration.util.ClaimSet;
import com.tsinsi.auth.configuration.util.MapClaims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ResourceUtils;
import org.sqids.Sqids;

import java.io.File;
import java.security.KeyStore;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.List;

public class JwtHelper {

    private RSAKey rsaKey;

    @Autowired
    private Sqids sqids;

    public JwtHelper() {
        try {
            File file = ResourceUtils.getFile("classpath:jwt.jks");
            rsaKey = RSAKey.load(KeyStore.getInstance(file, "KbMG52t#sMTF".toCharArray()), "tsinsi.com", "KbMG52t#sMTF".toCharArray());
        } catch (Exception exception) {
            rsaKey = null;
        }
    }

    public String onSign(ClaimSet claimSet) throws Exception {
        String hash = sqids.encode(List.of(claimSet.getUid()));
        JWSHeader header = new JWSHeader.Builder(JWSAlgorithm.RS256).type(JOSEObjectType.JWT).build();
        JWTClaimsSet.Builder builder = new JWTClaimsSet.Builder();
        builder.subject(hash);
        builder.issueTime(new Date());
        builder.issuer("tsinsi");
        builder.expirationTime(Date.from(Instant.now().plus(claimSet.getDays(), ChronoUnit.DAYS)));
        builder.audience(claimSet.getUsername());

        SignedJWT signedJWT = new SignedJWT(header, builder.build());
        RSASSASigner rsassaSigner = new RSASSASigner(rsaKey);
        signedJWT.sign(rsassaSigner);
        return signedJWT.serialize();
    }

    public MapClaims parse(String token) throws Exception{
        JWSObject object = JWSObject.parse(token);
        RSAKey publicRSAKey = rsaKey.toPublicJWK();
        RSASSAVerifier verifier = new RSASSAVerifier(publicRSAKey);
        if (!object.verify(verifier)) {
            return new MapClaims(null);
        }

        Payload payload = object.getPayload();
        return new MapClaims(payload.toJSONObject());
    }
}
