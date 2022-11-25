package com.cheatbreaker.client.ui.mainmenu;

import com.cheatbreaker.bridge.util.ResourceLocationBridge;
import com.cheatbreaker.main.CheatBreaker;

public class Account {
    private String clientToken;
    private String username;
    private String accessToken;
    private String displayName;
    private String uuid;
    private final ResourceLocationBridge headLocation;

    public Account(String username, String clientToken, String accessToken, String displayName, String uuid) {
        this.username = username;
        this.clientToken = clientToken;
        this.accessToken = accessToken;
        this.displayName = displayName;
        this.uuid = uuid;
        this.headLocation = CheatBreaker.getInstance().getHeadLocation(displayName);
    }

    public String getClientToken() {
        return this.clientToken;
    }

    public String getUsername() {
        return this.username;
    }

    public String getAccessToken() {
        return this.accessToken;
    }

    public String getDisplayName() {
        return this.displayName;
    }

    public String getUUID() {
        return this.uuid;
    }

    public void setClientToken(String string) {
        this.clientToken = string;
    }

    public void setUsername(String string) {
        this.username = string;
    }

    public void setAccessToken(String string) {
        this.accessToken = string;
    }

    public void setDisplayName(String string) {
        this.displayName = string;
    }

    public void setUUID(String string) {
        this.uuid = string;
    }

    public ResourceLocationBridge getHeadLocation() {
        return this.headLocation;
    }
}
