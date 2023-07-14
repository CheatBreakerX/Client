package com.cheatbreaker.client.remote;

import lombok.Getter;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class GitCommitProperties {
    @Getter
    private static String gitCommit = "0000000";
    @Getter
    private static String gitCommitId = "?";
    @Getter
    private static String gitBranch = "??????";

    private static boolean loaded = false;

    public static void loadProperties() {
        if (!loaded) {
            try (InputStream inputStream = ClassLoader.class.getResourceAsStream("/assets/minecraft/client/" +
                    "properties/app.properties")) {
                final Properties properties = new Properties();

                if (inputStream == null) {
                    return;
                }

                properties.load(inputStream);

                if (properties.getProperty("git.commit.id.abbrev") != null) {
                    gitCommit = properties.getProperty("git.commit.id.abbrev");
                }
                if (properties.getProperty("git.commit.id") != null) {
                    gitCommitId = properties.getProperty("git.commit.id");
                }
                if (properties.getProperty("git.branch") != null) {
                    gitBranch = properties.getProperty("git.branch");
                }
                loaded = true;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
