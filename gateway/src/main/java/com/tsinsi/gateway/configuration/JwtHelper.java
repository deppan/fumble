package com.tsinsi.gateway.configuration;

import com.nimbusds.jose.JWSObject;
import com.nimbusds.jose.Payload;
import com.nimbusds.jose.crypto.RSASSAVerifier;
import com.nimbusds.jose.jwk.RSAKey;
import com.tsinsi.gateway.configuration.util.MapClaims;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.security.KeyStore;

public class JwtHelper {

    private RSAKey rsaKey;

    public JwtHelper() {
        try {
            File file = ResourceUtils.getFile("classpath:jwt.jks");
            rsaKey = RSAKey.load(KeyStore.getInstance(file, "KbMG52t#sMTF".toCharArray()), "tsinsi.com", "KbMG52t#sMTF".toCharArray());
        } catch (Exception exception) {
            rsaKey = null;
        }
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
            return new MapClaims(null);
        }
    }

}
