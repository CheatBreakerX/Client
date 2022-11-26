package com.cheatbreaker.mixin.entity.player;

import com.cheatbreaker.bridge.entity.player.EntityPlayerBridge;
import com.cheatbreaker.bridge.entity.player.InventoryPlayerBridge;
import com.cheatbreaker.bridge.item.ItemStackBridge;
import com.cheatbreaker.mixin.entity.MixinEntityLivingBase;
import com.cheatbreaker.util.Utils;
import com.mojang.authlib.GameProfile;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(EntityPlayer.class)
public abstract class MixinEntityPlayer extends MixinEntityLivingBase implements EntityPlayerBridge {
    @Shadow public abstract GameProfile getGameProfile();
    @Shadow public abstract ItemStack getCurrentEquippedItem();
    @Shadow public InventoryPlayer inventory;

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
