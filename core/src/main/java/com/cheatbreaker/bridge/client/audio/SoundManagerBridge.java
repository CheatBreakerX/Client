package com.cheatbreaker.bridge.client.audio;

import paulscode.sound.SoundSystem;

public interface SoundManagerBridge {
    void playSound(String sound, float volume);
    SoundSystem bridge$getSoundSystem();
    void bridge$loadSoundSystem();
}
