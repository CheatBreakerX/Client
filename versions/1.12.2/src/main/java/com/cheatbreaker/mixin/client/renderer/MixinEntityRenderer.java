package com.cheatbreaker.mixin.client.renderer;

import com.cheatbreaker.bridge.client.renderer.EntityRendererBridge;
import com.cheatbreaker.bridge.client.resources.IResourceManagerBridge;
import com.cheatbreaker.bridge.client.shader.ShaderGroupBridge;
import net.minecraft.client.renderer.EntityRenderer;
import net.minecraft.client.resources.IResourceManager;
import net.minecraft.client.shader.ShaderGroup;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(EntityRenderer.class)
public abstract class MixinEntityRenderer implements EntityRendererBridge {
    @Shadow public abstract void stopUseShader();
    @Shadow public abstract ShaderGroup getShaderGroup();
    @Shadow public abstract boolean isShaderActive();
    @Shadow @Final private IResourceManager resourceManager;
    @Shadow private ShaderGroup shaderGroup;

    public void bridge$stopUseShader() {
        this.stopUseShader();
    }

    public ShaderGroupBridge bridge$getShaderGroup() {
        return (ShaderGroupBridge) this.getShaderGroup();
    }

    public void bridge$setShaderGroup(ShaderGroupBridge shaderGroup) {
        this.shaderGroup = (ShaderGroup) shaderGroup;
    }

    public boolean bridge$isShaderActive() {
        return this.isShaderActive();
    }

    public IResourceManagerBridge bridge$getResourceManager() {
        return (IResourceManagerBridge) this.resourceManager;
    }
}
