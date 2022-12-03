package com.cheatbreaker.client.util.hologram;

import com.cheatbreaker.bridge.ref.Ref;
import com.cheatbreaker.bridge.client.gui.FontRendererBridge;
import com.cheatbreaker.bridge.client.renderer.TessellatorBridge;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Hologram {
    private final UUID lIIIIlIIllIIlIIlIIIlIIllI;
    private String[] lIIIIIIIIIlIllIIllIlIIlIl;
    private final double IlllIIIlIlllIllIlIIlllIlI;
    private final double IIIIllIlIIIllIlllIlllllIl;
    private final double IIIIllIIllIIIIllIllIIIlIl;
    private static final List<Hologram> holograms = new ArrayList<>();

    public Hologram(UUID uUID, double d, double d2, double d3) {
        this.lIIIIlIIllIIlIIlIIIlIIllI = uUID;
        this.IlllIIIlIlllIllIlIIlllIlI = d;
        this.IIIIllIlIIIllIlllIlllllIl = d2;
        this.IIIIllIIllIIIIllIllIIIlIl = d3;
    }

    public static void lIIIIlIIllIIlIIlIIIlIIllI() {
        FontRendererBridge fontRenderer = Ref.getMinecraft().bridge$getFontRenderer();
        for (Hologram hologram : holograms) {
            if (hologram.IlllIIIlIlllIllIlIIlllIlI() == null || hologram.IlllIIIlIlllIllIlIIlllIlI().length <= 0) continue;
            for (int i = hologram.IlllIIIlIlllIllIlIIlllIlI().length - 1; i >= 0; --i) {
                String string = hologram.IlllIIIlIlllIllIlIIlllIlI()[hologram.IlllIIIlIlllIllIlIIlllIlI().length - i - 1];
                float f = (float)(hologram.IIIIllIlIIIllIlllIlllllIl() - (double)((float)Ref.getRenderManager().bridge$getRenderPosX()));
                float f2 = (float)(hologram.IIIIllIIllIIIIllIllIIIlIl() + 1.0 + (double)((float)i * (0.16049382f * 1.5576924f)) - (double)((float)Ref.getRenderManager().bridge$getRenderPosY()));
                float f3 = (float)(hologram.IlIlIIIlllIIIlIlllIlIllIl() - (double)((float)Ref.getRenderManager().bridge$getRenderPosZ()));
                float f4 = 1.7391304f * 0.92f;
                float f5 = 1.4081633f * 0.011835749f * f4;
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
                tessellator.bridge$setColorRGBA_F(0.0f, 0.0f, 0.0f, 0.6875f * 0.36363637f);
                tessellator.bridge$addVertex(-n2 - 1, -1 + n, 0.0);
                tessellator.bridge$addVertex(-n2 - 1, 8 + n, 0.0);
                tessellator.bridge$addVertex(n2 + 1, 8 + n, 0.0);
                tessellator.bridge$addVertex(n2 + 1, -1 + n, 0.0);
                tessellator.bridge$finish();
                Ref.getGlBridge().bridge$enableTexture2D();
                // lIIIIIIIIIlIllIIllIlIIlIl = drawString
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

    public UUID lIIIIIIIIIlIllIIllIlIIlIl() {
        return this.lIIIIlIIllIIlIIlIIIlIIllI;
    }

    public String[] IlllIIIlIlllIllIlIIlllIlI() {
        return this.lIIIIIIIIIlIllIIllIlIIlIl;
    }

    public void lIIIIlIIllIIlIIlIIIlIIllI(String[] arrstring) {
        this.lIIIIIIIIIlIllIIllIlIIlIl = arrstring;
    }

    public double IIIIllIlIIIllIlllIlllllIl() {
        return this.IlllIIIlIlllIllIlIIlllIlI;
    }

    public double IIIIllIIllIIIIllIllIIIlIl() {
        return this.IIIIllIlIIIllIlllIlllllIl;
    }

    public double IlIlIIIlllIIIlIlllIlIllIl() {
        return this.IIIIllIIllIIIIllIllIIIlIl;
    }

    public static List<Hologram> IIIllIllIlIlllllllIlIlIII() {
        return holograms;
    }
}