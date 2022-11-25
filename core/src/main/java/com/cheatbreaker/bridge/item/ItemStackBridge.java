package com.cheatbreaker.bridge.item;

public interface ItemStackBridge {
    int bridge$getItemDamageForDisplay();
    int bridge$getMaxDamage();
    boolean bridge$isItemDamaged();
    int bridge$getMaxStackSize();
    int bridge$getItemDamage();
    ItemBridge bridge$getItem();
    int bridge$getStackSize();
    String bridge$getDisplayName();
}
