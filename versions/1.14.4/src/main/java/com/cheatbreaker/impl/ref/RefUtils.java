package com.cheatbreaker.impl.ref;

import com.cheatbreaker.bridge.client.gui.GuiBridge;
import com.cheatbreaker.bridge.item.ItemBridge;
import com.cheatbreaker.bridge.ref.IRefUtils;
import com.cheatbreaker.bridge.ref.extra.CBMovementInputHelper;
import com.cheatbreaker.impl.extra.CBMovementInputHelperImpl;
import net.minecraft.client.Minecraft;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;

public class RefUtils implements IRefUtils {
    public ItemBridge getMostPowerfulArmourHelmet() {
        return (ItemBridge) Items.DIAMOND_HELMET;
    }

    public ItemBridge getMostPowerfulArmourChestplate() {
        return (ItemBridge) Items.DIAMOND_CHESTPLATE;
    }

    public ItemBridge getMostPowerfulArmourLeggings() {
        return (ItemBridge) Items.DIAMOND_LEGGINGS;
    }

    public ItemBridge getMostPowerfulArmourBoots() {
        return (ItemBridge) Items.DIAMOND_BOOTS;
    }

    public ItemBridge getMostPowerfulDamageItem() {
        return (ItemBridge) Items.DIAMOND_SWORD;
    }

    public ItemBridge getItemFromID(int itemId) {
        return (ItemBridge) Item.byId(itemId);
    }

    public CBMovementInputHelper getToggleSprintInputHelper() {
        return () -> CBMovementInputHelperImpl.toggleSprintString;
    }

    public float bridge$MathHelper$sin(float toSine) {
        return Mth.sin(toSine);
    }

    public ItemBridge getItemFromName(String name) {
        return (ItemBridge) Registry.ITEM.get(new ResourceLocation(name));
    }
}
