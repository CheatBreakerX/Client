package com.cheatbreaker.main.utils;

import com.cheatbreaker.client.remote.GitCommitProperties;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

public class Utility {
    public static InputStream base64StringToInputStream(String data) {
        String base64Image = data.split(",")[1];
        byte[] imageBytes = javax.xml.bind.DatatypeConverter.parseBase64Binary(base64Image);
        return new ByteArrayInputStream(imageBytes);
    }

    public static String getFullTitle() {
        GitCommitProperties.loadProperties();
        return "CheatBreaker 1.7.10 (" + GitCommitProperties.getGitCommit() + "/" + GitCommitProperties.getGitBranch() + ")";
    }
}
