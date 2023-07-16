package com.cheatbreaker.mixin.net.minecraft.client.renderer;

import com.cheatbreaker.bridge.client.shader.ShaderBridge;
import com.cheatbreaker.bridge.client.shader.ShaderManagerBridge;
import net.minecraft.client.renderer.EffectInstance;
import net.minecraft.client.renderer.PostPass;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(PostPass.class)
public class MixinPostPass implements ShaderBridge {
    @Shadow @Final private EffectInstance effect;

    public ShaderManagerBridge bridge$getShaderManager() {
        return (ShaderManagerBridge) this.effect;
    }
}
