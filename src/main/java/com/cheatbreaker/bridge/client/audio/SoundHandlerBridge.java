package com.cheatbreaker.bridge.client.audio;

public interface SoundHandlerBridge {
    SoundManagerBridge bridge$getSoundManager();
    void bridge$playSound(ISoundBridge sound);
}
