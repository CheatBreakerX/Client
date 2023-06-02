package com.cheatbreaker.mixin.client.gui;

import com.cheatbreaker.bridge.client.gui.GuiBridge;
import net.minecraft.client.gui.Gui;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(Gui.class)
public class MixinGui implements GuiBridge {
    @Shadow protected float zLevel;

    public float bridge$getZLevel() {
        return zLevel;
    }
}
