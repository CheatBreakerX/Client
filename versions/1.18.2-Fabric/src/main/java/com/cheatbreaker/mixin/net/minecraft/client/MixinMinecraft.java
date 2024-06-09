package com.cheatbreaker.mixin.net.minecraft.client;

import net.minecraft.client.Minecraft;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Minecraft.class)
public class MixinMinecraft {
    @Inject(method = "createTitle", at = @At("HEAD"), cancellable = true)
    private void createTitle(CallbackInfoReturnable<String> callback) {
        callback.setReturnValue("Minecraft 1.18.2, Fabric - This version is not supported by CheatBreakerX yet.");
    }
}
