package com.cheatbreaker.impl.ref;

import com.cheatbreaker.bridge.client.audio.ISoundBridge;
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
import com.cheatbreaker.util.ScaledResolution;
import com.cheatbreaker.util.Utils;
import com.mojang.blaze3d.pipeline.RenderTarget;
import com.mojang.blaze3d.platform.NativeImage;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import net.minecraft.client.KeyMapping;
import net.minecraft.client.Minecraft;
import net.minecraft.client.User;
import net.minecraft.client.gui.components.EditBox;
import net.minecraft.client.renderer.HttpTextureProcessor;
import net.minecraft.client.renderer.PostChain;
import net.minecraft.client.renderer.texture.DynamicTexture;
import net.minecraft.client.renderer.texture.HttpTexture;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.client.resources.language.I18n;
import net.minecraft.client.resources.sounds.SimpleSoundInstance;
import net.minecraft.core.Registry;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.HoverEvent;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.network.protocol.game.ClientboundCustomPayloadPacket;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.scores.Objective;
import net.minecraft.world.scores.Scoreboard;
import net.minecraft.world.scores.criteria.ObjectiveCriteria;

import java.awt.image.BufferedImage;
import java.io.File;

public class InstanceCreator implements IInstanceCreator {
    public ResourceLocationBridge createResourceLocation(String domain, String path) {
        return (ResourceLocationBridge) new ResourceLocation(domain, path);
    }

    public ThreadDownloadImageDataBridge createThreadDownloadImageData(File p_i1049_1_, String p_i1049_2_, ResourceLocationBridge p_i1049_3_, IImageBufferBridge p_i1049_4_) {
        return (ThreadDownloadImageDataBridge) new HttpTexture(p_i1049_1_, p_i1049_2_, (ResourceLocation) p_i1049_3_, (HttpTextureProcessor) p_i1049_4_);
    }

    public ScaledResolutionBridge createScaledResolution() {
        return new ScaledResolution();
    }

    public DynamicTextureBridge createDynamicTexture(BufferedImage img) {
        return (DynamicTextureBridge) new DynamicTexture(Utils.bufferedImageToNativeImage(img));
    }

    public DynamicTextureBridge createDynamicTexture(int width, int height) {
        return (DynamicTextureBridge) new DynamicTexture(width, height, true);
    }

    public ISoundBridge createSoundFromPSR(ResourceLocationBridge location, float pitch) {
        ResourceLocation mcLocation = (ResourceLocation) location;
        if (mcLocation.getPath().equals("gui.button.press")) {
            mcLocation = new ResourceLocation("ui.button.click");
        }

        return (ISoundBridge) SimpleSoundInstance.forUI(new SoundEvent(mcLocation), pitch);
    }

    public KeyBindingBridge createKeyBinding(String description, int keyCode, String category) {
        return (KeyBindingBridge) new KeyMapping(description, keyCode, category);
    }

    public Vec3Bridge createVec3(double x, double y, double z) {
        return (Vec3Bridge) new Vec3(x, y, z);
    }

    public ItemStackBridge createItemStack(ItemBridge item) {
        ItemStack stack = new ItemStack((Item) item);
        return Utils.itemStackToItemStackBridge(stack);
    }

    public RenderItemBridge createRenderItem() {
        return (RenderItemBridge) Minecraft.getInstance().getItemRenderer();
    }

    public ShaderGroupBridge createShaderGroup(TextureManagerBridge p_i1050_1_, IResourceManagerBridge p_i1050_2_, FrameBufferBridge p_i1050_3_, ResourceLocationBridge p_i1050_4_) {
        try {
            return (ShaderGroupBridge) new PostChain((TextureManager) p_i1050_1_, (ResourceManager) p_i1050_2_, (RenderTarget) p_i1050_3_, (ResourceLocation) p_i1050_4_);
        } catch (Exception e) {
            return null;
        }
    }

    public ChatComponentTextBridge createChatComponentText(String initialString) {
        return (ChatComponentTextBridge) new TextComponent(initialString);
    }

    public FrameBufferBridge createFrameBuffer(float width, float height, boolean b) {
        return (FrameBufferBridge) new RenderTarget((int) width, (int) height, b, Minecraft.ON_OSX);
    }

    public SessionBridge createSession(String username, String playerId, String token, String type) {
        return (SessionBridge) new User(username, playerId, token, type);
    }

    public AxisAlignedBBBridge createAxisAlignedBB(double minX, double minY, double minZ, double maxX, double maxY, double maxZ) {
        return (AxisAlignedBBBridge) new AABB(minX, minY, minZ, maxX, maxY, maxZ);
    }

    public GuiTextFieldBridge createTextField(int x, int y, int width, int height) {
        return (GuiTextFieldBridge) new EditBox(Minecraft.getInstance().font, x, y, width, height, "cbTextField");
    }

    public PacketBufferBridge createPacketBuffer(ByteBuf buffer) {
        return (PacketBufferBridge) new FriendlyByteBuf(buffer);
    }

    public C17PacketCustomPayloadBridge createC17PacketCustomPayload(String channel, byte[] data) {
        return (C17PacketCustomPayloadBridge) new ClientboundCustomPayloadPacket(new ResourceLocation(channel),
                new FriendlyByteBuf(Unpooled.wrappedBuffer(data)));
    }

    public C17PacketCustomPayloadBridge createC17PacketCustomPayload(String channel, PacketBufferBridge data) {
        return (C17PacketCustomPayloadBridge) new ClientboundCustomPayloadPacket(new ResourceLocation(channel),
                (FriendlyByteBuf) data);
    }

    public HoverEventBridge createHoverEvent(String action, IChatComponentBridge component) {
        // will change if needed to - right now there is no other use than "show_text"
        HoverEvent.Action nativeAction = HoverEvent.Action.SHOW_TEXT;
        return (HoverEventBridge) new HoverEvent(nativeAction, (Component) component);
    }

    public PotionEffectBridge createPotionEffect(String idName, int duration, int multiplier) {
        return (PotionEffectBridge) new MobEffectInstance(MobEffect.byId(Registry.POTION.getId(Potion.byName(idName.toLowerCase()))), duration, multiplier);
    }

    public ScoreboardBridge createScoreboard() {
        return (ScoreboardBridge) new Scoreboard();
    }

    public ScoreObjectiveBridge createScoreObjective(ScoreboardBridge scoreboard, String name, String type) {
        ObjectiveCriteria criteria = type.equalsIgnoreCase("dummy") ? ObjectiveCriteria.DUMMY : ObjectiveCriteria.TRIGGER;
        return (ScoreObjectiveBridge) new Objective((Scoreboard) scoreboard, name, criteria, new TextComponent(name), criteria.getDefaultRenderType());
    }

    public ScoreObjectiveBridge createScoreObjective(String name, String type) {
        ObjectiveCriteria criteria = type.equalsIgnoreCase("dummy") ? ObjectiveCriteria.DUMMY : ObjectiveCriteria.TRIGGER;
        return (ScoreObjectiveBridge) new Objective(new Scoreboard(), name, criteria, new TextComponent(name), criteria.getDefaultRenderType());
    }
}
