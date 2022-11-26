package com.cheatbreaker.client.remote;

import com.cheatbreaker.bridge.ref.Ref;
import com.cheatbreaker.bridge.util.ResourceLocationBridge;
import lombok.Getter;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class GitCommitProperties {
    public static void loadProperties() {
        try {
            final ResourceLocationBridge location = Ref.getInstanceCreator().createResourceLocation("client/properties/app.properties");
            final Properties properties = new Properties();
            InputStream inputStream = Ref.getMinecraft().bridge$getResourceManager().bridge$getResource(location).bridge$getInputStream();

            if (inputStream == null) {
                gitCommit = "?";
                gitCommitId = "?";
                gitBranch = "?";
                return;
            }

            properties.load(inputStream);
            gitCommit = properties.getProperty("git.commit.id.abbrev");
            gitCommitId = properties.getProperty("git.commit.id");
            gitBranch = properties.getProperty("git.branch");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Getter
    private static String gitCommit = "?";
    @Getter
    private static String gitCommitId = "?";
    @Getter
    private static String gitBranch = "?";
}
