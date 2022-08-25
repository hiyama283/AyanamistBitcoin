package com.github.distriful5061.ayanamistbitcoin.utils;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Objects;

public class VerifyUtils {
    public static byte[] base64Encode(byte[] input) {
        Objects.requireNonNull(input);
        return Base64
                .getEncoder()
                .encode(input);
    }

    public static byte[] base64Decode(byte[] input) {
        Objects.requireNonNull(input);
        return Base64
                .getDecoder()
                .decode(input);
    }

    public static String base64EncodeToString(byte[] text) {
        Objects.requireNonNull(text);
        return Base64
                .getEncoder()
                .encodeToString(text);
    }

    public static byte[] base64DecodeFromString(String text) {
        Objects.requireNonNull(text);
        return Base64
                .getDecoder()
                .decode(text);
    }

    public static String getHMACWithB64(byte[] text, byte[] secretKey, HMACHashMode hashMode) throws NoSuchAlgorithmException, InvalidKeyException {
        Objects.requireNonNull(text);
        Objects.requireNonNull(secretKey);
        Objects.requireNonNull(hashMode);

        SecretKeySpec sk = new SecretKeySpec(secretKey, hashMode.toString());
        Mac mac = Mac.getInstance(hashMode.toString());
        mac.init(sk);

        byte[] mac_bytes = mac.doFinal(text);
        return base64EncodeToString(mac_bytes);
    }


    public static String signWithHMAC(byte[] text, byte[] secretKey, HMACHashMode hashMode) throws NoSuchAlgorithmException, InvalidKeyException {
        Objects.requireNonNull(text);
        Objects.requireNonNull(secretKey);
        Objects.requireNonNull(hashMode);

        return "%s.%s"
                .formatted(
                        base64EncodeToString(text),
                        getHMACWithB64(text, secretKey, hashMode)
                );
    }

    public static boolean verifyWithHMAC(String code, byte[] secretKey, HMACHashMode hashMode) throws NoSuchAlgorithmException, InvalidKeyException {
        Objects.requireNonNull(code);
        Objects.requireNonNull(secretKey);
        Objects.requireNonNull(hashMode);

        String[] split = code.split("\\.");
        byte[] original = base64DecodeFromString(split[0]);

        String originalHMAC = getHMACWithB64(original, secretKey, hashMode);

        return Objects.equals(originalHMAC, split[1]);
    }
}
