package com.cheatbreaker.mixin.world.item;

import com.cheatbreaker.bridge.item.ItemBridge;
import net.minecraft.world.item.Item;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(Item.class)
public abstract class MixinItem implements ItemBridge {
    @Shadow public static int getId(Item itemIn) { return 0; }

    public int bridge$getId() {
        return getId((Item) ((ItemBridge) this));
    }
}
