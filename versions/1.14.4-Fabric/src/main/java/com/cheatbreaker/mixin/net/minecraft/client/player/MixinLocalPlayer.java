package com.cheatbreaker.mixin.net.minecraft.client.player;

import com.cheatbreaker.bridge.client.entity.EntityPlayerSPBridge;
import com.cheatbreaker.bridge.entity.player.InventoryPlayerBridge;
import com.cheatbreaker.bridge.item.ItemStackBridge;
import com.cheatbreaker.bridge.potion.PotionEffectBridge;
import com.cheatbreaker.bridge.util.AxisAlignedBBBridge;
import com.cheatbreaker.bridge.util.IChatComponentBridge;
import com.cheatbreaker.bridge.util.ResourceLocationBridge;
import com.mojang.authlib.GameProfile;
import net.minecraft.client.multiplayer.MultiPlayerLevel;
import net.minecraft.client.player.AbstractClientPlayer;
import net.minecraft.client.player.LocalPlayer;
import org.spongepowered.asm.mixin.Mixin;

import java.util.Collection;
import java.util.UUID;

@Mixin(LocalPlayer.class)
public class MixinLocalPlayer extends AbstractClientPlayer implements EntityPlayerSPBridge {
    public MixinLocalPlayer(MultiPlayerLevel multiPlayerLevel, GameProfile gameProfile) {
        super(multiPlayerLevel, gameProfile);
    }

    public void bridge$setLocationCape(ResourceLocationBridge location) {

    }

    public String bridge$getDisplayName() {
        return null;
    }

    public double bridge$getPosX() {
        return this.x;
    }

    public double bridge$getPosY() {
        return this.y;
    }

    public double bridge$getPosZ() {
        return this.z;
    }

    public UUID bridge$getUniqueID() {
        return null;
    }

    public double bridge$getLastTickPosX() {
        return 0;
    }

    public double bridge$getLastTickPosY() {
        return 0;
    }

    public double bridge$getLastTickPosZ() {
        return 0;
    }

    public float bridge$getRotationPitch() {
        return 0;
    }

    public float bridge$getRotationYaw() {
        return 0;
    }

    public AxisAlignedBBBridge bridge$getBoundingBox() {
        return null;
    }

    public float bridge$getHeight() {
        return 0;
    }

    public IChatComponentBridge bridge$func_145748_c_() {
        return null;
    }

    public Collection<PotionEffectBridge> bridge$getActivePotionEffects() {
        return null;
    }

    public String bridge$getCommandSenderName() {
        return null;
    }

    public GameProfile bridge$getGameProfile() {
        return null;
    }

    public InventoryPlayerBridge bridge$getInventory() {
        return null;
    }

    public ItemStackBridge bridge$getCurrentEquippedItem() {
        return null;
    }
}
