package com.cheatbreaker.mixin.world.level.block;

import com.cheatbreaker.bridge.block.BlockBridge;
import com.cheatbreaker.bridge.item.ItemBridge;
import net.minecraft.world.level.block.Block;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(Block.class)
public class MixinBlock implements BlockBridge {
    public ItemBridge bridge$asItem() {
        return (ItemBridge) ((Block) ((Object) this)).asItem();
    }
}
