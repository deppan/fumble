package com.tsinsi.fumble.account.configuration;

import com.nimbusds.jose.*;
import com.nimbusds.jose.crypto.RSASSASigner;
import com.nimbusds.jose.crypto.RSASSAVerifier;
import com.nimbusds.jose.jwk.RSAKey;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import com.tsinsi.fumble.account.configuration.util.ClaimSet;
import com.tsinsi.fumble.account.configuration.util.MapClaims;
import org.hashids.Hashids;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.security.KeyStore;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;

public class JwtHelper {

    private RSAKey rsaKey;

    @Autowired
    private Hashids hashids;

    public JwtHelper() {
        try {
            File file = ResourceUtils.getFile("classpath:jwt.jks");
            rsaKey = RSAKey.load(KeyStore.getInstance(file, "KbMG52t#sMTF".toCharArray()), "tsinsi.com", "KbMG52t#sMTF".toCharArray());
        } catch (Exception exception) {
            rsaKey = null;
        }
    }

    public String generate(ClaimSet claimSet) {
        try {
            String hash = hashids.encode(claimSet.getUid());
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
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public MapClaims parse(String token) {
        try {
            JWSObject object = JWSObject.parse(token);
            RSAKey publicRSAKey = rsaKey.toPublicJWK();
            RSASSAVerifier verifier = new RSASSAVerifier(publicRSAKey);
            if (!object.verify(verifier)) {
                return new MapClaims(null);
            }

            Payload payload = object.getPayload();
            return new MapClaims(payload.toJSONObject());
        } catch (Exception e) {
            e.printStackTrace();
            return new MapClaims(null);
        }
    }

}
