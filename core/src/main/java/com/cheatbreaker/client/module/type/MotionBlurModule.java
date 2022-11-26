package com.cheatbreaker.client.module.type;

import com.cheatbreaker.bridge.client.shader.ShaderBridge;
import com.cheatbreaker.bridge.client.shader.ShaderGroupBridge;
import com.cheatbreaker.bridge.client.shader.ShaderUniformBridge;
import com.cheatbreaker.bridge.ref.Ref;
import com.cheatbreaker.client.config.Setting;
import com.cheatbreaker.client.event.type.LoadWorldEvent;
import com.cheatbreaker.client.module.AbstractModule;
import com.google.common.base.Throwables;

public class MotionBlurModule extends AbstractModule {

    private final Setting amount;
    private final Setting color;

    public MotionBlurModule() {
        super("Motion Blur");
        this.setDefaultState(false);
        this.amount = new Setting(this, "Amount").setValue(5).setMinMax(1, 10);
        this.color = new Setting(this, "Color").setValue(-1).setMinMax(Integer.MIN_VALUE, Integer.MAX_VALUE);
        this.setPreviewLabel("Motion Blur", 5.0f * 0.22f);
        this.addEvent(LoadWorldEvent.class, this::onLoad);
    }

    private void onLoad(LoadWorldEvent event) {
        this.drawShader();
    }

    public void bindShader() {
        if (Ref.getGlBridge().bridge$isFramebufferEnabled() && Ref.getGlBridge().bridge$isShadersSupported()) {
            if (minecraft.bridge$getEntityRenderer().bridge$getShaderGroup() != null) {
                minecraft.bridge$getEntityRenderer().bridge$getShaderGroup().bridge$deleteShaderGroup();
            }
            try {
                minecraft.bridge$getEntityRenderer().bridge$setShaderGroup(Ref.getInstanceCreator().createShaderGroup(minecraft.bridge$getTextureManager(), minecraft.bridge$getEntityRenderer().bridge$getResourceManager(), minecraft.bridge$getFramebuffer(), Ref.getInstanceCreator().createResourceLocationBridge("shaders/post/motionblur.json")));
                minecraft.bridge$getEntityRenderer().bridge$getShaderGroup().bridge$createBindFramebuffers(minecraft.bridge$getDisplayWidth(), minecraft.bridge$getDisplayHeight());
            }
            catch (Exception exception) {
                // empty catch block
            }
        }
    }

    private void drawShader() {
        bindShader();
        ShaderGroupBridge shaderGroup = minecraft.bridge$getEntityRenderer().bridge$getShaderGroup();
        try {
            if (this.minecraft.bridge$getEntityRenderer().bridge$isShaderActive() && this.minecraft.bridge$getThePlayer() != null) {
                for (ShaderBridge shader : shaderGroup.bridge$getListShaders()) {
                    ShaderUniformBridge uniform = shader.bridge$getShaderManager().bridge$func_147991_a("Phosphor");
                    if (uniform == null) continue;
                    float f = 1.028125f * 0.68085104f + (float) (Integer) this.amount.getValue() / (float)100 * (float)3 - 0.7f * 0.014285714f;
                    int n = this.color.getColorValue();
                    float f2 = (float)(n >> 16 & 0xFF) / (float)255;
                    float f3 = (float)(n >> 8 & 0xFF) / (float)255;
                    float f4 = (float)(n & 0xFF) / (float)255;
                    uniform.bridge$func_148095_a(f * f2, f * f3, f * f4);
                }
            }
        }
        catch (IllegalArgumentException illegalArgumentException) {
            Throwables.propagate(illegalArgumentException);
        }
    }

}
