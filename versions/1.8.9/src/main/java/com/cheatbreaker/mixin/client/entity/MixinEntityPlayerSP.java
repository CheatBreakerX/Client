package com.cheatbreaker.mixin.client.entity;

import com.cheatbreaker.bridge.client.entity.EntityPlayerSPBridge;
import com.cheatbreaker.bridge.client.network.NetworkPlayerInfoBridge;
import com.cheatbreaker.bridge.entity.player.InventoryPlayerBridge;
import com.cheatbreaker.bridge.item.ItemStackBridge;
import com.cheatbreaker.bridge.potion.PotionEffectBridge;
import com.cheatbreaker.bridge.util.AxisAlignedBBBridge;
import com.cheatbreaker.bridge.util.IChatComponentBridge;
import com.cheatbreaker.bridge.util.ResourceLocationBridge;
import com.cheatbreaker.impl.extra.CBMovementInputHelperImpl;
import com.cheatbreaker.util.Utils;
import com.mojang.authlib.GameProfile;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.network.NetHandlerPlayClient;
import net.minecraft.potion.PotionEffect;
import net.minecraft.stats.StatFileWriter;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

@Mixin(EntityPlayerSP.class)
public class MixinEntityPlayerSP extends AbstractClientPlayer implements EntityPlayerSPBridge {
    @Shadow protected Minecraft mc;

    public MixinEntityPlayerSP(World worldIn, GameProfile playerProfile) {
        super(worldIn, playerProfile);
    }

    private CBMovementInputHelperImpl minecraftMovementInputHelper;
    @Inject(method = "<init>", at = @At("RETURN"))
    public void init(Minecraft mcIn, World worldIn, NetHandlerPlayClient netHandler, StatFileWriter statFile, CallbackInfo ca) {
        this.minecraftMovementInputHelper = new CBMovementInputHelperImpl(this.mc.gameSettings);
    }

    public void bridge$setLocationCape(ResourceLocationBridge location) {
        ((NetworkPlayerInfoBridge) this.getPlayerInfo()).bridge$setLocationCape(location);
    }

    public String bridge$getDisplayName() {
        return this.getDisplayNameString();
    }

    public double bridge$getPosX() {
        return this.posX;
    }

    public double bridge$getPosY() {
        return this.posY;
    }

    public double bridge$getPosZ() {
        return this.posZ;
    }

    public UUID bridge$getUniqueID() {
        return this.getUniqueID();
    }

    public double bridge$getLastTickPosX() {
        return this.lastTickPosX;
    }

    public double bridge$getLastTickPosY() {
        return this.lastTickPosY;
    }

    public double bridge$getLastTickPosZ() {
        return this.lastTickPosZ;
    }

    public float bridge$getRotationPitch() {
        return this.rotationPitch;
    }

    public float bridge$getRotationYaw() {
        return this.rotationYaw;
    }

    public AxisAlignedBBBridge bridge$getBoundingBox() {
        return (AxisAlignedBBBridge) this.getEntityBoundingBox();
    }

    public float bridge$getHeight() {
        return this.height;
    }

    public IChatComponentBridge bridge$func_145748_c_() {
        return (IChatComponentBridge) this.getDisplayName();
    }

    public Collection<PotionEffectBridge> bridge$getActivePotionEffects() {
        List<PotionEffectBridge> list = new ArrayList<>();

        for (PotionEffect effect : this.getActivePotionEffects()) {
            list.add((PotionEffectBridge) effect);
        }

        return list;
    }

    public String bridge$getCommandSenderName() {
        return this.getCommandSenderEntity().getName();
    }

    public GameProfile bridge$getGameProfile() {
        return this.getGameProfile();
    }

    public InventoryPlayerBridge bridge$getInventory() {
        return (InventoryPlayerBridge) this.inventory;
    }

    public ItemStackBridge bridge$getCurrentEquippedItem() {
        return Utils.itemStackToItemStackBridge(this.getCurrentEquippedItem());
    }
}
