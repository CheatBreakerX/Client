package com.cheatbreaker.mixin.inventory;

import com.cheatbreaker.bridge.inventory.IInventoryBridge;
import net.minecraft.inventory.IInventory;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(IInventory.class)
public interface MixinIInventory extends IInventoryBridge {
}
