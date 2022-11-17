package com.cheatbreaker.bridge.client;

import com.cheatbreaker.bridge.client.audio.ISoundBridge;
import com.cheatbreaker.bridge.client.audio.SoundHandlerBridge;
import com.cheatbreaker.bridge.client.gui.FontRendererBridge;
import com.cheatbreaker.bridge.client.multiplayer.WorldClientBridge;
import com.cheatbreaker.bridge.client.renderer.EntityRendererBridge;
import com.cheatbreaker.bridge.client.renderer.texture.TextureManagerBridge;
import com.cheatbreaker.bridge.client.resources.IResourceManagerBridge;
import com.cheatbreaker.bridge.client.settings.GameSettingsBridge;
import com.cheatbreaker.bridge.entity.player.EntityPlayerBridge;
import com.cheatbreaker.bridge.util.SessionBridge;
import com.cheatbreaker.bridge.util.TimerBridge;
import com.cheatbreaker.bridge.wrapper.CBGuiScreen;

import java.io.File;

public interface MinecraftBridge {
    SoundHandlerBridge bridge$getSoundHandler();
    int bridge$getDebugFPS();
    TimerBridge bridge$getTimer();
    FontRendererBridge bridge$getFontRenderer();
    CBGuiScreen bridge$getCurrentScreen();
    void bridge$displayGuiScreen(CBGuiScreen screen);
    WorldClientBridge bridge$getTheWorld();
    GameSettingsBridge bridge$getGameSettings();
    EntityPlayerBridge bridge$getThePlayer();
    SessionBridge bridge$getSession();
    File bridge$getMcDataDir();
    long bridge$getSystemTime();
    boolean bridge$isInGameHasFocus();
    TextureManagerBridge bridge$getTextureManager();
    IResourceManagerBridge bridge$getResourceManager();
    boolean bridge$isRunningOnMac();
    void bridge$setIngameFocus();
    EntityRendererBridge bridge$getEntityRenderer();
    int bridge$getDisplayWidth();
    int bridge$getDisplayHeight();
    void bridge$shutdown();
}
