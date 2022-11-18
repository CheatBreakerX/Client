package com.cheatbreaker.bridge.client.renderer;

import com.cheatbreaker.bridge.client.resources.IResourceManagerBridge;
import com.cheatbreaker.bridge.client.shader.ShaderGroupBride;

public interface EntityRendererBridge {
    void bridge$stopUseShader();
    ShaderGroupBride bridge$getShaderGroup();
    void bridge$setShaderGroup(ShaderGroupBride shaderGroup);
    boolean bridge$isShaderActive();
    IResourceManagerBridge bridge$getResourceManager();
}
