package com.cheatbreaker.mixin.block;

import com.cheatbreaker.bridge.block.BlockBridge;
import com.cheatbreaker.bridge.item.ItemBridge;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(Block.class)
public class MixinBlock implements BlockBridge {
    public ItemBridge bridge$asItem() {
        return (ItemBridge) Item.getItemFromBlock((Block) ((Object) this));
    }
}
