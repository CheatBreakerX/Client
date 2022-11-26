package com.cheatbreaker.bridge.ref;

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
import com.cheatbreaker.bridge.scoreboard.ScoreObjectiveBridge;
import com.cheatbreaker.bridge.scoreboard.ScoreboardBridge;
import com.cheatbreaker.bridge.util.*;
import io.netty.buffer.ByteBuf;

import java.awt.image.BufferedImage;
import java.io.File;

public interface IInstanceCreator {
    ResourceLocationBridge createResourceLocationBridge(String domain, String path);
    default ResourceLocationBridge createResourceLocationBridge(String path) {
        return this.createResourceLocationBridge("minecraft", path);
    }

    ThreadDownloadImageDataBridge createThreadDownloadImageData(File p_i1049_1_, String p_i1049_2_, ResourceLocationBridge p_i1049_3_, IImageBufferBridge p_i1049_4_);
    ScaledResolutionBridge createScaledResolution();
    DynamicTextureBridge createDynamicTexture(BufferedImage img);
    DynamicTextureBridge createDynamicTexture(int width, int height);
    ISoundBridge createSoundFromPSR(ResourceLocationBridge location, float pitch);
    KeyBindingBridge createKeyBinding(String description, int keyCode, String category);
    Vec3Bridge createVec3(double x, double y, double z);
    ItemStackBridge createItemStack(ItemBridge item);
    RenderItemBridge createRenderItem();
    ShaderGroupBridge createShaderGroup(TextureManagerBridge p_i1050_1_, IResourceManagerBridge p_i1050_2_, FrameBufferBridge p_i1050_3_, ResourceLocationBridge p_i1050_4_);
    ChatComponentTextBridge createChatComponentText(String initialString);
    FrameBufferBridge createFrameBuffer(float width, float height, boolean b);
    SessionBridge createSession(String username, String playerId, String token, String type);
    AxisAlignedBBBridge createAxisAlignedBB(double minX, double minY, double minZ, double maxX, double maxY, double maxZ);
    GuiTextFieldBridge createTextField(FontRendererBridge fontRenderer, int x, int y, int width, int height);
    PacketBufferBridge createPacketBuffer(ByteBuf buffer);
    C17PacketCustomPayloadBridge createC17PacketCustomPayload(String channel, byte[] data);
    C17PacketCustomPayloadBridge createC17PacketCustomPayload(String channel, PacketBufferBridge data);
    HoverEventBridge createHoverEvent(String action, IChatComponentBridge component);
    PotionEffectBridge createPotionEffect(String idName, int duration, int multiplier);
    ScoreboardBridge createScoreboard();
    ScoreObjectiveBridge createScoreObjective(ScoreboardBridge scoreboard, String name, String type);
}
