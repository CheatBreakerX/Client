package com.cheatbreaker.mixin.com.mojang.blaze3d.shaders;

import com.cheatbreaker.bridge.client.shader.ShaderUniformBridge;
import com.mojang.blaze3d.shaders.Uniform;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(Uniform.class)
public abstract class MixinUniform implements ShaderUniformBridge {
    @Shadow public abstract void set(float f, float f1, float f2);

    public void bridge$func_148095_a(float p_148095_1_, float p_148095_2_, float p_148095_3_) {
        this.set(p_148095_1_, p_148095_2_, p_148095_3_);
    }
}
