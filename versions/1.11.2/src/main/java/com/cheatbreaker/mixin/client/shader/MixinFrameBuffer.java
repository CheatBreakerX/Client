package com.cheatbreaker.mixin.client.shader;

import com.cheatbreaker.bridge.client.shader.FrameBufferBridge;
import net.minecraft.client.shader.Framebuffer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(Framebuffer.class)
public abstract class MixinFrameBuffer implements FrameBufferBridge {
    @Shadow public abstract void unbindFramebuffer();
    @Shadow public abstract void bindFramebuffer(boolean p_147610_1_);
    @Shadow public abstract void framebufferRender(int p_147615_1_, int p_147615_2_);

    public void bridge$unbindFramebuffer() {
        this.unbindFramebuffer();
    }

    public void bridge$bindFramebuffer(boolean b) {
        this.bindFramebuffer(b);
    }

    public void bridge$framebufferRender(float width, float height) {
        this.framebufferRender((int) width, (int) height);
    }
}
