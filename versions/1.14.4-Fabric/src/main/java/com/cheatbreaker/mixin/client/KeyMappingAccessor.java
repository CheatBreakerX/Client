package com.cheatbreaker.mixin.client;

import java.util.Map;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

import net.minecraft.client.KeyMapping;
import com.mojang.blaze3d.platform.InputConstants;

@Mixin(KeyMapping.class)
public interface KeyMappingAccessor {
    @Accessor("CATEGORY_SORT_ORDER")
    static Map<String, Integer> accessor$categorySortOrder() {
        throw new AssertionError();
    }

    @Accessor("key")
    InputConstants.Key accessor$key();
}
