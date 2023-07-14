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
import com.cheatbreaker.main.utils.Utility;
import com.cheatbreaker.util.Utils;
import com.cheatbreaker.util.WrappedGuiScreen;
import com.mojang.authlib.GameProfile;
import com.mojang.blaze3d.pipeline.RenderTarget;
import com.mojang.blaze3d.platform.Window;
import net.minecraft.Util;
import net.minecraft.client.*;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.screens.LanguageSelectScreen;
import net.minecraft.client.gui.screens.OptionsScreen;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.gui.screens.TitleScreen;
import net.minecraft.client.gui.screens.multiplayer.JoinMultiplayerScreen;
import net.minecraft.client.gui.screens.worldselection.SelectWorldScreen;
import net.minecraft.client.multiplayer.ClientPacketListener;
import net.minecraft.client.multiplayer.MultiPlayerLevel;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.client.renderer.LevelRenderer;
import net.minecraft.client.renderer.entity.EntityRenderDispatcher;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.client.resources.ClientPackSource;
import net.minecraft.client.resources.language.LanguageManager;
import net.minecraft.client.sounds.SoundManager;
import net.minecraft.realms.RealmsBridge;
import net.minecraft.server.packs.resources.ReloadableResourceManager;
import net.minecraft.world.InteractionHand;
import org.lwjgl.glfw.GLFW;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import javax.annotation.Nullable;
import java.io.File;
import java.net.Proxy;
import java.util.Collection;
import java.util.Queue;
import java.util.UUID;

@Mixin(Minecraft.class)
public abstract class MixinMinecraft implements MinecraftBridge {
    @Shadow public abstract SoundManager getSoundManager();
    @Shadow private static int fps;
    @Shadow @Final private Timer timer;
    @Shadow public Font font;
    @Shadow public abstract void setScreen(Screen par1);
    @Shadow public Options options;
    @Shadow @Final private User user;
    @Shadow @Final public File gameDirectory;
    @Shadow public abstract TextureManager getTextureManager();
    @Shadow private ReloadableResourceManager resourceManager;
    @Shadow public Screen screen;
    @Shadow public Window window;
    @Shadow public abstract void close();
    @Shadow @Final public static boolean ON_OSX;
    @Shadow public MouseHandler mouseHandler;
    @Shadow public abstract RenderTarget getMainRenderTarget();
    @Shadow public abstract Proxy getProxy();
    @Shadow public abstract void updateDisplay(boolean limitFramerate);
    @Shadow protected abstract boolean isFramerateLimited();
    @Shadow @Nullable public abstract ClientPacketListener getConnection();
    @Shadow public abstract LanguageManager getLanguageManager();
    @Shadow public MultiPlayerLevel level;
    @Shadow public Gui gui;
    @Shadow public LevelRenderer levelRenderer;
    @Shadow @Final private Queue<Runnable> progressTasks;
    @Shadow public abstract EntityRenderDispatcher getEntityRenderDispatcher();
    @Shadow public GameRenderer gameRenderer;

    @Shadow @Final private ClientPackSource clientPackSource;

    public SoundHandlerBridge bridge$getSoundHandler() {
        return (SoundHandlerBridge) this.getSoundManager();
    }

    public int bridge$getDebugFPS() {
        return fps;
    }

    public TimerBridge bridge$getTimer() {
        return (TimerBridge) this.timer;
    }

    public FontRendererBridge bridge$getFontRenderer() {
        return (FontRendererBridge) this.font;
    }

    // TODO: Create com.cheatbreaker.util.UnwrappedGuiScreen
    public CBGuiScreen bridge$getCurrentScreen() {
        // TODO: return this.currentCBScreen == null ? new UnwrappedGuiScreen(this.currentScreen) : this.currentCBScreen;
        return this.currentCBScreen;
    }

    private CBGuiScreen currentCBScreen = null;
    public void bridge$displayGuiScreen(CBGuiScreen screen) {
        this.currentCBScreen = screen;
        this.setScreen(screen == null ? null : new WrappedGuiScreen(screen));
    }

    public void bridge$displayInternalGuiScreen(InternalScreen screen, CBGuiScreen parent) {
        // Creating "new WrappedGuiScreen(null)" will produce a NullPointerException for
        //    each method.
        Screen nativeParent = parent == null ? null : new WrappedGuiScreen(parent);

        switch (screen) {
            case SINGLEPLAYER:
                this.setScreen(new SelectWorldScreen(nativeParent));
            break;
            case MULTIPLAYER:
                this.setScreen(new JoinMultiplayerScreen(nativeParent));
            break;
            case OPTIONS:
                this.setScreen(new OptionsScreen(nativeParent, this.options));
            break;
            case LANGUAGE:
                this.setScreen(new LanguageSelectScreen(nativeParent, this.options, this.getLanguageManager()));
            break;
            case REALMS:
                new RealmsBridge().switchToRealms(nativeParent);
            break;
        }
    }

    public WorldClientBridge bridge$getTheWorld() {
        return (WorldClientBridge) this.level;
    }

    public GameSettingsBridge bridge$getGameSettings() {
        return (GameSettingsBridge) this.options;
    }

    public EntityClientPlayerMPBridge bridge$getThePlayer() {
        return new EntityClientPlayerMPBridge() {
            public NetHandlerPlayClientBridge bridge$getSendQueue() {
                return (NetHandlerPlayClientBridge) Minecraft.getInstance().player.connection;
            }

            public void bridge$setLocationCape(ResourceLocationBridge location) {
                ((EntityPlayerSPBridge) Minecraft.getInstance().player).bridge$setLocationCape(location);
            }

            public String bridge$getDisplayName() {
                return Minecraft.getInstance().player.getDisplayName().getColoredString();
            }

            public String bridge$getCommandSenderName() {
                return Minecraft.getInstance().player.getName().getColoredString();
            }

            public GameProfile bridge$getGameProfile() {
                return Minecraft.getInstance().player.getGameProfile();
            }

            public InventoryPlayerBridge bridge$getInventory() {
                return (InventoryPlayerBridge) Minecraft.getInstance().player.inventory;
            }

            public ItemStackBridge bridge$getCurrentEquippedItem() {
                return Utils.itemStackToItemStackBridge(Minecraft.getInstance().player.getItemInHand(InteractionHand.MAIN_HAND));
            }

            public Collection<PotionEffectBridge> bridge$getActivePotionEffects() {
                return Utils.convertCollectionType(Minecraft.getInstance().player.getActiveEffects());
            }

            public double bridge$getPosX() {
                return Minecraft.getInstance().player.x;
            }

            public double bridge$getPosY() {
                return Minecraft.getInstance().player.y;
            }

            public double bridge$getPosZ() {
                return Minecraft.getInstance().player.z;
            }

            public UUID bridge$getUniqueID() {
                return Minecraft.getInstance().player.getUUID();
            }

            public double bridge$getLastTickPosX() {
                return Minecraft.getInstance().player.xOld;
            }

            public double bridge$getLastTickPosY() {
                return Minecraft.getInstance().player.yOld;
            }

            public double bridge$getLastTickPosZ() {
                return Minecraft.getInstance().player.zOld;
            }

            public float bridge$getRotationPitch() {
                return Minecraft.getInstance().player.xRot;
            }

            public float bridge$getRotationYaw() {
                return Minecraft.getInstance().player.yRot;
            }

            public AxisAlignedBBBridge bridge$getBoundingBox() {
                return (AxisAlignedBBBridge) Minecraft.getInstance().player.getBoundingBox();
            }

            public float bridge$getHeight() {
                return Minecraft.getInstance().player.getBbHeight();
            }

            public IChatComponentBridge bridge$func_145748_c_() {
                return (IChatComponentBridge) Minecraft.getInstance().player.getDisplayName();
            }
        };
    }

    public SessionBridge bridge$getSession() {
        return (SessionBridge) this.user;
    }

    public File bridge$getMcDataDir() {
        return this.gameDirectory;
    }

    public long bridge$getSystemTime() {
        return Util.getMillis();
    }

    public boolean bridge$isInGameHasFocus() {
        return this.mouseHandler.isMouseGrabbed();
    }

    public TextureManagerBridge bridge$getTextureManager() {
        return (TextureManagerBridge) this.getTextureManager();
    }

    public IResourceManagerBridge bridge$getResourceManager() {
        return (IResourceManagerBridge) this.resourceManager;
    }

    public DefaultResourcePackBridge bridge$getDefaultResourcePack() {
        return (DefaultResourcePackBridge) this.clientPackSource.getVanillaPack();
    }

    public boolean bridge$isRunningOnMac() {
        return ON_OSX;
    }

    public void bridge$setIngameFocus() {
        this.mouseHandler.grabMouse();
    }

    public EntityRendererBridge bridge$getEntityRenderer() {
        return (EntityRendererBridge) this.gameRenderer;
    }

    public int bridge$getDisplayWidth() {
        return this.window.getWidth();
    }

    public int bridge$getDisplayHeight() {
        return this.window.getHeight();
    }

    public void bridge$shutdown() {
        this.close();
    }

    public GuiIngameBridge bridge$getIngameGUI() {
        return (GuiIngameBridge) this.gui;
    }

    public void bridge$func_152344_a(Runnable runnable) {
        this.progressTasks.add(runnable);
    }

    public RenderGlobalBridge bridge$getRenderGlobal() {
        return (RenderGlobalBridge) this.levelRenderer;
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
        return (FrameBufferBridge) this.getMainRenderTarget();
    }

    public Proxy bridge$getProxy() {
        return this.getProxy();
    }

    public void bridge$func_147120_f() {
        this.updateDisplay(this.isFramerateLimited());
    }

    public NetHandlerPlayClientBridge bridge$getNetHandler() {
        return (NetHandlerPlayClientBridge) this.getConnection();
    }

    public RenderManagerBridge bridge$getRenderManager() {
        return (RenderManagerBridge) this.getEntityRenderDispatcher();
    }

    public boolean bridge$isIngame() {
        return this.screen == null;
    }

    public void bridge$goIngame() {
        this.currentCBScreen = null;
        this.setScreen(null);
    }

    @ModifyArg(method = "init", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/renderer/VirtualScreen;newWindow(Lcom/mojang/blaze3d/platform/DisplayData;Ljava/lang/String;Ljava/lang/String;)Lcom/mojang/blaze3d/platform/Window;"), index = 2)
    public String mod$newWindow(String title) {
        return Utility.getFullTitle(CheatBreakerMod.MINECRAFT_VERSION);
    }

    @Inject(method = "setScreen", at = @At("HEAD"), cancellable = true)
    public void impl$setScreen(Screen screen, CallbackInfo callbackInfo) {
        if (screen instanceof TitleScreen || (screen == null && this.level == null)) {
//            this.bridge$displayGuiScreen(new MainMenu());
            callbackInfo.cancel();
        }

        if (!(screen instanceof WrappedGuiScreen)) {
            this.currentCBScreen = null;
        }
    }

    @Inject(method = "init", at = @At("RETURN"))
    public void impl$init(CallbackInfo callbackInfo) {
        //CheatBreakerMod.CheatBreaker$preInitialize();
        CheatBreaker.getInstance().initialize();
        GLFW.glfwSetWindowTitle(Minecraft.getInstance().window.getWindow(),
                Utility.getFullTitle(CheatBreakerMod.MINECRAFT_VERSION));

        this.bridge$displayGuiScreen(new MainMenu());
    }

    private long previousFrameOccurringTime = 0L;

    @Inject(method = "runTick", at = @At(value = "INVOKE_STRING", target = "Lcom/mojang/blaze3d/platform/Window;setGlErrorSection(Ljava/lang/String;)V", args = "ldc=Post render"))
    public void impl$runTick(boolean renderWorldIn, CallbackInfo callbackInfo) {
        RenderUtil.updateFrameTime(this.previousFrameOccurringTime);
        this.previousFrameOccurringTime = System.nanoTime();
    }

    @Inject(method = "runTick", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/MouseHandler;turnPlayer()V", shift = At.Shift.BEFORE))
    public void impl$runTick$endStartSection(CallbackInfo callbackInfo) {
        OverlayGui.getInstance().pollNotifications();
    }

    private boolean initialisedCheatBreakerKeyboardCallback = false;
    @Inject(method = "handleKeybinds", at = @At("RETURN"))
    public void impl$runTick$getEventKeyState(CallbackInfo callbackInfo) {
        if (!this.initialisedCheatBreakerKeyboardCallback) {
            GLFW.glfwSetKeyCallback(this.window.getWindow(), (window, key, scancode, action, mods) -> {
                CheatBreaker.getInstance().getEventBus().callEvent(new KeyboardEvent(key));

                if (Screen.hasShiftDown() && key == GLFW.GLFW_KEY_TAB) {
                    this.bridge$displayGuiScreen(OverlayGui.getInstance());
                }
            });
            this.initialisedCheatBreakerKeyboardCallback = true;
        }
    }

    /**
     * @author iAmSpace
     * @reason big hz
     */
    @Overwrite
    private int getFramerateLimit() {
        return this.options.framerateLimit;
    }
}
