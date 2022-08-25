package com.github.distriful5061.ayanamistbitcoin.utils;

import java.security.*;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Objects;

public class SignatureUtils {
    /* Test
    public static void main(String[] args) throws GeneralSecurityException {
        KeyPair keyPair = generateKeyPair();
        PrivateKey privateKey = keyPair.getPrivate();
        PublicKey publicKey = keyPair.getPublic();
        String text = "HelloWorld";

        System.out.println(text);

        byte[] signed = sign(text.getBytes(StandardCharsets.UTF_8), privateKey);
        System.out.println(signed.length);

        boolean bool = verify(text.getBytes(), signed, publicKey);
        System.out.println(bool);

        byte[] publicKeyEncoded = publicKey.getEncoded();

        X509EncodedKeySpec spec = new X509EncodedKeySpec(publicKeyEncoded);
        KeyFactory keyFac = KeyFactory.getInstance("RSA");
        PublicKey pk = keyFac.generatePublic(spec);

        boolean isSpecIsGood = verify(text.getBytes(StandardCharsets.UTF_8), signed, pk);
        System.out.println(isSpecIsGood);
     }
     */

    private KeyPair keyPair;
    public SignatureUtils() throws NoSuchAlgorithmException {
        this.keyPair = generateKeyPair();
    }

    public SignatureUtils(int keySize) throws NoSuchAlgorithmException {
        this.keyPair = generateKeyPair(keySize);
    }

    public SignatureUtils(KeyPair keyP) {
        this.keyPair = keyP;
    }

    public void init(int size) throws NoSuchAlgorithmException {
        this.keyPair = generateKeyPair(size);
    }

    public KeyPair getKeyPair() {
        return this.keyPair;
    }

    public byte[] sign(byte[] text) throws GeneralSecurityException {
        return sign(text, this.keyPair.getPrivate());
    }

    public boolean verify(byte[] text, byte[] input) throws GeneralSecurityException {
        return verify(text, input, this.keyPair.getPublic());
    }

    public static PublicKey getPublicKey(byte[] encodePublicKey) throws NoSuchAlgorithmException, InvalidKeySpecException {
        Objects.requireNonNull(encodePublicKey);

        X509EncodedKeySpec spec = new X509EncodedKeySpec(encodePublicKey);
        KeyFactory keyFac = KeyFactory.getInstance("RSA");
        return keyFac.generatePublic(spec);
    }

    public static PrivateKey getPrivateKey(byte[] encodePrivateKey) throws NoSuchAlgorithmException, InvalidKeySpecException {
        Objects.requireNonNull(encodePrivateKey);

        PKCS8EncodedKeySpec spec2 = new PKCS8EncodedKeySpec(encodePrivateKey);
        KeyFactory keyFac = KeyFactory.getInstance("RSA");
        return keyFac.generatePrivate(spec2);
    }

    public static KeyPair generateKeyPair(int keySize) throws NoSuchAlgorithmException {
        KeyPairGenerator keyGen = KeyPairGenerator.getInstance("RSA");

        keyGen.initialize(keySize);

        return keyGen.generateKeyPair();
    }

    public static KeyPair generateKeyPair() throws NoSuchAlgorithmException {
        return generateKeyPair(2048);
    }

    public static byte[] sign(byte[] text, PrivateKey privatekey) throws GeneralSecurityException {
        Objects.requireNonNull(text);
        Objects.requireNonNull(privatekey);

        Signature sign = Signature.getInstance("SHA256withRSA");
        sign.initSign(privatekey);
        sign.update(text);

        return sign.sign();
    }

    public static boolean verify(byte[] text, byte[] sign, PublicKey publicKey) throws GeneralSecurityException {
        Objects.requireNonNull(text);
        Objects.requireNonNull(sign);
        Objects.requireNonNull(publicKey);

        Signature verify = Signature.getInstance("SHA256withRSA");
        verify.initVerify(publicKey);
        verify.update(text);

        return verify.verify(sign);
    }
}
