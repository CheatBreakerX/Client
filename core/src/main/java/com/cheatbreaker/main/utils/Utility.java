package com.cheatbreaker.main.utils;

import com.cheatbreaker.bridge.ref.Ref;
import com.cheatbreaker.client.remote.GitCommitProperties;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.List;

public class Utility {
    public static String fmt(String format, Object... args) {
        for (int x = 0; x < args.length; x++) {
            String str = args[x].toString();
            format = format.replaceFirst("\\{}", str);
            format = format.replaceAll("\\{" + x + "}", str);
        }
        return format;
    }

    public static long clamp(long value, long min, long max) {
        return value < min ? min : Math.min(value, max);
    }

    public static InputStream base64StringToInputStream(String data) {
        String base64Image = data.split(",")[1];
        byte[] imageBytes = javax.xml.bind.DatatypeConverter.parseBase64Binary(base64Image);
        return new ByteArrayInputStream(imageBytes);
    }

    public static String getFullTitle() {
        GitCommitProperties.loadProperties();
        return fmt("CheatBreaker {} ({}/{})",
                Ref.getMinecraftVersion(),
                GitCommitProperties.getGitCommit(),
                GitCommitProperties.getGitBranch()
        );
    }
}
