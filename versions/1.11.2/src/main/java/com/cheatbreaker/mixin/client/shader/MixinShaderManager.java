package com.cheatbreaker.mixin.client.shader;

import com.cheatbreaker.bridge.client.shader.ShaderManagerBridge;
import com.cheatbreaker.bridge.client.shader.ShaderUniformBridge;
import net.minecraft.client.shader.ShaderManager;
import net.minecraft.client.shader.ShaderUniform;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(ShaderManager.class)
public abstract class MixinShaderManager implements ShaderManagerBridge {
    @Shadow public abstract ShaderUniform getShaderUniform(String p_147991_1_);

    public ShaderUniformBridge bridge$func_147991_a(String p_147991_1_) {
        return (ShaderUniformBridge) this.getShaderUniform(p_147991_1_);
    }
}
