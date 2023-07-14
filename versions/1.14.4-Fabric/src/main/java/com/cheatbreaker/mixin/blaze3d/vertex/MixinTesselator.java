package com.cheatbreaker.mixin.blaze3d.vertex;

import com.cheatbreaker.bridge.client.renderer.TessellatorBridge;
import com.cheatbreaker.bridge.ext.GLColor;
import com.mojang.blaze3d.vertex.BufferBuilder;
import com.mojang.blaze3d.vertex.DefaultVertexFormat;
import com.mojang.blaze3d.vertex.Tesselator;
import com.mojang.blaze3d.vertex.VertexFormat;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

import java.util.Locale;

@Mixin(Tesselator.class)
public abstract class MixinTesselator implements TessellatorBridge {
    @Shadow public abstract void end();

    @Shadow @Final private BufferBuilder builder;

    public void bridge$startDrawingQuads() {
        this.bridge$startDrawing(7);
    }

    public void bridge$finish() {
        this.end();
    }

    public void bridge$startDrawing(int mode) {
        this.builder.begin(mode, DefaultVertexFormat.POSITION_TEX);
    }

    public TessellatorBridge bridge$setColorOpaque_I(int color) {
        int red = color >> 16 & 255;
        int green = color >> 8 & 255;
        int blue = color & 255;
        this.builder.color(red, green, blue, 255);
        return this;
    }

    public void bridge$setColorRGBA_F(float r, float g, float b, float a) {
        this.builder.color((int)(r * 255.0F), (int)(g * 255.0F), (int)(b * 255.0F), (int)(a * 255.0F));
    }

    public void bridge$setTranslation(double x, double y, double z) {
        this.builder.offset(x, y, z);
    }

    public void bridge$setColorRGBA_I(int color, int alpha) {
        int red = color >> 16 & 255;
        int green = color >> 8 & 255;
        int blue = color & 255;
        this.builder.color(red, green, blue, alpha);
    }

    public void bridge$addAndEndVertex(double x, double y, double z) {
        this.builder.vertex(x, y, z).endVertex();
    }

    public TessellatorBridge bridge$pos(double x, double y, double z) {
        this.builder.vertex(x, y, z);
        return this;
    }

    public TessellatorBridge bridge$tex(double u, double v) {
        this.builder.uv(u, v);
        return this;
    }

    public TessellatorBridge bridge$color(float r, float g, float b, float a) {
        this.builder.color(r, g, b, a);
        return this;
    }

    public TessellatorBridge bridge$color(int r, int g, int b, int a) {
        this.builder.color(r, g, b, a);
        return this;
    }

    public TessellatorBridge bridge$inheritGLSMColor() {
        this.builder.color(GLColor.glsmCurrentColor.getRed(), GLColor.glsmCurrentColor.getGreen(), GLColor.glsmCurrentColor.getBlue(), GLColor.glsmCurrentColor.getAlpha());
        return this;
    }

    public void bridge$endVertex() {
        this.builder.endVertex();
    }

    public TessellatorBridge bridge$begin(int glMode, String vertexFmt) {
        VertexFormat fmt = null;

        switch (vertexFmt.toLowerCase(Locale.ROOT)) {
            case "block":
                fmt = DefaultVertexFormat.BLOCK;
                break;
            case "item":
                //fmt = DefaultVertexFormat.ITEM;
                break;
            case "oldmodel_position_tex_normal":
                //fmt = DefaultVertexFormat.OLDMODEL_POSITION_TEX_NORMAL;
                break;
            case "particle_position_tex_color_lmap":
                //fmt = DefaultVertexFormat.PARTICLE_POSITION_TEX_COLOR_LMAP;
                break;
            case "position":
                fmt = DefaultVertexFormat.POSITION;
                break;
            case "position_color":
                fmt = DefaultVertexFormat.POSITION_COLOR;
                break;
            case "position_tex":
                fmt = DefaultVertexFormat.POSITION_TEX;
                break;
            case "position_normal":
                fmt = DefaultVertexFormat.POSITION_NORMAL;
                break;
            case "position_tex_color":
                fmt = DefaultVertexFormat.POSITION_TEX_COLOR;
                break;
            case "position_tex_normal":
                fmt = DefaultVertexFormat.POSITION_TEX_NORMAL;
                break;
            case "position_tex_lmap_color":
                //fmt = DefaultVertexFormat.POSITION_TEX_LMAP_COLOR;
                break;
            case "position_tex_color_normal":
                fmt = DefaultVertexFormat.POSITION_TEX_COLOR_NORMAL;
                break;
        }

        if (fmt != null) {
            this.builder.begin(glMode, fmt);
        }

        return this;
    }
}
