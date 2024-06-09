package com.cheatbreaker.mixin.net.minecraft.client;

import com.cheatbreaker.bridge.client.MouseHandlerBridge;
import net.minecraft.client.MouseHandler;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(MouseHandler.class)
public class MixinMouseHandler implements MouseHandlerBridge {
    @Shadow private boolean isMiddlePressed;

    public boolean bridge$isMiddlePressed() {
        return this.isMiddlePressed;
    }
}
