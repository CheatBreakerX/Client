package com.cheatbreaker.impl.ref;

import com.cheatbreaker.bridge.ext.GLBridge;
import com.mojang.blaze3d.platform.GLX;
import com.mojang.blaze3d.platform.GlStateManager;
import org.lwjgl.opengl.GL11;
import org.lwjgl.system.NativeType;

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
        GlStateManager.enableTexture();
    }

    public void bridge$disableTexture2D() {
        GlStateManager.disableTexture();
    }

    public void bridge$color(float r, float g, float b, float a) {
        GlStateManager.color4f(r, g, b, a);
    }

    public void bridge$glBlendFunc(int sfactorRGB, int dfactorRGB, int sfactorAlpha, int dfactorAlpha) {
        GLX.glBlendFuncSeparate(sfactorRGB, dfactorRGB, sfactorAlpha, dfactorAlpha);
    }

    public void bridge$enableAlphaTest() {
        GlStateManager.enableAlphaTest();
    }

    public void bridge$disableAlphaTest() {
        GlStateManager.disableAlphaTest();
    }

    public void bridge$setShadeModel(int i) {
        GlStateManager.shadeModel(i);
    }

    public boolean bridge$isFramebufferEnabled() {
        return GLX.isUsingFBOs();
    }

    public boolean bridge$isShadersSupported() {
        return GLX.isNextGen();
    }

    public void bridge$gluProject(float objX, float objY, float objZ, FloatBuffer modelMatrix, FloatBuffer projMatrix,
                                  IntBuffer viewport, FloatBuffer win_pos) {
        //GLU.gluProject(objX, objY, objZ, modelMatrix, projMatrix, viewport, win_pos);
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
        GlStateManager.scalef(x, y, z);
    }

    public void bridge$enableDepthTest() {
        GlStateManager.enableDepthTest();
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
        GlStateManager.translatef(x, y, z);
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
        GlStateManager.popAttributes();
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
        GlStateManager.disableDepthTest();
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
        GlStateManager.polygonOffset(factor, units);
    }

    public void bridge$disableCullFace() {
        GlStateManager.disableCull();
    }

    public void bridge$disableColorLogic() {
        GlStateManager.disableColorLogicOp();
    }

    public void bridge$enableColorLogic() {
        GlStateManager.enableColorLogicOp();
    }

    public void bridge$colorLogicOp(int code) {
        GlStateManager.logicOp(code);
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
