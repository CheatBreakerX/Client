package com.cheatbreaker.bridge.client;

import com.cheatbreaker.bridge.client.audio.SoundHandlerBridge;
import com.cheatbreaker.bridge.client.gui.FontRendererBridge;
import com.cheatbreaker.bridge.client.gui.GuiScreenBridge;
import com.cheatbreaker.bridge.client.multiplayer.WorldClientBridge;
import com.cheatbreaker.bridge.client.renderer.texture.TextureManagerBridge;
import com.cheatbreaker.bridge.client.resources.IResourceManagerBridge;
import com.cheatbreaker.bridge.client.settings.GameSettingsBridge;
import com.cheatbreaker.bridge.entity.player.EntityPlayerBridge;
import com.cheatbreaker.bridge.util.TimerBridge;
import com.cheatbreaker.bridge.util.SessionBridge;
import net.minecraft.client.gui.GuiScreen;

import java.io.File;

public interface MinecraftBridge {
    SoundHandlerBridge bridge$getSoundHandler();
    int bridge$getDebugFPS();
    TimerBridge bridge$getTimer();
    FontRendererBridge bridge$getFontRenderer();
    GuiScreenBridge bridge$getCurrentScreen();
    void bridge$displayGuiScreen(GuiScreen screen);
    WorldClientBridge bridge$getTheWorld();
    GameSettingsBridge bridge$getGameSettings();
    EntityPlayerBridge bridge$getThePlayer();
    SessionBridge bridge$getSession();
    File bridge$getMcDataDir();
    long bridge$getSystemTime();
    boolean bridge$isInGameHasFocus();
    TextureManagerBridge bridge$getTextureManager();
    IResourceManagerBridge bridge$getResourceManager();
}
