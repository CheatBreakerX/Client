package com.cheatbreaker.client.ui.extended;

import com.cheatbreaker.bridge.client.gui.ScaledResolutionBridge;
import com.cheatbreaker.bridge.ref.Ref;
import com.cheatbreaker.bridge.wrapper.CBGuiScreen;
import com.cheatbreaker.client.ui.util.RenderUtil;
import lombok.Setter;

import java.awt.*;

public class CustomLoadingScreen extends CBGuiScreen {
    @Setter
    private String message;

    public CustomLoadingScreen(String message) {
        this.message = message;
    }

    public void drawScreen(int mouseX, int mouseY, float delta) {
        Color startColor = new Color(0, 0, 0, 169);
        Color endColor = new Color(43, 43, 43, 97);

        ScaledResolutionBridge sr = Ref.getInstanceCreator().createScaledResolution();

        Ref.modified$drawRect(0f, 0f, sr.bridge$getScaledWidth(), sr.bridge$getScaledHeight(), 0xFF000000);
        Ref.modified$drawGradientRect(0f, 0f, sr.bridge$getScaledWidth(), sr.bridge$getScaledHeight(), startColor.getRGB(), endColor.getRGB());

        Ref.getGlBridge().bridge$pushMatrix();
        float scale = 2f / sr.bridge$getScaleFactor();
        Ref.getGlBridge().bridge$scale(scale, scale, scale);
        Ref.getGlBridge().bridge$popMatrix();
        Ref.getGlBridge().bridge$color(1.0f, 1.0f, 1.0f, 1.0f);
        RenderUtil.drawIcon("client/logo_108.png", 27f, sr.bridge$getScaledWidth() / 2f - 27f, sr.bridge$getScaledHeight() / 2f - 112f);

        Ref.getMinecraft().bridge$getFontRenderer().bridge$drawString(
                this.message,
                (sr.bridge$getScaledWidth() / 2f) - (Ref.getMinecraft().bridge$getFontRenderer().bridge$getStringWidth(this.message) / 2f),
                sr.bridge$getScaledHeight() / 2f - 19f,
                0xFFFFFFFF);
    }
}
