package com.cheatbreaker.bridge.ref;

import com.cheatbreaker.bridge.item.ItemBridge;
import com.cheatbreaker.bridge.ref.extra.CBMovementInputHelper;

public interface IRefUtils {
    ItemBridge getMostPowerfulArmourHelmet();
    ItemBridge getMostPowerfulArmourChestplate();
    ItemBridge getMostPowerfulArmourLeggings();
    ItemBridge getMostPowerfulArmourBoots();
    ItemBridge getMostPowerfulDamageItem();
    ItemBridge getItemFromID(int itemId);
    CBMovementInputHelper getToggleSprintInputHelper();
    float bridge$MathHelper$sin(float toSine);
    ItemBridge getItemFromName(String name);
}
