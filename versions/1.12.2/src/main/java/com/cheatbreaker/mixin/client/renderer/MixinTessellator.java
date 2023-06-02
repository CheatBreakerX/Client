package com.cheatbreaker.mixin.client.renderer;

import com.cheatbreaker.bridge.client.renderer.TessellatorBridge;
import com.cheatbreaker.bridge.ext.GLColor;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.client.renderer.vertex.VertexFormat;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

import java.util.Locale;

@Mixin(Tessellator.class)
public abstract class MixinTessellator implements TessellatorBridge {
    @Shadow private BufferBuilder buffer;
    @Shadow public abstract void draw();

    public void bridge$startDrawingQuads() {
        this.buffer.begin(7, DefaultVertexFormats.POSITION_TEX);
    }

    public void bridge$addVertexWithUV(double x, double y, double z, double u, double v) {
        this.buffer.pos(x, y, z).tex(u, v).endVertex();
    }

    public void bridge$finish() {
        this.draw();
    }

    public void bridge$addVertex(double x, double y, double z) {
        this.buffer.addVertexData(new int[]{ (int)x, (int)y, (int)z });
    }

    public void bridge$startDrawing(int mode) {
        this.buffer.begin(mode, DefaultVertexFormats.POSITION_TEX);
    }

    public TessellatorBridge bridge$setColorOpaque_I(int color) {
        int red = color >> 16 & 255;
        int green = color >> 8 & 255;
        int blue = color & 255;
        this.buffer.color(red, green, blue, 255);
        return this;
    }

    public void bridge$setColorRGBA_F(float r, float g, float b, float a) {
        this.buffer.color((int)(r * 255.0F), (int)(g * 255.0F), (int)(b * 255.0F), (int)(a * 255.0F));
    }

    public void bridge$setTranslation(double x, double y, double z) {
        this.buffer.setTranslation(x, y, z);
    }

    public void bridge$setColorRGBA_I(int color, int alpha) {
        int red = color >> 16 & 255;
        int green = color >> 8 & 255;
        int blue = color & 255;
        this.buffer.color(red, green, blue, alpha);
    }

    public void bridge$addAndEndVertex(double x, double y, double z) {
        this.buffer.pos(x, y, z).endVertex();
    }

    public TessellatorBridge bridge$pos(double x, double y, double z) {
        this.buffer.pos(x, y, z);
        return this;
    }

    public TessellatorBridge bridge$tex(double u, double v) {
        this.buffer.tex(u, v);
        return this;
    }

    public TessellatorBridge bridge$color(float r, float g, float b, float a) {
        this.buffer.color(r, g, b, a);
        return this;
    }

    public TessellatorBridge bridge$color(int r, int g, int b, int a) {
        this.buffer.color(r, g, b, a);
        return this;
    }

    public TessellatorBridge bridge$inheritGLSMColor() {
        this.buffer.color(GLColor.glsmCurrentColor.getRed(), GLColor.glsmCurrentColor.getGreen(), GLColor.glsmCurrentColor.getBlue(), GLColor.glsmCurrentColor.getAlpha());
        return this;
    }

    public void bridge$endVertex() {
        this.buffer.endVertex();
    }

    public TessellatorBridge bridge$begin(int glMode, String vertexFmt) {
        VertexFormat fmt = null;

        switch (vertexFmt.toLowerCase(Locale.ROOT)) {
            case "block":
                fmt = DefaultVertexFormats.BLOCK;
                break;
            case "item":
                fmt = DefaultVertexFormats.ITEM;
                break;
            case "oldmodel_position_tex_normal":
                fmt = DefaultVertexFormats.OLDMODEL_POSITION_TEX_NORMAL;
                break;
            case "particle_position_tex_color_lmap":
                fmt = DefaultVertexFormats.PARTICLE_POSITION_TEX_COLOR_LMAP;
                break;
            case "position":
                fmt = DefaultVertexFormats.POSITION;
                break;
            case "position_color":
                fmt = DefaultVertexFormats.POSITION_COLOR;
                break;
            case "position_tex":
                fmt = DefaultVertexFormats.POSITION_TEX;
                break;
            case "position_normal":
                fmt = DefaultVertexFormats.POSITION_NORMAL;
                break;
            case "position_tex_color":
                fmt = DefaultVertexFormats.POSITION_TEX_COLOR;
                break;
            case "position_tex_normal":
                fmt = DefaultVertexFormats.POSITION_TEX_NORMAL;
                break;
            case "position_tex_lmap_color":
                fmt = DefaultVertexFormats.POSITION_TEX_LMAP_COLOR;
                break;
            case "position_tex_color_normal":
                fmt = DefaultVertexFormats.POSITION_TEX_COLOR_NORMAL;
                break;
        }

        if (fmt != null) {
            this.buffer.begin(glMode, fmt);
        }

        return this;
    }
}
