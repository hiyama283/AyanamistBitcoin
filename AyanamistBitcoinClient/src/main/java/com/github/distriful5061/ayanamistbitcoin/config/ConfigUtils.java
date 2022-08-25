package com.github.distriful5061.ayanamistbitcoin.config;

import com.github.distriful5061.ayanamistbitcoin.Main;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.*;
import java.net.URI;
import java.net.URISyntaxException;

public class ConfigUtils {
    private static boolean checkBeforeWriteFile(File file){
        if (file.exists()){
            if (file.isFile() && file.canWrite()){
                return true;
            }
        }

        return false;
    }

    public static void dumpConfig() throws URISyntaxException {
        System.out.print("Configを保存するファイル名: ");
        String fileName = Main.scanner.nextLine();

        File file = new File(new URI(fileName));

        if (!checkBeforeWriteFile(file)) {
            System.out.print("保存できませんでした");
            return;
        }

        try (
                FileWriter fileWriter = new FileWriter(fileName);
                BufferedWriter bw = new BufferedWriter(fileWriter)
        )
        {
            String dumpedJson = new GsonBuilder()
                    .setPrettyPrinting()
                    .create().toJson(Main.configDump);

            for (String s : dumpedJson.split("\n")) {
                bw.write(s);
                bw.newLine();
            }
        } catch (IOException ignored) {}
    }

    private static boolean checkBeforeReadFile(File file){
        if (file.exists()){
            if (file.isFile() && file.canRead()){
                return true;
            }
        }

        return false;
    }

    public static void loadConfig() throws URISyntaxException {
        System.out.print("Configが保存されているファイル名: ");
        String fileName = Main.scanner.nextLine();

        File file = new File(new URI(fileName));

        if (!checkBeforeReadFile(file)) {
            System.out.print("読み込みできませんでした");
            return;
        }

        try (
                FileReader fileReader = new FileReader(fileName);
                BufferedReader br = new BufferedReader(fileReader)
        )
        {
            StringBuilder stringBuilder = new StringBuilder();

            String str;

            while ((str = br.readLine()) != null) {
                stringBuilder.append(str).append("\n");
            }

            Main.configDump = new Gson()
                    .fromJson(stringBuilder.toString(), StatusDumper.class);
        } catch (IOException ignored) {}
    }
}
