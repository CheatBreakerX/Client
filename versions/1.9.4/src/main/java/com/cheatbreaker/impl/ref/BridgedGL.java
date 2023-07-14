package com.cheatbreaker.impl.ref;

import com.cheatbreaker.bridge.ext.GLBridge;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.OpenGlHelper;
import org.lwjgl.opengl.GL11;
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

    public void bridge$blendFunc(int i, int i1) {
        GlStateManager.blendFunc(i, i1);
    }

    public void bridge$pushMatrix() {
        GlStateManager.pushMatrix();
    }

    public void bridge$popMatrix() {
        GlStateManager.popMatrix();
    }

    public void bridge$scale(float x, float y, float z) {
        GlStateManager.scale(x, y, z);
    }

    public void bridge$enableDepthTest() {
        GlStateManager.enableDepth();
    }

    public void bridge$disableFog() {
        GlStateManager.disableFog();
    }

    public void bridge$disableLighting() {
        GlStateManager.disableLighting();
    }

    public void bridge$matrixMode(int mode) {
        GlStateManager.matrixMode(mode);
    }

    public void bridge$loadIdentity() {
        GlStateManager.loadIdentity();
    }

    public void bridge$translate(float x, float y, float z) {
        GlStateManager.translate(x, y, z);
    }

    public void bridge$ortho(double left, double right, double bottom, double top, double zNear, double zFar) {
        GlStateManager.ortho(left, right, bottom, top, zNear, zFar);
    }

    public void bridge$flush() {
        GL11.glFlush();
    }

    public void bridge$alphaFunc(int func, float ref) {
        GlStateManager.alphaFunc(func, ref);
    }

    public void bridge$enableLineSmooth() {
        GL11.glEnable(GL11.GL_LINE_SMOOTH);
    }

    public void bridge$disableLineSmooth() {
        GL11.glDisable(GL11.GL_LINE_SMOOTH);
    }

    public void bridge$begin(int mode) {
        GL11.glBegin(mode);
    }

    public void bridge$end() {
        GL11.glEnd();
    }

    public void bridge$popAttrib() {
        GlStateManager.popAttrib();
    }

    public void bridge$pushAttrib(int mode) {
        GL11.glPushAttrib(mode);
    }

    public void bridge$vertex2d(double x, double y) {
        GL11.glVertex2d(x, y);
    }

    public void bridge$texCoord2d(double u, double v) {
        GL11.glTexCoord2d(u, v);
    }

    public void bridge$scissor(int x, int y, int width, int height) {
        GL11.glScissor(x, y, width, height);
    }

    public void bridge$enableScissoring() {
        GL11.glEnable(0xC11);
    }

    public void bridge$disableScissoring() {
        GL11.glDisable(0xC11);
    }

    public void bridge$rotate(float angle, float x, float y, float z) {
        GL11.glRotatef(angle, x, y, z);
    }

    public void bridge$texCoord2f(float x, float y) {
        GL11.glTexCoord2f(x, y);
    }

    public void bridge$bindTexture(int id) {
        GlStateManager.bindTexture(id);
    }

    public void bridge$disableDepthTest() {
        GlStateManager.disableDepth();
    }

    public void bridge$depthMask(boolean b) {
        GlStateManager.depthMask(b);
    }

    public void bridge$enableLighting() {
        GlStateManager.enableLighting();
    }

    public void bridge$normal3f(float x, float y, float z) {
        GL11.glNormal3f(x, y, z);
    }

    public void bridge$enableCullFace() {
        GlStateManager.enableCull();
    }

    public void bridge$polygonOffset(float factor, float units) {
        GlStateManager.doPolygonOffset(factor, units);
    }

    public void bridge$disableCullFace() {
        GlStateManager.disableCull();
    }

    public void bridge$disableColorLogic() {
        GlStateManager.disableColorLogic();
    }

    public void bridge$enableColorLogic() {
        GlStateManager.enableColorLogic();
    }

    public void bridge$colorLogicOp(int code) {
        GlStateManager.colorLogicOp(code);
    }

    public void bridge$enableRescaleNormal() {
        GlStateManager.enableRescaleNormal();
    }

    public void bridge$disableRescaleNormal() {
        GlStateManager.disableRescaleNormal();
    }

    public void bridge$enablePolygonOffset() {
        GlStateManager.enablePolygonOffset();
    }

    public void bridge$disablePolygonOffset() {
        GlStateManager.disablePolygonOffset();
    }

    public void bridge$lineWidth(float width) {
        GL11.glLineWidth(width);
    }

    public void bridge$hint(int target, int mode) {
        GL11.glHint(target, mode);
    }
}
