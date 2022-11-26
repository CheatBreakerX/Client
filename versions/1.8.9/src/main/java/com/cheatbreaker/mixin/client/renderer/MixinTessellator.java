package com.cheatbreaker.mixin.client.renderer;

import com.cheatbreaker.bridge.client.renderer.TessellatorBridge;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(Tessellator.class)
public abstract class MixinTessellator implements TessellatorBridge {
    @Shadow private WorldRenderer worldRenderer;

    public void bridge$startDrawingQuads() {
        this.worldRenderer.begin(7, DefaultVertexFormats.POSITION_TEX_COLOR);
    }

    public void bridge$addVertexWithUV(double x, double y, double z, double u, double v) {
        this.worldRenderer.pos(x, y, z).tex(u, v).endVertex();
    }

    public void bridge$finish() {
        this.worldRenderer.finishDrawing();
    }

    public void bridge$addVertex(double x, double y, double z) {
        this.worldRenderer.pos(x, y, z);
    }

    public void bridge$startDrawing(int mode) {
        this.worldRenderer.begin(mode, DefaultVertexFormats.POSITION_TEX);
    }

    public void bridge$setColorOpaque_I(int color) {
        java.awt.Color colorAsAwt = new java.awt.Color(color);
        this.worldRenderer.color(colorAsAwt.getRed(), colorAsAwt.getGreen(), colorAsAwt.getBlue(), 255);
    }

    public void bridge$setColorRGBA_F(float r, float g, float b, float a) {
        this.worldRenderer.color(r, g, b, a);
    }

    public void bridge$setTranslation(double x, double y, double z) {
        this.worldRenderer.setTranslation(x, y, z);
    }

    public void bridge$setColorRGBA_I(int color, int alpha) {
        java.awt.Color colorAsAwt = new java.awt.Color(color);
        this.worldRenderer.color(colorAsAwt.getRed(), colorAsAwt.getGreen(), colorAsAwt.getBlue(), colorAsAwt.getAlpha());
    }
}
