package com.cheatbreaker.bridge.entity.player;

import com.cheatbreaker.bridge.inventory.IInventoryBridge;
import com.cheatbreaker.bridge.item.ItemStackBridge;

public interface InventoryPlayerBridge extends IInventoryBridge {
    ItemStackBridge[] bridge$getMainInventory();
    ItemStackBridge[] bridge$getArmorInventory();
}
