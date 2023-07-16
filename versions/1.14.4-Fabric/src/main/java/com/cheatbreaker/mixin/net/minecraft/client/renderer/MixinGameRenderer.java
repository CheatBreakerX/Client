package com.cheatbreaker.mixin.net.minecraft.client.renderer;

import com.cheatbreaker.bridge.client.renderer.EntityRendererBridge;
import com.cheatbreaker.bridge.client.resources.IResourceManagerBridge;
import com.cheatbreaker.bridge.client.shader.ShaderGroupBridge;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.client.renderer.PostChain;
import net.minecraft.server.packs.resources.ResourceManager;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(GameRenderer.class)
public abstract class MixinGameRenderer implements EntityRendererBridge {
    @Shadow public abstract void shutdownEffect();
    @Shadow public abstract PostChain currentEffect();
    @Shadow private PostChain postEffect;
    @Shadow private boolean effectActive;
    @Shadow @Final private ResourceManager resourceManager;

    public void bridge$stopUseShader() {
        this.shutdownEffect();
    }

    public ShaderGroupBridge bridge$getShaderGroup() {
        return (ShaderGroupBridge) this.currentEffect();
    }

    public void bridge$setShaderGroup(ShaderGroupBridge shaderGroup) {
        this.postEffect = (PostChain) shaderGroup;
    }

    public boolean bridge$isShaderActive() {
        return this.effectActive;
    }

    public IResourceManagerBridge bridge$getResourceManager() {
        return (IResourceManagerBridge) this.resourceManager;
    }
}
