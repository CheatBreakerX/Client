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
import com.cheatbreaker.bridge.client.renderer.TessellatorBridge;
import com.cheatbreaker.bridge.client.renderer.entity.RenderManagerBridge;
import com.cheatbreaker.bridge.client.renderer.texture.TextureManagerBridge;
import com.cheatbreaker.bridge.client.resources.IResourceManagerBridge;
import com.cheatbreaker.bridge.client.settings.GameSettingsBridge;
import com.cheatbreaker.bridge.client.shader.FrameBufferBridge;
import com.cheatbreaker.bridge.ref.Ref;
import com.cheatbreaker.bridge.util.SessionBridge;
import com.cheatbreaker.bridge.util.TimerBridge;
import com.cheatbreaker.bridge.wrapper.CBGuiScreen;
import com.cheatbreaker.util.WrappedGuiScreen;
import com.google.common.util.concurrent.ListenableFuture;
import net.minecraft.client.Minecraft;
import net.minecraft.client.audio.SoundHandler;
import net.minecraft.client.gui.*;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.client.network.NetHandlerPlayClient;
import net.minecraft.client.renderer.EntityRenderer;
import net.minecraft.client.renderer.RenderGlobal;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.client.resources.IReloadableResourceManager;
import net.minecraft.client.resources.LanguageManager;
import net.minecraft.client.settings.GameSettings;
import net.minecraft.client.shader.Framebuffer;
import net.minecraft.util.Session;
import net.minecraft.util.Timer;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

import java.io.File;
import java.net.Proxy;

@Mixin(Minecraft.class)
public abstract class MixinMinecraft implements MinecraftBridge {
    @Shadow @Final public File mcDataDir;
    @Shadow public static long getSystemTime() { return 0; }
    @Shadow public boolean inGameHasFocus;
    @Shadow @Final public static boolean isRunningOnMac;
    @Shadow public abstract void setIngameFocus();
    @Shadow public int displayWidth;
    @Shadow public int displayHeight;
    @Shadow public abstract void shutdown();
    @Shadow public abstract ListenableFuture<Object> addScheduledTask(Runnable runnableToSchedule);
    @Shadow public abstract Framebuffer getFramebuffer();
    @Shadow public abstract Proxy getProxy();
    @Shadow private static int debugFPS;
    @Shadow private Timer timer;
    @Shadow public FontRenderer fontRendererObj;
    @Shadow public RenderGlobal renderGlobal;
    @Shadow public EntityRenderer entityRenderer;
    @Shadow private IReloadableResourceManager mcResourceManager;
    @Shadow @Final private Session session;
    @Shadow public GameSettings gameSettings;
    @Shadow public WorldClient theWorld;
    @Shadow public abstract void displayGuiScreen(GuiScreen guiScreenIn);
    @Shadow public abstract LanguageManager getLanguageManager();
    @Shadow public abstract TextureManager getTextureManager();
    @Shadow public GuiIngame ingameGUI;
    @Shadow public abstract void updateDisplay();
    @Shadow public abstract NetHandlerPlayClient getNetHandler();
    @Shadow public abstract SoundHandler getSoundHandler();

    @Shadow public abstract RenderManager getRenderManager();

    public SoundHandlerBridge bridge$getSoundHandler() {
        return (SoundHandlerBridge) this.getSoundHandler();
    }

    public int bridge$getDebugFPS() {
        return debugFPS;
    }

    public TimerBridge bridge$getTimer() {
        return (TimerBridge) this.timer;
    }

    public FontRendererBridge bridge$getFontRenderer() {
        return (FontRendererBridge) this.fontRendererObj;
    }

    public CBGuiScreen bridge$getCurrentScreen() {
        return null;
    }

    public void bridge$displayGuiScreen(CBGuiScreen screen) {
        this.displayGuiScreen(new WrappedGuiScreen(screen));
    }

    public void bridge$displayInternalGuiScreen(InternalScreen screen, CBGuiScreen parent) {
        GuiScreen nativeParent = parent == null ? null : new WrappedGuiScreen(parent);

        switch (screen) {
            case SINGLEPLAYER:
                this.displayGuiScreen(new GuiSelectWorld(nativeParent));
            break;
            case MULTIPLAYER:
                this.displayGuiScreen(new GuiMultiplayer(nativeParent));
            break;
            case OPTIONS:
                this.displayGuiScreen(new GuiOptions(nativeParent, this.gameSettings));
            break;
            case LANGUAGE:
                this.displayGuiScreen(new GuiLanguage(nativeParent, this.gameSettings, this.getLanguageManager()));
            break;
        }
    }

    public WorldClientBridge bridge$getTheWorld() {
        return (WorldClientBridge) this.theWorld;
    }

    public GameSettingsBridge bridge$getGameSettings() {
        return (GameSettingsBridge) this.gameSettings;
    }

    public EntityClientPlayerMPBridge bridge$getThePlayer() {
        return null;
    }

    public SessionBridge bridge$getSession() {
        return (SessionBridge) this.session;
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
        return (TextureManagerBridge) this.getTextureManager();
    }

    public IResourceManagerBridge bridge$getResourceManager() {
        return (IResourceManagerBridge) this.mcResourceManager;
    }

    public boolean bridge$isRunningOnMac() {
        return isRunningOnMac;
    }

    public void bridge$setIngameFocus() {
        this.setIngameFocus();
    }

    public EntityRendererBridge bridge$getEntityRenderer() {
        return (EntityRendererBridge) this.entityRenderer;
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
        return (GuiIngameBridge) this.ingameGUI;
    }

    public void bridge$func_152344_a(Runnable runnable) {
        this.addScheduledTask(runnable);
    }

    public RenderGlobalBridge bridge$getRenderGlobal() {
        return (RenderGlobalBridge) this.renderGlobal;
    }

    public void bridge$scaledTessellator(int x, int y, int u, int v, int width, int height) {
        float var7 = 0.00390625F;
        float var8 = 0.00390625F;
        TessellatorBridge var9 = Ref.getTessellator();
        var9.bridge$startDrawingQuads();
        var9.bridge$addVertexWithUV(x, y + height, 0.0D, (float)(u) * var7, (float)(v + height) * var8);
        var9.bridge$addVertexWithUV(x + width, y + height, 0.0D, (float)(u + width) * var7, (float)(v + height) * var8);
        var9.bridge$addVertexWithUV(x + width, y, 0.0D, (float)(u + width) * var7, (float)(v) * var8);
        var9.bridge$addVertexWithUV(x, y, 0.0D, (float)(u) * var7, (float)(v) * var8);
        var9.bridge$finish();
    }

    public FrameBufferBridge bridge$getFramebuffer() {
        return (FrameBufferBridge) this.getFramebuffer();
    }

    public Proxy bridge$getProxy() {
        return this.getProxy();
    }

    public void bridge$func_147120_f() {
        this.updateDisplay();
    }

    public NetHandlerPlayClientBridge bridge$getNetHandler() {
        return (NetHandlerPlayClientBridge) this.getNetHandler();
    }

    public RenderManagerBridge bridge$getRenderManager() {
        return (RenderManagerBridge) this.getRenderManager();
    }
/*    @ModifyArg(method = "createDisplay", at = @At(value = "INVOKE", target = "Lorg/lwjgl/opengl/Display;setTitle(Ljava/lang/String;)V"))
    public String createDisplay(String newTitle) {
        return "ExampleClient";
    }*/
}
