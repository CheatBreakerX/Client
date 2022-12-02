package com.cheatbreaker.impl.ref;

import com.cheatbreaker.bridge.ext.GLBridge;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import org.lwjgl.util.glu.GLU;

import java.nio.FloatBuffer;
import java.nio.IntBuffer;

public class BridgedGL implements GLBridge {
    public void bridge$enableBlend() {
        GlStateManager.enableBlend();
    }

    public void bridge$disableBlend() {
        GlStateManager.disableBlend();
    }

    public void bridge$enableTexture2D() {
        GlStateManager.enableTexture2D();
    }

    public void bridge$disableTexture2D() {
        GlStateManager.disableTexture2D();
    }

    public void bridge$color(float r, float g, float b, float a) {
        GlStateManager.color(r, g, b, a);
    }

    public void bridge$glBlendFunc(int i, int i1, int i2, int i3) {
        OpenGlHelper.glBlendFunc(i, i1, i2, i3);
    }

    public void bridge$enableAlphaTest() {
        GlStateManager.enableAlpha();
    }

    public void bridge$disableAlphaTest() {
        GlStateManager.disableAlpha();
    }

    public void bridge$setShadeModel(int i) {
        GlStateManager.shadeModel(i);
    }

    public boolean bridge$isFramebufferEnabled() {
        return OpenGlHelper.isFramebufferEnabled();
    }

    public boolean bridge$isShadersSupported() {
        return OpenGlHelper.shadersSupported;
    }

    public void bridge$gluProject(float objX, float objY, float objZ, FloatBuffer modelMatrix, FloatBuffer projMatrix, IntBuffer viewport, FloatBuffer win_pos) {
        GLU.gluProject(objX, objY, objZ, modelMatrix, projMatrix, viewport, win_pos);
    }

    public void bridge$gluPerspective(float v, float v1, float v2, float v3) {
        GLU.gluPerspective(v, v1, v2, v3);
    }

    public void bridge$blendFunc(int i, int i1) {
        GlStateManager.blendFunc(i, i1);
    }

    public void bridge$pushMatrix() {
        GlStateManager.pushMatrix();
    }

    public void bridge$popMatrix() {
        GlStateManager.popMatrix();
    }
}
