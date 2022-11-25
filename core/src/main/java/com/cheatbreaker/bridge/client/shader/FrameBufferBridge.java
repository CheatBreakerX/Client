package com.cheatbreaker.bridge.client.shader;

public interface FrameBufferBridge {
    void bridge$unbindFramebuffer();
    void bridge$bindFramebuffer(boolean b);
    void bridge$framebufferRender(float width, float height);
}
