package com.cheatbreaker.impl.ref;

import com.cheatbreaker.bridge.client.audio.ISoundBridge;
import com.cheatbreaker.bridge.client.gui.FontRendererBridge;
import com.cheatbreaker.bridge.client.gui.GuiTextFieldBridge;
import com.cheatbreaker.bridge.client.gui.ScaledResolutionBridge;
import com.cheatbreaker.bridge.client.renderer.IImageBufferBridge;
import com.cheatbreaker.bridge.client.renderer.ThreadDownloadImageDataBridge;
import com.cheatbreaker.bridge.client.renderer.entity.RenderItemBridge;
import com.cheatbreaker.bridge.client.renderer.texture.DynamicTextureBridge;
import com.cheatbreaker.bridge.client.renderer.texture.TextureManagerBridge;
import com.cheatbreaker.bridge.client.resources.IResourceManagerBridge;
import com.cheatbreaker.bridge.client.settings.KeyBindingBridge;
import com.cheatbreaker.bridge.client.shader.FrameBufferBridge;
import com.cheatbreaker.bridge.client.shader.ShaderGroupBride;
import com.cheatbreaker.bridge.event.HoverEventBridge;
import com.cheatbreaker.bridge.item.ItemBridge;
import com.cheatbreaker.bridge.item.ItemStackBridge;
import com.cheatbreaker.bridge.network.PacketBufferBridge;
import com.cheatbreaker.bridge.network.play.client.C17PacketCustomPayloadBridge;
import com.cheatbreaker.bridge.potion.PotionEffectBridge;
import com.cheatbreaker.bridge.ref.IInstanceCreator;
import com.cheatbreaker.bridge.scoreboard.ScoreObjectiveBridge;
import com.cheatbreaker.bridge.scoreboard.ScoreboardBridge;
import com.cheatbreaker.bridge.util.*;
import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.texture.DynamicTexture;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Vec3;

import java.awt.image.BufferedImage;
import java.io.File;

public class InstanceCreator implements IInstanceCreator {
    public ResourceLocationBridge createResourceLocationBridge(String domain, String path) {
        return (ResourceLocationBridge) new ResourceLocation(domain, path);
    }

    public ThreadDownloadImageDataBridge createThreadDownloadImageData(File p_i1049_1_, String p_i1049_2_, ResourceLocationBridge p_i1049_3_, IImageBufferBridge p_i1049_4_) {
        return null;
    }

    public ScaledResolutionBridge createScaledResolution() {
        return (ScaledResolutionBridge) new ScaledResolution(Minecraft.getMinecraft());
    }

    public DynamicTextureBridge createDynamicTexture(BufferedImage img) {
        return (DynamicTextureBridge) new DynamicTexture(img);
    }

    public DynamicTextureBridge createDynamicTexture(int width, int height) {
        return (DynamicTextureBridge) new DynamicTexture(width, height);
    }

    public ISoundBridge createSoundFromPSR(ResourceLocationBridge location, float volume) {
        return null;
    }

    public KeyBindingBridge createKeyBinding(String description, int keyCode, String category) {
        return (KeyBindingBridge) new KeyBinding(description, keyCode, category);
    }

    public Vec3Bridge createVec3(double x, double y, double z) {
        return (Vec3Bridge) new Vec3(x, y, z);
    }

    public ItemStackBridge createItemStack(ItemBridge item) {
        return null;
    }

    public RenderItemBridge createRenderItem() {
        return null;
    }

    public ShaderGroupBride createShaderGroup(TextureManagerBridge p_i1050_1_, IResourceManagerBridge p_i1050_2_, FrameBufferBridge p_i1050_3_, ResourceLocationBridge p_i1050_4_) {
        return null;
    }

    public ChatComponentTextBridge createChatComponentText(String initialString) {
        return null;
    }

    public FrameBufferBridge createFrameBuffer(float width, float height, boolean b) {
        return null;
    }

    public SessionBridge createSession(String username, String playerId, String token, String type) {
        return null;
    }

    public AxisAlignedBBBridge createAxisAlignedBB(double minX, double minY, double minZ, double maxX, double maxY, double maxZ) {
        return null;
    }

    public GuiTextFieldBridge createTextField(FontRendererBridge fontRenderer, int i, int i1, int i2, int i3) {
        return null;
    }

    public PacketBufferBridge createPacketBuffer(ByteBuf buffer) {
        return null;
    }

    public C17PacketCustomPayloadBridge createC17PacketCustomPayload(String channel, byte[] data) {
        return null;
    }

    public C17PacketCustomPayloadBridge createC17PacketCustomPayload(String channel, PacketBufferBridge data) {
        return null;
    }

    public HoverEventBridge createHoverEvent(String action, IChatComponentBridge component) {
        return null;
    }

    public PotionEffectBridge createPotionEffect(String idName, int duration, int multiplier) {
        return null;
    }

    public ScoreboardBridge createScoreboard() {
        return null;
    }

    public ScoreObjectiveBridge createScoreObjective(ScoreboardBridge scoreboard, String name, String type) {
        return null;
    }
}
