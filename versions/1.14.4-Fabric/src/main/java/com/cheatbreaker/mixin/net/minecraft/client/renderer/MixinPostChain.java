package com.cheatbreaker.mixin.net.minecraft.client.renderer;

import com.cheatbreaker.bridge.client.shader.ShaderBridge;
import com.cheatbreaker.bridge.client.shader.ShaderGroupBridge;
import net.minecraft.client.renderer.PostChain;
import net.minecraft.client.renderer.PostPass;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

import java.util.ArrayList;
import java.util.List;

@Mixin(PostChain.class)
public abstract class MixinPostChain implements ShaderGroupBridge {
    @Shadow public abstract void close();
    @Shadow public abstract void resize(int width, int height);

    @Shadow @Final private List<PostPass> passes;

    public void bridge$deleteShaderGroup() {
        this.close();
    }

    public void bridge$createBindFramebuffers(int displayWidth, int displayHeight) {
        this.resize(displayWidth, displayHeight);
    }

    public List<ShaderBridge> bridge$getListShaders() {
        List<ShaderBridge> list = new ArrayList<>();

        for (PostPass shader : this.passes) {
            list.add((ShaderBridge) shader);
        }

        return list;
    }
}
