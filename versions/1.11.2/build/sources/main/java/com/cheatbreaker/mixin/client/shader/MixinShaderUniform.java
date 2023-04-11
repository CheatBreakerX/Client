package com.cheatbreaker.mixin.client.shader;

import com.cheatbreaker.bridge.client.shader.ShaderUniformBridge;
import net.minecraft.client.shader.ShaderUniform;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(ShaderUniform.class)
public abstract class MixinShaderUniform implements ShaderUniformBridge {
    @Shadow public abstract void set(float p_148095_1_, float p_148095_2_, float p_148095_3_);

    public void bridge$func_148095_a(float p_148095_1_, float p_148095_2_, float p_148095_3_) {
        this.set(p_148095_1_, p_148095_2_, p_148095_3_);
    }
}
