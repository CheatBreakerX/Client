package com.cheatbreaker.mixin.com.replaymod.lib.de.johni0702.minecraft.gui;

import com.cheatbreaker.bridge.ref.Ref;
import com.cheatbreaker.main.utils.Utility;
import org.apache.logging.log4j.LogManager;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Pseudo;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Pseudo
@Mixin(targets = "com.replaymod.lib.de.johni0702.minecraft.gui.OffsetGuiRenderer", remap = false)
public abstract class MixinOffsetGuiRenderer {
    @Shadow public abstract void setDrawingArea(int x, int y, int width, int height);

    @Inject(method = "setDrawingArea(IIII)V", at = @At("HEAD"), remap = false, cancellable = true)
    public void impl$setDrawingArea(int x, int y, int width, int height, CallbackInfo callbackInfo) {
        // what if...
        callbackInfo.cancel();

        int scaleFactor = Ref.getInstanceCreator().createScaledResolution().bridge$getScaleFactor();
        int remainder = height % scaleFactor;

        if (remainder != 0) {
            this.setDrawingArea(x, y, width, height + (scaleFactor - remainder));
            //LogManager.getLogger("test").info(Utility.fmt("{}, %={}", height, remainder));
            callbackInfo.cancel();
        }
    }
}
