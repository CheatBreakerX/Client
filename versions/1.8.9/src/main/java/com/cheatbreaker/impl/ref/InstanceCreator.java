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
import com.cheatbreaker.bridge.client.shader.ShaderGroupBridge;
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
import com.cheatbreaker.util.Utils;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import net.minecraft.client.Minecraft;
import net.minecraft.client.audio.PositionedSoundRecord;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiTextField;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.IImageBuffer;
import net.minecraft.client.renderer.ThreadDownloadImageData;
import net.minecraft.client.renderer.texture.DynamicTexture;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.client.resources.IResourceManager;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.client.shader.Framebuffer;
import net.minecraft.client.shader.ShaderGroup;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.play.client.C17PacketCustomPayload;
import net.minecraft.potion.PotionEffect;
import net.minecraft.scoreboard.IScoreObjectiveCriteria;
import net.minecraft.scoreboard.ScoreObjective;
import net.minecraft.scoreboard.Scoreboard;
import net.minecraft.util.*;

import java.awt.image.BufferedImage;
import java.io.File;

public class InstanceCreator implements IInstanceCreator {
    public ResourceLocationBridge createResourceLocation(String domain, String path) {
        return (ResourceLocationBridge) new ResourceLocation(domain, path);
    }

    public ThreadDownloadImageDataBridge createThreadDownloadImageData(File p_i1049_1_, String p_i1049_2_, ResourceLocationBridge p_i1049_3_, IImageBufferBridge p_i1049_4_) {
        return (ThreadDownloadImageDataBridge) new ThreadDownloadImageData(p_i1049_1_, p_i1049_2_, (ResourceLocation) p_i1049_3_, (IImageBuffer) p_i1049_4_);
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

    public ISoundBridge createSoundFromPSR(ResourceLocationBridge location, float pitch) {
        return (ISoundBridge) PositionedSoundRecord.create((ResourceLocation) location, pitch);
    }

    public KeyBindingBridge createKeyBinding(String description, int keyCode, String category) {
        return (KeyBindingBridge) new KeyBinding(description, keyCode, category);
    }

    public Vec3Bridge createVec3(double x, double y, double z) {
        return (Vec3Bridge) new Vec3(x, y, z);
    }

    public ItemStackBridge createItemStack(ItemBridge item) {
        ItemStack stack = new ItemStack((Item) item);
        return Utils.itemStackToItemStackBridge(stack);
    }

    public RenderItemBridge createRenderItem() {
        return (RenderItemBridge) Minecraft.getMinecraft().getRenderItem();
    }

    public ShaderGroupBridge createShaderGroup(TextureManagerBridge p_i1050_1_, IResourceManagerBridge p_i1050_2_, FrameBufferBridge p_i1050_3_, ResourceLocationBridge p_i1050_4_) {
        try {
            return (ShaderGroupBridge) new ShaderGroup((TextureManager) p_i1050_1_, (IResourceManager) p_i1050_2_, (Framebuffer) p_i1050_3_, (ResourceLocation) p_i1050_4_);
        } catch (Exception e) {
            return null;
        }
    }

    public ChatComponentTextBridge createChatComponentText(String initialString) {
        return (ChatComponentTextBridge) new ChatComponentText(initialString);
    }

    public FrameBufferBridge createFrameBuffer(float width, float height, boolean b) {
        return (FrameBufferBridge) new Framebuffer((int) width, (int) height, b);
    }

    public SessionBridge createSession(String username, String playerId, String token, String type) {
        return (SessionBridge) new Session(username, playerId, token, type);
    }

    public AxisAlignedBBBridge createAxisAlignedBB(double minX, double minY, double minZ, double maxX, double maxY, double maxZ) {
        return (AxisAlignedBBBridge) AxisAlignedBB.fromBounds(minX, minY, minZ, maxX, maxY, maxZ);
    }

    public GuiTextFieldBridge createTextField(FontRendererBridge fontRenderer, int x, int y, int width, int height) {
        return (GuiTextFieldBridge) new GuiTextField(0x530, (FontRenderer) fontRenderer, x, y, width, height);
    }

    public PacketBufferBridge createPacketBuffer(ByteBuf buffer) {
        return (PacketBufferBridge) new PacketBuffer(buffer);
    }

    public C17PacketCustomPayloadBridge createC17PacketCustomPayload(String channel, byte[] data) {
        return (C17PacketCustomPayloadBridge) new C17PacketCustomPayload(channel, new PacketBuffer(Unpooled.wrappedBuffer(data)));
    }

    public C17PacketCustomPayloadBridge createC17PacketCustomPayload(String channel, PacketBufferBridge data) {
        return (C17PacketCustomPayloadBridge) new C17PacketCustomPayload(channel, (PacketBuffer) data);
    }

    public HoverEventBridge createHoverEvent(String action, IChatComponentBridge component) {
        return null;
    }

    public PotionEffectBridge createPotionEffect(String idName, int duration, int multiplier) {
//        return (PotionEffectBridge) new PotionEffect(idName, duration, multiplier);
        return null;
    }

    public ScoreboardBridge createScoreboard() {
        return (ScoreboardBridge) new Scoreboard();
    }

    public ScoreObjectiveBridge createScoreObjective(ScoreboardBridge scoreboard, String name, String type) {
        IScoreObjectiveCriteria criteria = type.equalsIgnoreCase("dummy") ? IScoreObjectiveCriteria.DUMMY : IScoreObjectiveCriteria.TRIGGER;
        return (ScoreObjectiveBridge) new ScoreObjective((Scoreboard) scoreboard, name, criteria);
    }
}
