package com.cheatbreaker.client.util.voicechat;

import com.cheatbreaker.bridge.util.EnumChatFormattingBridge;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class VoiceChannel {
    @Getter
    private final UUID uuid;
    @Getter
    private final String channelName;
    @Getter
    private final List<VoiceUser> voiceUsers = new ArrayList<>();
    private final List<UUID> listeningList = new ArrayList<>();

    public VoiceChannel(UUID uuid, String channelName) {
        this.uuid = uuid;
        this.channelName = channelName;
    }

    public VoiceUser getOrCreateVoiceUser(UUID uuid, String string) {
        VoiceUser voiceUser = null;
        if (!this.isInChannel(uuid)) {
            System.out.println("[CB Voice] Created the user client side (" + uuid.toString() + ", " + string + ").");
            voiceUser = new VoiceUser(uuid, EnumChatFormattingBridge.getTextWithoutFormattingCodes(string));
            this.listeningList.add(voiceUser.getUUID());
            this.voiceUsers.add(voiceUser);
        }
        return voiceUser;
    }

    public void removeUser(UUID uuid) {
        this.voiceUsers.removeIf(voiceUser -> voiceUser.getUUID().equals(uuid));
    }

    public void addToListening(UUID uuid) {
        if (this.isInChannel(uuid)) {
            this.listeningList.add(uuid);
        }
    }

    public void removeListener(UUID uuid) {
        this.listeningList.removeIf(voiceUser -> voiceUser.equals(uuid));
    }

    public boolean isListening(UUID uuid) {
        return this.listeningList.stream().anyMatch(voiceUser -> voiceUser.equals(uuid));
    }

    public boolean isInChannel(UUID uuid) {
        return this.voiceUsers.stream().anyMatch(voiceUser -> voiceUser.getUUID().equals(uuid));
    }
}