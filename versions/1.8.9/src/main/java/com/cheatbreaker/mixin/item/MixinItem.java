package com.cheatbreaker.mixin.item;

import com.cheatbreaker.bridge.item.ItemBridge;
import net.minecraft.item.Item;
import net.minecraft.util.RegistryNamespaced;
import net.minecraft.util.ResourceLocation;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(Item.class)
public class MixinItem implements ItemBridge {
    @Shadow @Final public static RegistryNamespaced<ResourceLocation, Item> itemRegistry;

    public int bridge$getId() {
        return itemRegistry.getIDForObject((Item) ((Object) this));
    }
}
