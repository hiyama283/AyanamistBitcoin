package com.github.distriful5061.ayanamistbitcoin.config;

public class StatusDumper {
    public String configSavePath;
    public boolean isMinerModeIsEnabled;

    public StatusDumper() {
        this.configSavePath = "config.json";
        this.isMinerModeIsEnabled = false;
    }

    public StatusDumper(String savePath, boolean minerModeBool) {
        this.configSavePath = savePath;
        this.isMinerModeIsEnabled = minerModeBool;
    }
}
