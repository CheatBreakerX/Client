package com.cheatbreaker.impl.ref;

import com.cheatbreaker.bridge.ref.IDrawingUtils;
import com.cheatbreaker.bridge.ref.Ref;
import com.cheatbreaker.bridge.util.ResourceLocationBridge;
import com.cheatbreaker.util.Utils;
import com.mojang.blaze3d.platform.GlStateManager;
import com.mojang.blaze3d.vertex.BufferBuilder;
import com.mojang.blaze3d.vertex.DefaultVertexFormat;
import com.mojang.blaze3d.vertex.Tesselator;
import com.mojang.math.Matrix4f;
import net.minecraft.Util;
import net.minecraft.client.Minecraft;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import org.lwjgl.opengl.GL11;

import java.util.List;

public class DrawingUtils implements IDrawingUtils {
    public void drawGradientRect(float left, float top, float right, float bottom, int startColor, int endColor) {
        float f = (float)(startColor >> 24 & 255) / 255.0F;
        float f1 = (float)(startColor >> 16 & 255) / 255.0F;
        float f2 = (float)(startColor >> 8 & 255) / 255.0F;
        float f3 = (float)(startColor & 255) / 255.0F;
        float f4 = (float)(endColor >> 24 & 255) / 255.0F;
        float f5 = (float)(endColor >> 16 & 255) / 255.0F;
        float f6 = (float)(endColor >> 8 & 255) / 255.0F;
        float f7 = (float)(endColor & 255) / 255.0F;
        GlStateManager.disableTexture();
        GlStateManager.enableBlend();
        GlStateManager.disableAlphaTest();
        GlStateManager.blendFuncSeparate(770, 771, 1, 0);
        GlStateManager.shadeModel(7425);
        Tesselator tesselator = Tesselator.getInstance();
        BufferBuilder buffer = tesselator.getBuilder();
        buffer.begin(7, DefaultVertexFormat.POSITION_COLOR);
        buffer.vertex(right, top, 0D).color(f1, f2, f3, f).endVertex();
        buffer.vertex(left, top, 0D).color(f1, f2, f3, f).endVertex();
        buffer.vertex(left, bottom, 0D).color(f5, f6, f7, f4).endVertex();
        buffer.vertex(right, bottom, 0D).color(f5, f6, f7, f4).endVertex();
        tesselator.end();
        GlStateManager.shadeModel(7424);
        GlStateManager.disableBlend();
        GlStateManager.enableAlphaTest();
        GlStateManager.enableTexture();
    }

    public void drawRect(float left, float top, float right, float bottom, int color) {
        if (left < right) {
            float i = left;
            left = right;
            right = i;
        }

        if (top < bottom) {
            float j = top;
            top = bottom;
            bottom = j;
        }

        float f3 = (float)(color >> 24 & 255) / 255.0F;
        float f = (float)(color >> 16 & 255) / 255.0F;
        float f1 = (float)(color >> 8 & 255) / 255.0F;
        float f2 = (float)(color & 255) / 255.0F;
        Tesselator tesselator = Tesselator.getInstance();
        BufferBuilder buffer = tesselator.getBuilder();
        GlStateManager.enableBlend();
        GlStateManager.disableTexture();
        GlStateManager.blendFuncSeparate(770, 771, 1, 0);
        GlStateManager.color4f(f, f1, f2, f3);
        buffer.begin(7, DefaultVertexFormat.POSITION);
        buffer.vertex(left, bottom, 0.0D).endVertex();
        buffer.vertex(right, bottom, 0.0D).endVertex();
        buffer.vertex(right, top, 0.0D).endVertex();
        buffer.vertex(left, top, 0.0D).endVertex();
        tesselator.end();
        GlStateManager.enableTexture();
        GlStateManager.disableBlend();
    }

    private float panoramaTime = 0.f;
    private void drawPanorama(float speed, int panoramaTimer, ResourceLocationBridge[] images) {
        List<ResourceLocation> titlePanoramaPaths = Utils.convertListType(Utils.toList(images));

        this.panoramaTime += Ref.getMinecraft().bridge$getTimer().bridge$getRenderPartialTicks();
        float f = Mth.sin(this.panoramaTime * 0.001f) * 5f + 25f;
        float g = -this.panoramaTime * 0.1f;
        float h = 1f;

        Tesselator lv = Tesselator.getInstance();
        BufferBuilder lv2 = lv.getBuilder();
        GlStateManager.matrixMode(5889);
        GlStateManager.pushMatrix();
        GlStateManager.loadIdentity();
        GlStateManager.multMatrix(
                Matrix4f.perspective(
                        85.0,
                        (float)Minecraft.getInstance().window.getWidth()
                                / (float)Minecraft.getInstance().window.getHeight(),
                        0.05F, 10.0F
                )
        );
        GlStateManager.matrixMode(5888);
        GlStateManager.pushMatrix();
        GlStateManager.loadIdentity();
        GlStateManager.color4f(1.0F, 1.0F, 1.0F, 1.0F);
        GlStateManager.rotatef(180.0F, 1.0F, 0.0F, 0.0F);
        GlStateManager.enableBlend();
        GlStateManager.disableAlphaTest();
        GlStateManager.disableCull();
        GlStateManager.depthMask(false);
        GlStateManager.blendFuncSeparate(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA, GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ZERO);

        for (int j = 0; j < 4; ++j) {
            GlStateManager.pushMatrix();
            float k = ((float)(j % 2) / 2.0F - 0.5F) / 256.0F;
            float l = ((float)(j / 2) / 2.0F - 0.5F) / 256.0F;
            GlStateManager.translatef(k, l, 0.0F);
            GlStateManager.rotatef(f, 1.0F, 0.0F, 0.0F);
            GlStateManager.rotatef(g, 0.0F, 1.0F, 0.0F);

            for (int n = 0; n < 6; ++n) {
                Minecraft.getInstance().getTextureManager().bind(titlePanoramaPaths.get(n));
                lv2.begin(7, DefaultVertexFormat.POSITION_TEX_COLOR);
                int o = Math.round(255.0F * h) / (j + 1);
                if (n == 0) {
                    lv2.vertex(-1.0, -1.0, 1.0).uv(0.0, 0.0).color(255, 255, 255, o).endVertex();
                    lv2.vertex(-1.0, 1.0, 1.0).uv(0.0, 1.0).color(255, 255, 255, o).endVertex();
                    lv2.vertex(1.0, 1.0, 1.0).uv(1.0, 1.0).color(255, 255, 255, o).endVertex();
                    lv2.vertex(1.0, -1.0, 1.0).uv(1.0, 0.0).color(255, 255, 255, o).endVertex();
                }

                if (n == 1) {
                    lv2.vertex(1.0, -1.0, 1.0).uv(0.0, 0.0).color(255, 255, 255, o).endVertex();
                    lv2.vertex(1.0, 1.0, 1.0).uv(0.0, 1.0).color(255, 255, 255, o).endVertex();
                    lv2.vertex(1.0, 1.0, -1.0).uv(1.0, 1.0).color(255, 255, 255, o).endVertex();
                    lv2.vertex(1.0, -1.0, -1.0).uv(1.0, 0.0).color(255, 255, 255, o).endVertex();
                }

                if (n == 2) {
                    lv2.vertex(1.0, -1.0, -1.0).uv(0.0, 0.0).color(255, 255, 255, o).endVertex();
                    lv2.vertex(1.0, 1.0, -1.0).uv(0.0, 1.0).color(255, 255, 255, o).endVertex();
                    lv2.vertex(-1.0, 1.0, -1.0).uv(1.0, 1.0).color(255, 255, 255, o).endVertex();
                    lv2.vertex(-1.0, -1.0, -1.0).uv(1.0, 0.0).color(255, 255, 255, o).endVertex();
                }

                if (n == 3) {
                    lv2.vertex(-1.0, -1.0, -1.0).uv(0.0, 0.0).color(255, 255, 255, o).endVertex();
                    lv2.vertex(-1.0, 1.0, -1.0).uv(0.0, 1.0).color(255, 255, 255, o).endVertex();
                    lv2.vertex(-1.0, 1.0, 1.0).uv(1.0, 1.0).color(255, 255, 255, o).endVertex();
                    lv2.vertex(-1.0, -1.0, 1.0).uv(1.0, 0.0).color(255, 255, 255, o).endVertex();
                }

                if (n == 4) {
                    lv2.vertex(-1.0, -1.0, -1.0).uv(0.0, 0.0).color(255, 255, 255, o).endVertex();
                    lv2.vertex(-1.0, -1.0, 1.0).uv(0.0, 1.0).color(255, 255, 255, o).endVertex();
                    lv2.vertex(1.0, -1.0, 1.0).uv(1.0, 1.0).color(255, 255, 255, o).endVertex();
                    lv2.vertex(1.0, -1.0, -1.0).uv(1.0, 0.0).color(255, 255, 255, o).endVertex();
                }

                if (n == 5) {
                    lv2.vertex(-1.0, 1.0, 1.0).uv(0.0, 0.0).color(255, 255, 255, o).endVertex();
                    lv2.vertex(-1.0, 1.0, -1.0).uv(0.0, 1.0).color(255, 255, 255, o).endVertex();
                    lv2.vertex(1.0, 1.0, -1.0).uv(1.0, 1.0).color(255, 255, 255, o).endVertex();
                    lv2.vertex(1.0, 1.0, 1.0).uv(1.0, 0.0).color(255, 255, 255, o).endVertex();
                }

                lv.end();
            }

            GlStateManager.popMatrix();
            GlStateManager.colorMask(true, true, true, false);
        }

        lv2.offset(0.0, 0.0, 0.0);
        GlStateManager.colorMask(true, true, true, true);
        GlStateManager.matrixMode(5889);
        GlStateManager.popMatrix();
        GlStateManager.matrixMode(5888);
        GlStateManager.popMatrix();
        GlStateManager.depthMask(true);
        GlStateManager.enableCull();
        GlStateManager.enableDepthTest();
    }

    public void renderSkybox(ResourceLocationBridge[] images, float speed, int scaledWidth, int scaledHeight, int panoramaTimer, ResourceLocationBridge backgroundLocation) {
        this.drawPanorama(speed, panoramaTimer, images);
    }
}
