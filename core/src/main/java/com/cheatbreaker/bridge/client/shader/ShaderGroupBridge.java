package com.cheatbreaker.bridge.client.shader;

import java.util.List;

public interface ShaderGroupBridge {
    void bridge$deleteShaderGroup();
    void bridge$createBindFramebuffers(int displayWidth, int displayHeight);
    List<ShaderBridge> bridge$getListShaders();
}
