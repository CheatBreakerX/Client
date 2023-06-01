package com.cheatbreaker.mixin.client.shader;

import com.cheatbreaker.bridge.client.shader.ShaderBridge;
import com.cheatbreaker.bridge.client.shader.ShaderManagerBridge;
import net.minecraft.client.shader.Shader;
import net.minecraft.client.shader.ShaderManager;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(Shader.class)
public abstract class MixinShader implements ShaderBridge {
    @Shadow public abstract ShaderManager getShaderManager();

    public ShaderManagerBridge bridge$getShaderManager() {
        return (ShaderManagerBridge) this.getShaderManager();
    }
}
