package com.cheatbreaker.mixin.com.mojang.blaze3d.pipeline;

import com.cheatbreaker.bridge.client.shader.FrameBufferBridge;
import com.mojang.blaze3d.pipeline.RenderTarget;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(RenderTarget.class)
public abstract class MixinRenderTarget implements FrameBufferBridge {
    @Shadow public abstract void destroyBuffers();
    @Shadow public abstract void bindWrite(boolean bl);
    @Shadow public abstract void bindRead();
    @Shadow public abstract void blitToScreen(int width, int height);

    public void bridge$unbindFramebuffer() {
        this.destroyBuffers();
    }

    public void bridge$bindFramebuffer(boolean b) {
        this.bindRead();
        this.bindWrite(b);
    }

    public void bridge$framebufferRender(float width, float height) {
        this.blitToScreen((int) width, (int) height);
    }
}
