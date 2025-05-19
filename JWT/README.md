# JWT integration 

<br>

---


<br>

## How to generate RSA private/public key pair

<br>

```java
package com.frizo.lib.jwt;

import java.security.*;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;


public class JksGeneratorUtil {
    public static void main(String[] args) {
        genKeyPair();
    }
    public static void genKeyPair() {
        try {
            // Generate RSA key pair
            KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
            keyPairGenerator.initialize(2048); // You can adjust the key size as needed
            KeyPair keyPair = keyPairGenerator.generateKeyPair();

            // Extract the private and public keys from the key pair
            String privateKey = toPemString("RSA PRIVATE KEY", keyPair.getPrivate());
            String publicKey = toPemString("RSA PUBLIC KEY", keyPair.getPublic());

            // Print the private and public keys
            System.out.println("Private Key:");
            System.out.println(privateKey);
            System.out.println("--------------------------------------------------");
            System.out.println("Public Key:");
            System.out.println(publicKey);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }

    public static String toPemString(String keyType, Key key) {
        byte[] keyBytes = key.getEncoded();
        String encodedKey = Base64.getEncoder().encodeToString(keyBytes);
        StringBuilder pemString = new StringBuilder();
        pemString.append("-----BEGIN ").append(keyType).append("-----\n");
        pemString.append(encodedKey).append("\n");
        pemString.append("-----END ").append(keyType).append("-----");
        return pemString.toString();
    }

}
```

Save the console result to public_key.pem anddd private_key.pem

<br>
<br>

## JWT generate and parse

<br>

```java
package com.frizo.lib.jwt;

import com.frizo.lib.exceptions.AppException;
import com.frizo.lib.jwt.model.JwtPrivateClaims;
import com.frizo.lib.jwt.model.JwtResponse;

import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.SignatureException;
import org.apache.commons.lang3.StringUtils;
import lombok.extern.slf4j.Slf4j;

import java.security.KeyFactory;
import java.security.PublicKey;
import java.security.interfaces.RSAPrivateKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Slf4j
public class JwtTokenUtil {

    /**
     * Decode and verify a JWT using an RSA public key (PEM).
     *
     * @param token        the compact JWS string
     * @param publicKeyPem the RSA public key in PEM format (PKCS#1 or PKCS#8)
     * @return the parsed Claims if the token is valid
     * @throws IllegalArgumentException if inputs are blank or verification/parsing fails
     */
    public static Claims parseJwt(String token, String publicKeyPem) {
        if (StringUtils.isBlank(token) || StringUtils.isBlank(publicKeyPem)) {
            throw new IllegalArgumentException("JWT token or publicKeyPem is empty.");
        }

        try {
            // 1. Strip PEM headers/footers and whitespace
            String pem = publicKeyPem
                    .replace("-----BEGIN RSA PUBLIC KEY-----", "")
                    .replace("-----END RSA PUBLIC KEY-----", "")
                    .replaceAll("\\s", "");

            // 2. Base64-decode to DER, then build PublicKey
            byte[] der = Decoders.BASE64.decode(pem);
            X509EncodedKeySpec spec = new X509EncodedKeySpec(der);
            KeyFactory kf = KeyFactory.getInstance("RSA");
            PublicKey pubKey = kf.generatePublic(spec);

            // 3. Build parser, verify signature, and parse claims
            JwtParser parser = Jwts.parser()
                    .verifyWith(pubKey)
                    .build();

            // 4. Actually parse the JWS and extract body
            return parser
                    .parseClaimsJws(token)
                    .getBody();

        } catch(SignatureException e) {
            log.error("JWT signature verification failed.", e);
            throw new AppException("JWT signature verification failed");
        } catch (MalformedJwtException e) {
            log.error("JWT token is malformed.", e);
            throw new AppException("JWT token is malformed");
        } catch (ExpiredJwtException e) {
            log.error("JWT token is expired.", e);
            throw new AppException("JWT token is expired");
        } catch (Exception e) {
            log.error("Failed to parse JWT token.", e);
            throw new AppException("Failed to parse JWT token");
        }
    }


    /**
     * generate User JWT token
     *
     * @param receiver
     * @param expSec
     * @param privateKeyContent
     * @return
     * @throws IllegalArgumentException
     */
    public static JwtResponse generateJwt(
            String receiver, Long expSec, JwtPrivateClaims privateClaims, String privateKeyContent) throws IllegalArgumentException {
        if (StringUtils.isEmpty(privateKeyContent)) {
            log.error("generateAccessToken() input is empty.");
            throw new IllegalArgumentException("privateKeyContent is empty.");
        }
        try {
            // Decode the PEM-encoded private key
            byte[] decodedPrivateKey =
                    Base64.getDecoder()
                            .decode(
                                    privateKeyContent
                                            .replace("-----BEGIN RSA PRIVATE KEY-----", "")
                                            .replace("-----END RSA PRIVATE KEY-----", "")
                                            .replaceAll("\\s", ""));

            // Convert the private key into PKCS#8 format
            PKCS8EncodedKeySpec spec = new PKCS8EncodedKeySpec(decodedPrivateKey);
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            RSAPrivateKey privateKey = (RSAPrivateKey) keyFactory.generatePrivate(spec);

            Map<String, Object> claims = new HashMap<>();
            Instant now = Instant.now();
            // Add public-claims.
            claims.put("iss", "Frizo");
            claims.put("sub", receiver);
            claims.put("aud", receiver);
            claims.put("scop", "read,write");
            claims.put("jti", UUID.randomUUID().toString());
            claims.put("iat", now.getEpochSecond());
            claims.put("exp", now.plus(expSec, ChronoUnit.SECONDS).getEpochSecond());
            // Add private-claims
            claims.put("username", privateClaims.getUsername());
            claims.put("status", privateClaims.getStatus());
            claims.put("roleName", privateClaims.getRoleName());
            // Generate the JWT token RSA256 algorithm

            String token = Jwts.builder()
                    .claims(claims)
                    .signWith(privateKey, SignatureAlgorithm.RS256)
                    .compact();

            return JwtResponse.of(token, now, expSec);
        } catch (Exception e) {
            log.error("generateAccessToken() failed.", e);
            throw new AppException(e.getMessage());
        }
    }
}
```

<br>
<br>
<br>
<br>

## Additional

<br>

### Load PEM key form classpath file

```java
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.util.StreamUtils;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class PemKeyLoader {

    /**
     * Reads the public key PEM file from classpath:/jks/public_key.pem
     */
    public static String pub() {
        return loadPemFromClasspath("jks/public_key.pem");
    }

    /**
     * Reads the private key PEM file from classpath:/jks/private_key.pem
     */
    public static String priv() {
        return loadPemFromClasspath("jks/private_key.pem");
    }

    /**
     * Utility to read any PEM file on the classpath into a UTF-8 String.
     */
    private static String loadPemFromClasspath(String classpathLocation) {
        Resource resource = new ClassPathResource(classpathLocation);
        try {
            // copyToString handles small text files nicely
            return StreamUtils.copyToString(resource.getInputStream(), StandardCharsets.UTF_8);
        } catch (IOException e) {
            throw new IllegalStateException(
                    "Failed to load PEM file from classpath:" + classpathLocation, e
            );
        }
    }
}
```