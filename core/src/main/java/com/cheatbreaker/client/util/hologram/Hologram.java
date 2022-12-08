package com.cheatbreaker.client.util.hologram;

import com.cheatbreaker.bridge.ref.Ref;
import com.cheatbreaker.bridge.client.gui.FontRendererBridge;
import com.cheatbreaker.bridge.client.renderer.TessellatorBridge;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter
public class Hologram {
    private final UUID uuid;
    @Setter
    private String[] texts;
    private final double x;
    private final double y;
    private final double z;
    @Getter
    private static final List<Hologram> holograms = new ArrayList<>();

    public Hologram(UUID uuid, double x, double y, double z) {
        this.uuid = uuid;
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public static void lIIIIlIIllIIlIIlIIIlIIllI() {
        FontRendererBridge fontRenderer = Ref.getMinecraft().bridge$getFontRenderer();
        for (Hologram hologram : holograms) {
            if (hologram.getTexts() == null || hologram.getTexts().length == 0) continue;
            for (int i = hologram.getTexts().length - 1; i >= 0; --i) {
                String string = hologram.getTexts()[hologram.getTexts().length - i - 1];
                float f = (float)(hologram.getX() - Ref.getRenderManager().bridge$getRenderPosX());
                float f2 = (float)(hologram.getY() + 1.0 + (double)((float)i * (0.25f)) - Ref.getRenderManager().bridge$getRenderPosY());
                float f3 = (float)(hologram.getZ() - Ref.getRenderManager().bridge$getRenderPosZ());
                float f5 = 0.02666667f;
                Ref.getGlBridge().bridge$pushMatrix();
                Ref.getGlBridge().bridge$translate(f, f2, f3);
                Ref.getGlBridge().bridge$normal3f(0f, 1f, 0f);
                Ref.getGlBridge().bridge$rotate(-Ref.getRenderManager().bridge$getPlayerViewY(), 0.0f, 1.0f, 0.0f);
                Ref.getGlBridge().bridge$rotate(Ref.getRenderManager().bridge$getPlayerViewX(), 1.0f, 0.0f, 0.0f);
                Ref.getGlBridge().bridge$scale(-f5, -f5, f5);
                Ref.getGlBridge().bridge$disableLighting();
                Ref.getGlBridge().bridge$depthMask(false);
                Ref.getGlBridge().bridge$disableDepthTest();
                Ref.getGlBridge().bridge$enableBlend();
                Ref.getGlBridge().bridge$glBlendFunc(770, 771, 1, 0);
                TessellatorBridge tessellator = Ref.getTessellator();
                int n = 0;
                Ref.getGlBridge().bridge$disableTexture2D();
                tessellator.bridge$startDrawingQuads();
                int n2 = fontRenderer.bridge$getStringWidth(string) / 2;
                tessellator.bridge$pos(-n2 - 1, -1 + n, 0.0).bridge$color(0f, 0f, 0f, 0.25f).bridge$endVertex();
                tessellator.bridge$pos(-n2 - 1, 8 + n, 0.0).bridge$color(0f, 0f, 0f, 0.25f).bridge$endVertex();
                tessellator.bridge$pos(n2 + 1, 8 + n, 0.0).bridge$color(0f, 0f, 0f, 0.25f).bridge$endVertex();
                tessellator.bridge$pos(n2 + 1, -1 + n, 0.0).bridge$color(0f, 0f, 0f, 0.25f).bridge$endVertex();
                tessellator.bridge$finish();
                Ref.getGlBridge().bridge$enableTexture2D();
                fontRenderer.bridge$drawString(string, -fontRenderer.bridge$getStringWidth(string) / 2, n, 0x20FFFFFF);
                Ref.getGlBridge().bridge$enableDepthTest();
                Ref.getGlBridge().bridge$depthMask(true);
                fontRenderer.bridge$drawString(string, -fontRenderer.bridge$getStringWidth(string) / 2, n, -1);
                Ref.getGlBridge().bridge$enableLighting();
                Ref.getGlBridge().bridge$disableBlend();
                Ref.getGlBridge().bridge$color(1.0F, 1.0F, 1.0F, 1.0F);
                Ref.getGlBridge().bridge$popMatrix();
            }
        }
    }
}