package com.github.distriful5061.ayanamistbitcoin.utils;

public enum HMACHashMode {
    MD5("HmacMD5"),
    SHA_1("HmacSHA1"),
    SHA_256("HmacSHA256")
    ;

    private final String fullAlgorithmName;
    HMACHashMode(String fullAlgorithmName) {
        this.fullAlgorithmName = fullAlgorithmName;
    }

    @Override
    public String toString() {
        return this.fullAlgorithmName;
    }
}
