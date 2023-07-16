package com.cheatbreaker.mixin.net.minecraft.client.renderer;

import com.cheatbreaker.bridge.client.shader.ShaderManagerBridge;
import com.cheatbreaker.bridge.client.shader.ShaderUniformBridge;
import com.mojang.blaze3d.shaders.Uniform;
import net.minecraft.client.renderer.EffectInstance;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

import javax.annotation.Nullable;

@Mixin(EffectInstance.class)
public abstract class MixinEffectInstance implements ShaderManagerBridge {
    @Shadow @Nullable public abstract Uniform getUniform(String string);

    public ShaderUniformBridge bridge$func_147991_a(String p_147991_1_) {
        return (ShaderUniformBridge) this.getUniform(p_147991_1_);
    }
}
