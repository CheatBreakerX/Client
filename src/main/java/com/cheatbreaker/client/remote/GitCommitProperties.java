package com.cheatbreaker.client.remote;

import com.cheatbreaker.bridge.Ref;
import com.cheatbreaker.bridge.util.ResourceLocationBridge;
import lombok.Getter;
import net.minecraft.client.Minecraft;
import net.minecraft.util.ResourceLocation;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class GitCommitProperties {
    public static void loadProperties() {
        try {
            final ResourceLocationBridge resourceLocation = Ref.createResourceLocation("client/properties/app.properties");
            final Properties properties = new Properties();
            InputStream inputStream = Ref.getMinecraft().bridge$getResourceManager().bridge$getResource(resourceLocation).bridge$getInputStream();

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
