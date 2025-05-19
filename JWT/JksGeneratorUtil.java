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