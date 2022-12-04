package com.cheatbreaker.bridge.client;

import com.cheatbreaker.bridge.client.audio.SoundHandlerBridge;
import com.cheatbreaker.bridge.client.entity.EntityClientPlayerMPBridge;
import com.cheatbreaker.bridge.client.gui.FontRendererBridge;
import com.cheatbreaker.bridge.client.gui.GuiIngameBridge;
import com.cheatbreaker.bridge.client.multiplayer.WorldClientBridge;
import com.cheatbreaker.bridge.client.network.NetHandlerPlayClientBridge;
import com.cheatbreaker.bridge.client.renderer.EntityRendererBridge;
import com.cheatbreaker.bridge.client.renderer.RenderGlobalBridge;
import com.cheatbreaker.bridge.client.renderer.entity.RenderManagerBridge;
import com.cheatbreaker.bridge.client.renderer.texture.TextureManagerBridge;
import com.cheatbreaker.bridge.client.resources.IResourceManagerBridge;
import com.cheatbreaker.bridge.client.settings.GameSettingsBridge;
import com.cheatbreaker.bridge.client.shader.FrameBufferBridge;
import com.cheatbreaker.bridge.util.SessionBridge;
import com.cheatbreaker.bridge.util.TimerBridge;
import com.cheatbreaker.bridge.wrapper.CBGuiScreen;

import java.io.File;
import java.net.Proxy;

public interface MinecraftBridge {
    SoundHandlerBridge bridge$getSoundHandler();
    int bridge$getDebugFPS();
    TimerBridge bridge$getTimer();
    FontRendererBridge bridge$getFontRenderer();
    CBGuiScreen bridge$getCurrentScreen();
    void bridge$displayGuiScreen(CBGuiScreen screen);
    void bridge$displayInternalGuiScreen(InternalScreen screen, CBGuiScreen parent);
    WorldClientBridge bridge$getTheWorld();
    GameSettingsBridge bridge$getGameSettings();
    EntityClientPlayerMPBridge bridge$getThePlayer();
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
    GuiIngameBridge bridge$getIngameGUI();
    void bridge$func_152344_a(Runnable runnable);
    RenderGlobalBridge bridge$getRenderGlobal();
    void bridge$scaledTessellator(int p_71392_1_, int p_71392_2_, int p_71392_3_, int p_71392_4_, int p_71392_5_, int p_71392_6_);
    FrameBufferBridge bridge$getFramebuffer();
    Proxy bridge$getProxy();
    void bridge$func_147120_f(); // resetSize
    NetHandlerPlayClientBridge bridge$getNetHandler();
    RenderManagerBridge bridge$getRenderManager();
    boolean bridge$isIngame();
    void bridge$goIngame();

    enum InternalScreen {
        SINGLEPLAYER,
        MULTIPLAYER,
        OPTIONS,
        LANGUAGE,
        REALMS
    }
}
