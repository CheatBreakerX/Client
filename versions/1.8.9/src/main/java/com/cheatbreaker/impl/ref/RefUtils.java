package com.cheatbreaker.impl.ref;

import com.cheatbreaker.bridge.client.gui.GuiBridge;
import com.cheatbreaker.bridge.item.ItemBridge;
import com.cheatbreaker.bridge.ref.IRefUtils;
import com.cheatbreaker.bridge.ref.extra.CBMovementInputHelper;
import com.cheatbreaker.impl.extra.CBMovementInputHelperImpl;
import net.minecraft.client.Minecraft;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.util.MathHelper;

public class RefUtils implements IRefUtils {
    public ItemBridge getMostPowerfulArmourHelmet() {
        return (ItemBridge) Items.diamond_helmet;
    }

    public ItemBridge getMostPowerfulArmourChestplate() {
        return (ItemBridge) Items.diamond_chestplate;
    }

    public ItemBridge getMostPowerfulArmourLeggings() {
        return (ItemBridge) Items.diamond_leggings;
    }

    public ItemBridge getMostPowerfulArmourBoots() {
        return (ItemBridge) Items.diamond_boots;
    }

    public ItemBridge getMostPowerfulDamageItem() {
        return (ItemBridge) Items.diamond_sword;
    }

    public ItemBridge getItemFromID(int itemId) {
        return (ItemBridge) Item.getItemById(itemId);
    }

    public CBMovementInputHelper getToggleSprintInputHelper() {
        return () -> CBMovementInputHelperImpl.toggleSprintString;
    }

    public float bridge$MathHelper$sin(float toSine) {
        return MathHelper.sin(toSine);
    }

    public float bridge$Gui$getZLevel() {
        return ((GuiBridge) Minecraft.getMinecraft().currentScreen).bridge$getZLevel();
    }

    public ItemBridge getItemFromName(String name) {
        return (ItemBridge) Item.getByNameOrId(name);
    }
}
