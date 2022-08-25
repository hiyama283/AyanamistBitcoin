package com.github.distriful5061.ayanamistbitcoin;

import com.github.distriful5061.ayanamistbitcoin.config.StatusDumper;

import java.util.Locale;
import java.util.Objects;
import java.util.Scanner;

public class Main {
    public static final String LINE_SEPARATOR = System.getProperty("line.separator");
    public static Scanner scanner = new Scanner(System.in);
    public static StatusDumper configDump = new StatusDumper();

    public static void main(String[] args) {
        if (args.length != 1) {
            System.out.print("マイナーモード(マイニングが可能なモード)を使用しますか? Y(es), N(o): ");
            String interruptedString = scanner.next().toLowerCase(Locale.ROOT);

            boolean minerModeIsEnabled = false;

            if (interruptedString.equals("y") || interruptedString.equals("yes")) {
                minerModeIsEnabled = true;
                configDump.isMinerModeIsEnabled = true;
            }
        } else {
            saveArgsConfig(args.clone());
        }

        while (true) {
            System.out.print("Select Option,\n1. Generate Wallet, 2. Check Wallet\n>");
            int selectedOption = Integer.parseInt(scanner.next());
        }
    }

    private static boolean toBoolean(String name) {
        Objects.requireNonNull(name);
        return name.equalsIgnoreCase("true") ||
                name.equalsIgnoreCase("y") ||
                name.equalsIgnoreCase("yes") ||
                name.equalsIgnoreCase("1") ||
                name.equals("はい");
    }

    private static void saveArgsConfig(String[] args) {
        configDump = new StatusDumper(
                "config.json",
                toBoolean(args[0])
        );

        System.out.printf("Config Save Path: %s", "config.json");
        System.out.println();

        System.out.printf("Miner Mode: %s", toBoolean(args[0]));
        System.out.println();
    }
}
