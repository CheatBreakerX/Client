package com.cheatbreaker.mixin.client.shader;

import com.cheatbreaker.bridge.client.shader.ShaderBridge;
import com.cheatbreaker.bridge.client.shader.ShaderGroupBridge;
import net.minecraft.client.shader.Shader;
import net.minecraft.client.shader.ShaderGroup;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

import java.util.ArrayList;
import java.util.List;

@Mixin(ShaderGroup.class)
public abstract class MixinShaderGroup implements ShaderGroupBridge {
    @Shadow public abstract void deleteShaderGroup();

    @Shadow public abstract void createBindFramebuffers(int width, int height);

    @Shadow @Final private List<Shader> listShaders;

    public void bridge$deleteShaderGroup() {
        this.deleteShaderGroup();
    }

    public void bridge$createBindFramebuffers(int displayWidth, int displayHeight) {
        this.createBindFramebuffers(displayWidth, displayHeight);
    }

    public List<ShaderBridge> bridge$getListShaders() {
        List<ShaderBridge> list = new ArrayList<>();

        for (Shader shader : this.listShaders) {
            list.add((ShaderBridge) shader);
        }

        return list;
    }
}
