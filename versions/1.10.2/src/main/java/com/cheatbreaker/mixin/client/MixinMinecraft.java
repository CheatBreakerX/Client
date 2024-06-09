package com.cheatbreaker.mixin.client;

import com.cheatbreaker.CheatBreakerMod;
import com.cheatbreaker.bridge.client.MinecraftBridge;
import com.cheatbreaker.bridge.client.audio.SoundHandlerBridge;
import com.cheatbreaker.bridge.client.entity.EntityClientPlayerMPBridge;
import com.cheatbreaker.bridge.client.entity.EntityPlayerSPBridge;
import com.cheatbreaker.bridge.client.gui.FontRendererBridge;
import com.cheatbreaker.bridge.client.gui.GuiIngameBridge;
import com.cheatbreaker.bridge.client.multiplayer.WorldClientBridge;
import com.cheatbreaker.bridge.client.network.NetHandlerPlayClientBridge;
import com.cheatbreaker.bridge.client.renderer.EntityRendererBridge;
import com.cheatbreaker.bridge.client.renderer.RenderGlobalBridge;
import com.cheatbreaker.bridge.client.renderer.TessellatorBridge;
import com.cheatbreaker.bridge.client.renderer.entity.RenderManagerBridge;
import com.cheatbreaker.bridge.client.renderer.texture.TextureManagerBridge;
import com.cheatbreaker.bridge.client.resources.DefaultResourcePackBridge;
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
import com.cheatbreaker.main.CheatBreaker;
import com.cheatbreaker.main.Icons;
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
import net.minecraft.client.resources.DefaultResourcePack;
import net.minecraft.client.resources.IReloadableResourceManager;
import net.minecraft.client.resources.LanguageManager;
import net.minecraft.client.settings.GameSettings;
import net.minecraft.client.shader.Framebuffer;
import net.minecraft.realms.RealmsBridge;
import net.minecraft.util.EnumHand;
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
    @Shadow @Final public static boolean IS_RUNNING_ON_MAC;
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
    @Shadow public abstract NetHandlerPlayClient getConnection();
    @Shadow public abstract SoundHandler getSoundHandler();
    @Shadow public abstract RenderManager getRenderManager();

    @Shadow protected abstract ByteBuffer readImageToBuffer(InputStream imageStream) throws IOException;

    @Shadow @Final private static Logger LOGGER;

    @Shadow public GuiScreen currentScreen;

    @Shadow @Final public DefaultResourcePack mcDefaultResourcePack;

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

    public Object bridge$getCurrentScreenNative() {
        return this.currentScreen;
    }

    private CBGuiScreen currentCBScreen = null;
    public void bridge$displayGuiScreen(CBGuiScreen screen) {
        this.currentCBScreen = screen;
        this.displayGuiScreen(screen == null ? null : new WrappedGuiScreen(screen));
    }

    public void bridge$displayGuiScreenNative(Object screen) {
        this.displayGuiScreen((GuiScreen) screen);
    }

    public void bridge$displayInternalGuiScreen(InternalScreen screen, CBGuiScreen parent) {
        // Creating "new WrappedGuiScreen(null)" will produce a NullPointerException for
        //    each method.
        GuiScreen nativeParent = parent == null ? null : new WrappedGuiScreen(parent);

        switch (screen) {
            case SINGLEPLAYER:
                this.displayGuiScreen(new GuiWorldSelection(nativeParent));
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
                return (NetHandlerPlayClientBridge) Minecraft.getMinecraft().thePlayer.connection;
            }

            public void bridge$setLocationCape(ResourceLocationBridge location) {
                ((EntityPlayerSPBridge) Minecraft.getMinecraft().thePlayer).bridge$setLocationCape(location);
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
                return Utils.itemStackToItemStackBridge(Minecraft.getMinecraft().thePlayer.getHeldItem(EnumHand.MAIN_HAND));
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

    public DefaultResourcePackBridge bridge$getDefaultResourcePack() {
        return (DefaultResourcePackBridge) this.mcDefaultResourcePack;
    }

    public boolean bridge$isRunningOnMac() {
        return IS_RUNNING_ON_MAC;
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
        return (NetHandlerPlayClientBridge) this.getConnection();
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
        return Utility.getFullTitle(CheatBreakerMod.MINECRAFT_VERSION);
    }

    @Inject(method = "displayGuiScreen", at = @At("HEAD"), cancellable = true)
    public void impl$displayGuiScreen(GuiScreen screen, CallbackInfo callbackInfo) {
        if (screen instanceof GuiMainMenu || (screen == null && this.theWorld == null)) {
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
                icon_x16 = Utility.base64StringToInputStream(Icons.CHEATBREAKER_ICON_16X);
                icon_x32 = Utility.base64StringToInputStream(Icons.CHEATBREAKER_ICON_32X);

                Display.setIcon(new ByteBuffer[] {
                        this.readImageToBuffer(icon_x16),
                        this.readImageToBuffer(icon_x32)
                });
            }
            catch (IOException ioexception) {
                LOGGER.error("[CB] Couldn't set icon", ioexception);
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

    @Inject(method = "runTickKeyboard", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/Minecraft;dispatchKeypresses()V", shift = At.Shift.AFTER))
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
        return this.theWorld == null && this.currentScreen != null ? 120 : this.gameSettings.limitFramerate;
    }
}
