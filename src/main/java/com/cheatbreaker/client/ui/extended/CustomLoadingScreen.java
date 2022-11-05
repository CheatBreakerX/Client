package com.cheatbreaker.client.ui.extended;

import com.cheatbreaker.bridge.Ref;
import com.cheatbreaker.client.ui.util.RenderUtil;
import lombok.Setter;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.ScaledResolution;
import org.lwjgl.opengl.GL11;

import java.awt.*;

public class CustomLoadingScreen extends GuiScreen {
    @Setter
    private String message;

    public CustomLoadingScreen(String message) {
        this.message = message;
    }

    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        Color startColor = new Color(0, 0, 0, 169);
        Color endColor = new Color(43, 43, 43, 97);

        ScaledResolution sr = Ref.createScaledResolution();

        Ref.modified$drawRect(0f, 0f, sr.getScaledWidth(), sr.getScaledHeight(), 0xFF000000);
        Ref.modified$drawGradientRect(0f, 0f, sr.getScaledWidth(), sr.getScaledHeight(), startColor.getRGB(), endColor.getRGB());

        GL11.glPushMatrix();
        float scale = 2f / sr.getScaleFactor();
        float sWidth = sr.getScaledWidth() / scale;
        float sHeight = sr.getScaledHeight() / scale;
        GL11.glScalef(scale, scale, scale);

        GL11.glPopMatrix();

        GL11.glColor4f(1.0f, 1.0f, 1.0f, 1.0f);
        RenderUtil.drawIcon("client/logo_108.png", 27f, sr.getScaledWidth() / 2f - 27f, sr.getScaledHeight() / 2f - 112f);

        Ref.getMinecraft().bridge$getFontRenderer().bridge$drawString(
                this.message,
                (sr.getScaledWidth() / 2f) - (Ref.getMinecraft().bridge$getFontRenderer().bridge$getStringWidth(this.message) / 2f),
                sr.getScaledHeight() / 2f - 19f,
                0xFFFFFFFF);
    }
}
