package com.github.distriful5061.ayanamistbitcoin;

import java.util.Locale;
import java.util.Scanner;

public class Main {
    public static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.print("Enable Miner Mode? (Y(es), N(o)): ");
        String interruptedString = scanner.next().toLowerCase(Locale.ROOT);

        boolean minerModeIsEnabled = false;

        if (interruptedString.equals("y") || interruptedString.equals("yes"))
            minerModeIsEnabled = true;

    }
}
