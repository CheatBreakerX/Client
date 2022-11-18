package com.cheatbreaker.bridge.client.shader;

import java.util.List;

public interface ShaderGroupBride {
    void deleteShaderGroup();
    void createBindFramebuffers(int displayWidth, int displayHeight);
    List<ShaderBridge> bridge$getListShaders();
}
