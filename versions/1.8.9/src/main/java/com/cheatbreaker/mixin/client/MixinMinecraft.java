package com.cheatbreaker.mixin.client;

import com.cheatbreaker.bridge.client.MinecraftBridge;
import com.cheatbreaker.bridge.client.audio.SoundHandlerBridge;
import com.cheatbreaker.bridge.client.entity.EntityClientPlayerMPBridge;
import com.cheatbreaker.bridge.client.gui.FontRendererBridge;
import com.cheatbreaker.bridge.client.gui.GuiIngameBridge;
import com.cheatbreaker.bridge.client.multiplayer.WorldClientBridge;
import com.cheatbreaker.bridge.client.network.NetHandlerPlayClientBridge;
import com.cheatbreaker.bridge.client.renderer.EntityRendererBridge;
import com.cheatbreaker.bridge.client.renderer.RenderGlobalBridge;
import com.cheatbreaker.bridge.client.renderer.texture.TextureManagerBridge;
import com.cheatbreaker.bridge.client.resources.IResourceManagerBridge;
import com.cheatbreaker.bridge.client.settings.GameSettingsBridge;
import com.cheatbreaker.bridge.client.shader.FrameBufferBridge;
import com.cheatbreaker.bridge.util.SessionBridge;
import com.cheatbreaker.bridge.util.TimerBridge;
import com.cheatbreaker.bridge.wrapper.CBGuiScreen;
import com.google.common.util.concurrent.ListenableFuture;
import net.minecraft.client.Minecraft;
import net.minecraft.client.shader.Framebuffer;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

import java.io.File;
import java.net.Proxy;

@Mixin(Minecraft.class)
public abstract class MixinMinecraft implements MinecraftBridge {
    @Shadow @Final public File mcDataDir;

    @Shadow
    public static long getSystemTime() {
        return 0;
    }

    @Shadow public boolean inGameHasFocus;

    @Shadow @Final public static boolean isRunningOnMac;

    @Shadow public abstract void setIngameFocus();

    @Shadow public int displayWidth;

    @Shadow public int displayHeight;

    @Shadow public abstract void shutdown();

    @Shadow public abstract ListenableFuture<Object> addScheduledTask(Runnable runnableToSchedule);

    @Shadow public abstract Framebuffer getFramebuffer();

    @Shadow public abstract Proxy getProxy();

    public SoundHandlerBridge bridge$getSoundHandler() {
        return null;
    }

    public int bridge$getDebugFPS() {
        return 0;
    }

    public TimerBridge bridge$getTimer() {
        return null;
    }

    public FontRendererBridge bridge$getFontRenderer() {
        return null;
    }

    public CBGuiScreen bridge$getCurrentScreen() {
        return null;
    }

    public void bridge$displayGuiScreen(CBGuiScreen screen) {

    }

    public void bridge$displayInternalGuiScreen(InternalScreen screen, CBGuiScreen parent) {

    }

    public WorldClientBridge bridge$getTheWorld() {
        return null;
    }

    public GameSettingsBridge bridge$getGameSettings() {
        return null;
    }

    public EntityClientPlayerMPBridge bridge$getThePlayer() {
        return null;
    }

    public SessionBridge bridge$getSession() {
        return null;
    }

    public File bridge$getMcDataDir() {
        return this.mcDataDir;
    }

    public long bridge$getSystemTime() {
        return getSystemTime();
    }

    public boolean bridge$isInGameHasFocus() {
        return this.inGameHasFocus;
    }

    public TextureManagerBridge bridge$getTextureManager() {
        return null;
    }

    public IResourceManagerBridge bridge$getResourceManager() {
        return null;
    }

    public boolean bridge$isRunningOnMac() {
        return isRunningOnMac;
    }

    public void bridge$setIngameFocus() {
        this.setIngameFocus();
    }

    public EntityRendererBridge bridge$getEntityRenderer() {
        return null;
    }

    public int bridge$getDisplayWidth() {
        return this.displayWidth;
    }

    public int bridge$getDisplayHeight() {
        return this.displayHeight;
    }

    public void bridge$shutdown() {
        this.shutdown();
    }

    public GuiIngameBridge bridge$getIngameGUI() {
        return null;
    }

    public void bridge$func_152344_a(Runnable runnable) {
        this.addScheduledTask(runnable);
    }

    public RenderGlobalBridge bridge$getRenderGlobal() {
        return null;
    }

    public void bridge$scaledTessellator(int p_71392_1_, int p_71392_2_, int p_71392_3_, int p_71392_4_, int p_71392_5_, int p_71392_6_) {

    }

    public FrameBufferBridge bridge$getFramebuffer() {
        return (FrameBufferBridge) this.getFramebuffer();
    }

    public Proxy bridge$getProxy() {
        return this.getProxy();
    }

    public void bridge$func_147120_f() {

    }

    public NetHandlerPlayClientBridge bridge$getNetHandler() {
        return null;
    }
/*    @ModifyArg(method = "createDisplay", at = @At(value = "INVOKE", target = "Lorg/lwjgl/opengl/Display;setTitle(Ljava/lang/String;)V"))
    public String createDisplay(String newTitle) {
        return "ExampleClient";
    }*/
}
