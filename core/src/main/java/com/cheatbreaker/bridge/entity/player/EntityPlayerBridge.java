package com.cheatbreaker.bridge.entity.player;

import com.cheatbreaker.bridge.entity.EntityLivingBaseBridge;
import com.cheatbreaker.bridge.item.ItemStackBridge;
import com.cheatbreaker.client.ui.module.CBGuiAnchor;
import com.mojang.authlib.GameProfile;

public interface EntityPlayerBridge extends EntityLivingBaseBridge {
    String bridge$getCommandSenderName();
    GameProfile bridge$getGameProfile();
    InventoryPlayerBridge bridge$getInventory();
    ItemStackBridge bridge$getCurrentEquippedItem();
}
