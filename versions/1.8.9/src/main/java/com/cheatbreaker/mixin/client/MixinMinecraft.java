package com.cheatbreaker.mixin.client;

import com.cheatbreaker.CheatBreakerMod;
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
import com.cheatbreaker.bridge.entity.player.InventoryPlayerBridge;
import com.cheatbreaker.bridge.item.ItemStackBridge;
import com.cheatbreaker.bridge.potion.PotionEffectBridge;
import com.cheatbreaker.bridge.ref.Ref;
import com.cheatbreaker.bridge.util.*;
import com.cheatbreaker.bridge.wrapper.CBGuiScreen;
import com.cheatbreaker.client.event.type.KeyboardEvent;
import com.cheatbreaker.client.ui.mainmenu.MainMenu;
import com.cheatbreaker.client.ui.overlay.OverlayGui;
import com.cheatbreaker.client.ui.util.RenderUtil;
import com.cheatbreaker.impl.ref.InstanceCreator;
import com.cheatbreaker.main.CheatBreaker;
import com.cheatbreaker.main.utils.Utility;
import com.cheatbreaker.util.Utils;
import com.cheatbreaker.util.WrappedGuiScreen;
import com.google.common.util.concurrent.ListenableFuture;
import com.mojang.authlib.GameProfile;
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
import net.minecraft.realms.RealmsBridge;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Session;
import net.minecraft.util.Timer;
import net.minecraft.util.Util;
import org.apache.commons.io.IOUtils;
import org.apache.logging.log4j.Logger;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.Display;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.Proxy;
import java.nio.ByteBuffer;
import java.util.Collection;
import java.util.UUID;

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

    @Shadow protected abstract ByteBuffer readImageToBuffer(InputStream imageStream) throws IOException;

    @Shadow @Final private static Logger logger;

    @Shadow public GuiScreen currentScreen;

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

    // TODO: Create com.cheatbreaker.util.UnwrappedGuiScreen
    public CBGuiScreen bridge$getCurrentScreen() {
        // TODO: return this.currentCBScreen == null ? new UnwrappedGuiScreen(this.currentScreen) : this.currentCBScreen;
        return this.currentCBScreen;
    }

    private CBGuiScreen currentCBScreen = null;
    public void bridge$displayGuiScreen(CBGuiScreen screen) {
        this.currentCBScreen = screen;
        this.displayGuiScreen(screen == null ? null : new WrappedGuiScreen(screen));
    }

    public void bridge$displayInternalGuiScreen(InternalScreen screen, CBGuiScreen parent) {
        // Creating "new WrappedGuiScreen(null)" will produce a NullPointerException for
        //    each method.
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
            case REALMS:
                new RealmsBridge().switchToRealms(nativeParent);
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
        return new EntityClientPlayerMPBridge() {
            public NetHandlerPlayClientBridge bridge$getSendQueue() {
                return (NetHandlerPlayClientBridge) Minecraft.getMinecraft().thePlayer.sendQueue;
            }

            public void bridge$setLocationCape(ResourceLocationBridge location) {

            }

            public String bridge$getDisplayName() {
                return Minecraft.getMinecraft().thePlayer.getDisplayName().getFormattedText();
            }

            public String bridge$getCommandSenderName() {
                return Minecraft.getMinecraft().thePlayer.getCommandSenderEntity().getName();
            }

            public GameProfile bridge$getGameProfile() {
                return Minecraft.getMinecraft().thePlayer.getGameProfile();
            }

            public InventoryPlayerBridge bridge$getInventory() {
                return (InventoryPlayerBridge) Minecraft.getMinecraft().thePlayer.inventory;
            }

            public ItemStackBridge bridge$getCurrentEquippedItem() {
                return Utils.itemStackToItemStackBridge(Minecraft.getMinecraft().thePlayer.getCurrentEquippedItem());
            }

            public Collection<PotionEffectBridge> bridge$getActivePotionEffects() {
                return Utils.convertCollectionType(Minecraft.getMinecraft().thePlayer.getActivePotionEffects());
            }

            public double bridge$getPosX() {
                return Minecraft.getMinecraft().thePlayer.posX;
            }

            public double bridge$getPosY() {
                return Minecraft.getMinecraft().thePlayer.posY;
            }

            public double bridge$getPosZ() {
                return Minecraft.getMinecraft().thePlayer.posZ;
            }

            public UUID bridge$getUniqueID() {
                return Minecraft.getMinecraft().thePlayer.getUniqueID();
            }

            public double bridge$getLastTickPosX() {
                return Minecraft.getMinecraft().thePlayer.lastTickPosX;
            }

            public double bridge$getLastTickPosY() {
                return Minecraft.getMinecraft().thePlayer.lastTickPosY;
            }

            public double bridge$getLastTickPosZ() {
                return Minecraft.getMinecraft().thePlayer.lastTickPosZ;
            }

            public float bridge$getRotationPitch() {
                return Minecraft.getMinecraft().thePlayer.rotationPitch;
            }

            public float bridge$getRotationYaw() {
                return Minecraft.getMinecraft().thePlayer.rotationYaw;
            }

            public AxisAlignedBBBridge bridge$getBoundingBox() {
                return (AxisAlignedBBBridge) Minecraft.getMinecraft().thePlayer.getEntityBoundingBox();
            }

            public float bridge$getHeight() {
                return Minecraft.getMinecraft().thePlayer.height;
            }

            public IChatComponentBridge bridge$func_145748_c_() {
                return (IChatComponentBridge) Minecraft.getMinecraft().thePlayer.getDisplayName();
            }
        };
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
        var9.bridge$pos(x, y + height, 0.0D).bridge$tex((float)(u) * var7, (float)(v + height) * var8).bridge$endVertex();
        var9.bridge$pos(x + width, y + height, 0.0D).bridge$tex((float)(u + width) * var7, (float)(v + height) * var8).bridge$endVertex();
        var9.bridge$pos(x + width, y, 0.0D).bridge$tex((float)(u + width) * var7, (float)(v) * var8).bridge$endVertex();
        var9.bridge$pos(x, y, 0.0D).bridge$tex((float)(u) * var7, (float)(v) * var8).bridge$endVertex();
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

    public boolean bridge$isIngame() {
        return this.currentScreen == null;
    }

    public void bridge$goIngame() {
        this.currentCBScreen = null;
        this.displayGuiScreen(null);
    }

    @ModifyArg(method = "createDisplay", at = @At(value = "INVOKE", target = "Lorg/lwjgl/opengl/Display;setTitle(Ljava/lang/String;)V"))
    public String mod$createDisplay(String newTitle) {
        return "CheatBreaker 1.8.9";
    }

    @Inject(method = "displayGuiScreen", at = @At("HEAD"), cancellable = true)
    public void impl$displayGuiScreen(GuiScreen screen, CallbackInfo callbackInfo) {
        if (screen instanceof GuiMainMenu) {
            this.bridge$displayGuiScreen(new MainMenu());
            callbackInfo.cancel();
        }

        if (!(screen instanceof WrappedGuiScreen)) {
            this.currentCBScreen = null;
        }
    }

    @Inject(method = "startGame", at = @At(value = "INVOKE", target = "Lnet/minecraftforge/client/GuiIngameForge;<init>(Lnet/minecraft/client/Minecraft;)V"))
    public void impl$startGame$guiIngameInit(CallbackInfo callbackInfo) {
        CheatBreakerMod.CheatBreaker$preInitialize();
        CheatBreaker.getInstance().initialize();
        Display.setTitle(Utility.getFullTitle());
    }

    /**
     * @author iAmSpace
     * @reason Custom CheatBreaker icon
     */
    @Overwrite
    private void setWindowIcon() {
        Util.EnumOS osType = Util.getOSType();

        if (osType != Util.EnumOS.OSX) {
            InputStream icon_x16 = null;
            InputStream icon_x32 = null;

            try {
                icon_x16 = Utility.base64StringToInputStream("data:image/png;base64,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/GMzLdsAADoyaVRYdFhNTDpjb20uYWRvYmUueG1wAAAAAAA8P3hwYWNrZXQgYmVnaW49Iu+7vyIgaWQ9Ilc1TTBNcENlaGlIenJlU3pOVGN6a2M5ZCI/Pgo8eDp4bXBtZXRhIHhtbG5zOng9ImFkb2JlOm5zOm1ldGEvIiB4OnhtcHRrPSJBZG9iZSBYTVAgQ29yZSA1LjYtYzEzOCA3OS4xNTk4MjQsIDIwMTYvMDkvMTQtMDE6MDk6MDEgICAgICAgICI+CiAgIDxyZGY6UkRGIHhtbG5zOnJkZj0iaHR0cDovL3d3dy53My5vcmcvMTk5OS8wMi8yMi1yZGYtc3ludGF4LW5zIyI+CiAgICAgIDxyZGY6RGVzY3JpcHRpb24gcmRmOmFib3V0PSIiCiAgICAgICAgICAgIHhtbG5zOnhtcD0iaHR0cDovL25zLmFkb2JlLmNvbS94YXAvMS4wLyIKICAgICAgICAgICAgeG1sbnM6eG1wTU09Imh0dHA6Ly9ucy5hZG9iZS5jb20veGFwLzEuMC9tbS8iCiAgICAgICAgICAgIHhtbG5zOnN0RXZ0PSJodHRwOi8vbnMuYWRvYmUuY29tL3hhcC8xLjAvc1R5cGUvUmVzb3VyY2VFdmVudCMiCiAgICAgICAgICAgIHhtbG5zOmRjPSJodHRwOi8vcHVybC5vcmcvZGMvZWxlbWVudHMvMS4xLyIKICAgICAgICAgICAgeG1sbnM6cGhvdG9zaG9wPSJodHRwOi8vbnMuYWRvYmUuY29tL3Bob3Rvc2hvcC8xLjAvIgogICAgICAgICAgICB4bWxuczp0aWZmPSJodHRwOi8vbnMuYWRvYmUuY29tL3RpZmYvMS4wLyIKICAgICAgICAgICAgeG1sbnM6ZXhpZj0iaHR0cDovL25zLmFkb2JlLmNvbS9leGlmLzEuMC8iPgogICAgICAgICA8eG1wOkNyZWF0b3JUb29sPkFkb2JlIFBob3Rvc2hvcCBDQyAyMDE3IChXaW5kb3dzKTwveG1wOkNyZWF0b3JUb29sPgogICAgICAgICA8eG1wOkNyZWF0ZURhdGU+MjAxOC0wMy0xNlQwMDoyMTozMCswMTowMDwveG1wOkNyZWF0ZURhdGU+CiAgICAgICAgIDx4bXA6TWV0YWRhdGFEYXRlPjIwMTgtMDMtMTZUMDA6MjE6MzArMDE6MDA8L3htcDpNZXRhZGF0YURhdGU+CiAgICAgICAgIDx4bXA6TW9kaWZ5RGF0ZT4yMDE4LTAzLTE2VDAwOjIxOjMwKzAxOjAwPC94bXA6TW9kaWZ5RGF0ZT4KICAgICAgICAgPHhtcE1NOkluc3RhbmNlSUQ+eG1wLmlpZDo0NzZmOWNkYS1hYTZlLWMyNDEtODEzYy1lZjkxZjhhNTJiYWM8L3htcE1NOkluc3RhbmNlSUQ+CiAgICAgICAgIDx4bXBNTTpEb2N1bWVudElEPmFkb2JlOmRvY2lkOnBob3Rvc2hvcDo5MjEwODIyNy0yOGE3LTExZTgtOTEwOC1lODNmNmU0NDgzNDU8L3htcE1NOkRvY3VtZW50SUQ+CiAgICAgICAgIDx4bXBNTTpPcmlnaW5hbERvY3VtZW50SUQ+eG1wLmRpZDo3OTNhM2E1ZS0yYzZhLWM4NDAtOTcxYS1iNWI4NjQ3YzZjY2U8L3htcE1NOk9yaWdpbmFsRG9jdW1lbnRJRD4KICAgICAgICAgPHhtcE1NOkhpc3Rvcnk+CiAgICAgICAgICAgIDxyZGY6U2VxPgogICAgICAgICAgICAgICA8cmRmOmxpIHJkZjpwYXJzZVR5cGU9IlJlc291cmNlIj4KICAgICAgICAgICAgICAgICAgPHN0RXZ0OmFjdGlvbj5jcmVhdGVkPC9zdEV2dDphY3Rpb24+CiAgICAgICAgICAgICAgICAgIDxzdEV2dDppbnN0YW5jZUlEPnhtcC5paWQ6NzkzYTNhNWUtMmM2YS1jODQwLTk3MWEtYjViODY0N2M2Y2NlPC9zdEV2dDppbnN0YW5jZUlEPgogICAgICAgICAgICAgICAgICA8c3RFdnQ6d2hlbj4yMDE4LTAzLTE2VDAwOjIxOjMwKzAxOjAwPC9zdEV2dDp3aGVuPgogICAgICAgICAgICAgICAgICA8c3RFdnQ6c29mdHdhcmVBZ2VudD5BZG9iZSBQaG90b3Nob3AgQ0MgMjAxNyAoV2luZG93cyk8L3N0RXZ0OnNvZnR3YXJlQWdlbnQ+CiAgICAgICAgICAgICAgIDwvcmRmOmxpPgogICAgICAgICAgICAgICA8cmRmOmxpIHJkZjpwYXJzZVR5cGU9IlJlc291cmNlIj4KICAgICAgICAgICAgICAgICAgPHN0RXZ0OmFjdGlvbj5zYXZlZDwvc3RFdnQ6YWN0aW9uPgogICAgICAgICAgICAgICAgICA8c3RFdnQ6aW5zdGFuY2VJRD54bXAuaWlkOjQ3NmY5Y2RhLWFhNmUtYzI0MS04MTNjLWVmOTFmOGE1MmJhYzwvc3RFdnQ6aW5zdGFuY2VJRD4KICAgICAgICAgICAgICAgICAgPHN0RXZ0OndoZW4+MjAxOC0wMy0xNlQwMDoyMTozMCswMTowMDwvc3RFdnQ6d2hlbj4KICAgICAgICAgICAgICAgICAgPHN0RXZ0OnNvZnR3YXJlQWdlbnQ+QWRvYmUgUGhvdG9zaG9wIENDIDIwMTcgKFdpbmRvd3MpPC9zdEV2dDpzb2Z0d2FyZUFnZW50PgogICAgICAgICAgICAgICAgICA8c3RFdnQ6Y2hhbmdlZD4vPC9zdEV2dDpjaGFuZ2VkPgogICAgICAgICAgICAgICA8L3JkZjpsaT4KICAgICAgICAgICAgPC9yZGY6U2VxPgogICAgICAgICA8L3htcE1NOkhpc3Rvcnk+CiAgICAgICAgIDxkYzpmb3JtYXQ+aW1hZ2UvcG5nPC9kYzpmb3JtYXQ+CiAgICAgICAgIDxwaG90b3Nob3A6Q29sb3JNb2RlPjM8L3Bob3Rvc2hvcDpDb2xvck1vZGU+CiAgICAgICAgIDxwaG90b3Nob3A6SUNDUHJvZmlsZT5zUkdCIElFQzYxOTY2LTIuMTwvcGhvdG9zaG9wOklDQ1Byb2ZpbGU+CiAgICAgICAgIDx0aWZmOk9yaWVudGF0aW9uPjE8L3RpZmY6T3JpZW50YXRpb24+CiAgICAgICAgIDx0aWZmOlhSZXNvbHV0aW9uPjExODExMDAvMTAwMDA8L3RpZmY6WFJlc29sdXRpb24+CiAgICAgICAgIDx0aWZmOllSZXNvbHV0aW9uPjExODExMDAvMTAwMDA8L3RpZmY6WVJlc29sdXRpb24+CiAgICAgICAgIDx0aWZmOlJlc29sdXRpb25Vbml0PjM8L3RpZmY6UmVzb2x1dGlvblVuaXQ+CiAgICAgICAgIDxleGlmOkNvbG9yU3BhY2U+MTwvZXhpZjpDb2xvclNwYWNlPgogICAgICAgICA8ZXhpZjpQaXhlbFhEaW1lbnNpb24+MzI8L2V4aWY6UGl4ZWxYRGltZW5zaW9uPgogICAgICAgICA8ZXhpZjpQaXhlbFlEaW1lbnNpb24+MzI8L2V4aWY6UGl4ZWxZRGltZW5zaW9uPgogICAgICA8L3JkZjpEZXNjcmlwdGlvbj4KICAgPC9yZGY6UkRGPgo8L3g6eG1wbWV0YT4KICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgIAogICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgCiAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAKICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgIAogICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgCiAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAKICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgIAogICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgCiAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAKICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgIAogICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgCiAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAKICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgIAogICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgCiAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAKICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgIAogICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgCiAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAKICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgIAogICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgCiAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAKICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgIAogICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgCiAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAKICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgIAogICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgCiAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAKICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgIAogICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgCiAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAKICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgIAogICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgCiAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAKICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgIAogICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgCiAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAKICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgIAogICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgCiAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAKICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgIAogICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgCiAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAKICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgIAogICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgCiAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAKICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgIAogICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgCiAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAKICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgIAogICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgCiAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAKICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgIAogICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgCiAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAKICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgIAogICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgCiAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAKICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgIAogICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgCiAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAKICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgIAogICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgCiAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAKICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgIAogICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgCiAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAKICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgIAogICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgCiAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAKICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgIAogICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgCiAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAKICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgIAogICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgCiAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAKICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgIAogICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgCiAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAKICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgIAogICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgCiAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAKICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgIAogICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgCiAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAKICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgIAogICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgCiAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAKICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgIAogICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgCiAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAKICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgIAogICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgCiAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAKICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgIAogICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgCiAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAKICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgIAogICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgCiAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAKICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgIAogICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgCiAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAKICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgIAogICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgCiAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAKICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgIAogICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgCiAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAKICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgIAogICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgCiAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAKICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgIAogICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgCiAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAKICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgIAogICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgCiAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAKICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgIAogICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgCiAgICAgICAgICAgICAgICAgICAgICAgICAgICAKPD94cGFja2V0IGVuZD0idyI/Pkzolt0AAAAgY0hSTQAAeiUAAICDAAD5/wAAgOkAAHUwAADqYAAAOpgAABdvkl/FRgAACdtJREFUeNqUl3usZXV1xz/r9/vt5zl3rvfO3Hl1RBgLDDAdRQaZVtvUNGlQMEOspA9aKKJtY8TGaEfTaEuKKVFJI0aE2rQqSI0psU4lthgKCB0KdRCRlzovZhimdyYzc9/nnL1/j9U/zrmX0bak/SU7O9mvtfb6ftf3u36iqjyz8/cAEIGYlJQUa4SUFARylyECbQigSlJ9UxvCzoT+thXjx8rybp/0W5K552oxoEpIib5vscZgRGhjpMoynLXEmOi1LRffdw+OV1mCgICIoJouVdWdCjsR2ZpZS4gJEWFxMLglqt6SR7e3Z+zuIst2G5FnRAQRebUQ/z0BGR0AibQjhHRViPFKVb3IiGCMGd2DzFmMGJrgcdZgRLb3fbu9if5mQZ5S1d3i3G5n7Q/+t0TcclBGDySNb/Up7bQqV0TVC2KKqAyDhpSorCUlHQY1liIz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");
                icon_x32 = Utility.base64StringToInputStream("data:image/png;base64,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");

                Display.setIcon(new ByteBuffer[] {
                        this.readImageToBuffer(icon_x16),
                        this.readImageToBuffer(icon_x32)
                });
            }
            catch (IOException ioexception) {
                logger.error("[CB] Couldn't set icon", ioexception);
            }
            finally {
                IOUtils.closeQuietly(icon_x16);
                IOUtils.closeQuietly(icon_x32);
            }
        }
    }

    private long previousFrameOccurringTime = 0L;

    @Inject(method = "runGameLoop", at = @At(value = "INVOKE_STRING", target = "Lnet/minecraft/client/Minecraft;checkGLError(Ljava/lang/String;)V", args = "ldc=Post render"))
    public void impl$runGameLoop(CallbackInfo callbackInfo) {
        RenderUtil.updateFrameTime(previousFrameOccurringTime);
        previousFrameOccurringTime = System.nanoTime();
    }

    @Inject(method = "runTick", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/GuiScreen;handleInput()V", shift = At.Shift.AFTER))
    public void impl$runTick$handleInput(CallbackInfo callbackInfo) {
        OverlayGui.getInstance().pollNotifications();
    }

    @Inject(method = "runTick", at = @At(value = "INVOKE_STRING", target = "Lnet/minecraft/profiler/Profiler;endStartSection(Ljava/lang/String;)V", args = "ldc=mouse", shift = At.Shift.BEFORE))
    public void impl$runTick$endStartSection(CallbackInfo callbackInfo) {
        OverlayGui.getInstance().pollNotifications();
    }

    @Inject(method = "runTick", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/Minecraft;dispatchKeypresses()V", shift = At.Shift.AFTER))
    public void impl$runTick$getEventKeyState(CallbackInfo callbackInfo) {
        if (Keyboard.getEventKeyState()) {
            CheatBreaker.getInstance().getEventBus().callEvent(new KeyboardEvent(Keyboard.getEventKey()));

            if (Keyboard.isKeyDown(42) && Keyboard.getEventKey() == 15) {
                this.bridge$displayGuiScreen(OverlayGui.getInstance());
            }
        }
    }

    /**
     * @author iAmSpace
     * @reason big hz
     */
    @Overwrite
    public int getLimitFramerate() {
        return this.gameSettings.limitFramerate;
    }
}
