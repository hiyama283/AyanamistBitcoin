package com.github.distriful5061.ayanamistbitcoin.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PatternUtils {
    public static Pattern compile(String regex) {
        return Pattern.compile(regex);
    }

    public static Matcher matchCompile(String regex, CharSequence input) {
        return compile(regex).matcher(input);
    }
}
