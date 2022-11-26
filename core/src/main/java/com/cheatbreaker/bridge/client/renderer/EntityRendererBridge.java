package com.cheatbreaker.bridge.client.renderer;

import com.cheatbreaker.bridge.client.resources.IResourceManagerBridge;
import com.cheatbreaker.bridge.client.shader.ShaderGroupBridge;

public interface EntityRendererBridge {
    void bridge$stopUseShader();
    ShaderGroupBridge bridge$getShaderGroup();
    void bridge$setShaderGroup(ShaderGroupBridge shaderGroup);
    boolean bridge$isShaderActive();
    IResourceManagerBridge bridge$getResourceManager();
}
