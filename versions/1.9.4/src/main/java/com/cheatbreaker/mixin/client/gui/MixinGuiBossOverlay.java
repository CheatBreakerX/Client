package com.cheatbreaker.mixin.client.gui;

import com.cheatbreaker.bridge.client.gui.GuiBossOverlayBridge;
import net.minecraft.client.gui.GuiBossOverlay;
import net.minecraft.world.BossInfoLerping;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Map;
import java.util.UUID;

@Mixin(GuiBossOverlay.class)
public class MixinGuiBossOverlay implements GuiBossOverlayBridge {
    @Shadow @Final public Map<UUID, BossInfoLerping> mapBossInfos;

    public Map<UUID, BossInfoLerping> bridge$mapBossInfos() {
        return this.mapBossInfos;
    }

    @Inject(method = "renderBossHealth", at = @At("HEAD"), cancellable = true)
    void impl$renderBossHealth(CallbackInfo callbackInfo) {
        callbackInfo.cancel();
    }
}
