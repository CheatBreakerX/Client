package com.cheatbreaker.client.module.type;

import com.cheatbreaker.bridge.client.MinecraftBridge;
import com.cheatbreaker.bridge.client.gui.ScaledResolutionBridge;
import com.cheatbreaker.bridge.client.renderer.TessellatorBridge;
import com.cheatbreaker.bridge.entity.player.EntityPlayerBridge;
import com.cheatbreaker.bridge.ref.Ref;
import com.cheatbreaker.bridge.util.Vec3Bridge;
import com.cheatbreaker.client.CheatBreaker;
import com.cheatbreaker.client.event.type.DisconnectEvent;
import com.cheatbreaker.client.event.type.GuiDrawEvent;
import com.cheatbreaker.client.util.teammates.IlIlIIlllIIIIIlIlIlIIIllI;
import com.cheatbreaker.client.util.teammates.Teammate;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.settings.GameSettings;
import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL11;
import org.lwjgl.util.glu.GLU;

import java.awt.*;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.util.ArrayList;
import java.util.List;

public class TeammatesModule {
    public FloatBuffer modelViewMatrixBuffer = BufferUtils.createFloatBuffer(16);
    public FloatBuffer projectionMatrixBuffer = BufferUtils.createFloatBuffer(16);
    private final List<Teammate> teammates;
    private final int[] IIIIllIlIIIllIlllIlllllIl = new int[]{-15007996, -43234, -3603713, -16580641, -8912129, -16601345, -2786, -64828, -15629042, -10744187};
    private boolean IIIIllIIllIIIIllIllIIIlIl = false;
    private final MinecraftBridge minecraft = Ref.getMinecraft();

    public TeammatesModule() {
        this.teammates = new ArrayList<>();
    }

    public double getDistance(double d, double d2, double d3) {
        double d4 = d - this.minecraft.bridge$getThePlayer().bridge$getPosX();
        double d5 = d2 - this.minecraft.bridge$getThePlayer().bridge$getPosY();
        double d6 = d3 - this.minecraft.bridge$getThePlayer().bridge$getPosZ();
        return Math.sqrt(d4 * d4 + d5 * d5 + d6 * d6);
    }

    private void onDraw(GuiDrawEvent guiDrawEvent) {
        if (this.teammates.isEmpty()) {
            return;
        }
        IntBuffer intBuffer = BufferUtils.createIntBuffer(16);
        GL11.glGetInteger(2978, intBuffer);
        float f2 = (float)(this.minecraft.bridge$getThePlayer().bridge$getLastTickPosX() + (this.minecraft.bridge$getThePlayer().bridge$getPosX() - this.minecraft.bridge$getThePlayer().bridge$getLastTickPosX()) * Ref.getMinecraft().bridge$getTimer().bridge$getRenderPartialTicks()) - (float) RenderManager.renderPosX;
        float f3 = (float)(this.minecraft.bridge$getThePlayer().bridge$getLastTickPosY() + (this.minecraft.bridge$getThePlayer().bridge$getPosY() - this.minecraft.bridge$getThePlayer().bridge$getLastTickPosY()) * Ref.getMinecraft().bridge$getTimer().bridge$getRenderPartialTicks()) - (float) RenderManager.renderPosY;
        float f4 = (float)(this.minecraft.bridge$getThePlayer().bridge$getLastTickPosZ() + (this.minecraft.bridge$getThePlayer().bridge$getPosZ() - this.minecraft.bridge$getThePlayer().bridge$getLastTickPosZ()) * Ref.getMinecraft().bridge$getTimer().bridge$getRenderPartialTicks()) - (float) RenderManager.renderPosZ;
        double d = (double)(this.minecraft.bridge$getThePlayer().bridge$getRotationPitch() + (float)90) * (0.3249923327873289 * 9.666666984558105) / (double)180;
        double d2 = (double)(this.minecraft.bridge$getThePlayer().bridge$getRotationYaw() + (float)90) * (7.479982742083262 * (double)0.42f) / (double)180;
        Vec3Bridge vec3 = Ref.getInstanceCreator().createVec3(Math.sin(d) * Math.cos(d2), Math.cos(d), Math.sin(d) * Math.sin(d2));
        if (this.minecraft.bridge$getGameSettings().bridge$getThirdPersonView() == 2) {
            vec3 = Ref.getInstanceCreator().createVec3(vec3.bridge$getXCoord() * (double)-1, vec3.bridge$getYCoord() * (double)-1, vec3.bridge$getZCoord() * (double)-1);
        }
        for (Teammate teammate : this.teammates) {
            EntityPlayerBridge entityPlayer = this.minecraft.bridge$getTheWorld().bridge$getPlayerEntityByName(teammate.IlllIIIlIlllIllIlIIlllIlI());
            if (entityPlayer == null) {
                double d3;
                if (System.currentTimeMillis() - teammate.lIIIIIIIIIlIllIIllIlIIlIl() > teammate.IIIIllIlIIIllIlllIlllllIl()) continue;
                double d4 = teammate.getVector3D().bridge$getXCoord() - (double)f2;
                double d5 = teammate.getVector3D().bridge$getYCoord() - (double)f3;
                double d6 = teammate.getVector3D().bridge$getZCoord() - (double)f4;
                double d7 = this.getDistance(teammate.getVector3D().bridge$getXCoord(), teammate.getVector3D().bridge$getYCoord(), teammate.getVector3D().bridge$getZCoord());
                if (d7 > (d3 = (this.minecraft.bridge$getGameSettings().bridge$getOptionFloatValue(GameSettings.Options.RENDER_DISTANCE) * (float)16))) {
                    d4 = d4 / d7 * d3;
                    d5 = d5 / d7 * d3;
                    d6 = d6 / d7 * d3;
                }
                this.lIIIIlIIllIIlIIlIIIlIIllI(guiDrawEvent.getResolution(), teammate, (float)d4, (float)d5, (float)d6, intBuffer, (int)d7);
                continue;
            }
            if (entityPlayer == this.minecraft.bridge$getThePlayer()) continue;
            float f5 = (float)(entityPlayer.bridge$getLastTickPosX() + (entityPlayer.bridge$getPosX() - entityPlayer.bridge$getLastTickPosX()) * Ref.getMinecraft().bridge$getTimer().bridge$getRenderPartialTicks() - (double)f2);
            float f6 = (float)(entityPlayer.bridge$getLastTickPosY() + (entityPlayer.bridge$getPosY() - entityPlayer.bridge$getLastTickPosY()) * Ref.getMinecraft().bridge$getTimer().bridge$getRenderPartialTicks() - (double)f3) + entityPlayer.bridge$getHeight() + 1.0f;
            float f7 = (float)(entityPlayer.bridge$getLastTickPosZ() + (entityPlayer.bridge$getLastTickPosZ() - entityPlayer.bridge$getLastTickPosZ()) * Ref.getMinecraft().bridge$getTimer().bridge$getRenderPartialTicks() - (double)f4);
            double d8 = this.getDistance(entityPlayer.bridge$getPosX(), entityPlayer.bridge$getPosY(), entityPlayer.bridge$getPosZ());
            this.lIIIIlIIllIIlIIlIIIlIIllI(guiDrawEvent.getResolution(), teammate, f5, f6, f7, intBuffer, (int)d8);
        }
    }

    private void lIIIIlIIllIIlIIlIIIlIIllI(ScaledResolutionBridge scaledResolution, Teammate ilIlllIlIlIIllllIlllIlIII, float f, float f2, float f3, IntBuffer intBuffer, int n) {
        Vec3Bridge vec3 = Ref.getInstanceCreator().createVec3(f, f2, f3);
        double d = vec3.bridge$lengthVector();
        if (vec3.bridge$dotProduct(vec3 = vec3.bridge$normalize()) <= 2.0714285373687744 * 0.009655172572549829) {
            double d3 = Math.sin(3.883357527820847 * (double)0.4f);
            double d4 = Math.cos(0.7150309097153498 * 2.1724138259887695);
            Vec3Bridge vec33 = vec3.bridge$crossProduct(vec3);
            double d5 = vec33.bridge$getXCoord();
            double d6 = vec33.bridge$getYCoord();
            double d7 = vec33.bridge$getZCoord();
            double d8 = d4 + d5 * d5 * (1.0 - d4);
            double d9 = d5 * d6 * (1.0 - d4) - d7 * d3;
            double d10 = d5 * d7 * (1.0 - d4) + d6 * d3;
            double d11 = d6 * d5 * (1.0 - d4) + d7 * d3;
            double d12 = d4 + d6 * d6 * (1.0 - d4);
            double d13 = d6 * d7 * (1.0 - d4) - d5 * d3;
            double d14 = d7 * d5 * (1.0 - d4) - d6 * d3;
            double d15 = d7 * d6 * (1.0 - d4) + d5 * d3;
            double d16 = d4 + d7 * d7 * (1.0 - d4);
            f = (float)(d * (d8 * vec3.bridge$getXCoord() + d9 * vec3.bridge$getYCoord() + d10 * vec3.bridge$getZCoord()));
            f2 = (float)(d * (d11 * vec3.bridge$getXCoord() + d12 * vec3.bridge$getYCoord() + d13 * vec3.bridge$getZCoord()));
            f3 = (float)(d * (d14 * vec3.bridge$getXCoord() + d15 * vec3.bridge$getYCoord() + d16 * vec3.bridge$getZCoord()));
        }
        FloatBuffer floatBuffer = BufferUtils.createFloatBuffer(3);
        GLU.gluProject(f, f2, f3, this.modelViewMatrixBuffer, this.projectionMatrixBuffer, intBuffer, floatBuffer);
        float f4 = floatBuffer.get(0) / (float)scaledResolution.bridge$getScaleFactor();
        float f5 = floatBuffer.get(1) / (float)scaledResolution.bridge$getScaleFactor();
        IlIlIIlllIIIIIlIlIlIIIllI ilIlIIlllIIIIIlIlIlIIIllI = null;
        int n2 = 8;
        int n3 = 10;
        int n4 = -4 - n3;
        float f6 = (float)scaledResolution.bridge$getScaledHeight() - f5;
        if (f6 < 0.0f) {
            ilIlIIlllIIIIIlIlIlIIIllI = IlIlIIlllIIIIIlIlIlIIIllI.lIIIIlIIllIIlIIlIIIlIIllI;
            f5 = scaledResolution.bridge$getScaledHeight() - 6;
        } else if (f6 > (float)(scaledResolution.bridge$getScaledHeight() - n3)) {
            ilIlIIlllIIIIIlIlIlIIIllI = IlIlIIlllIIIIIlIlIlIIIllI.IlllIIIlIlllIllIlIIlllIlI;
            f5 = 6;
        }
        if (f4 - (float)n2 < 0.0f) {
            ilIlIIlllIIIIIlIlIlIIIllI = IlIlIIlllIIIIIlIlIlIIIllI.lIIIIIIIIIlIllIIllIlIIlIl;
            f4 = 6;
        } else if (f4 > (float)(scaledResolution.bridge$getScaledWidth() - n2)) {
            ilIlIIlllIIIIIlIlIlIIIllI = IlIlIIlllIIIIIlIlIlIIIllI.IIIIllIlIIIllIlllIlllllIl;
            f4 = scaledResolution.bridge$getScaledWidth() - 6;
        }
        GL11.glPushMatrix();
        GL11.glTranslatef(f4, (float)scaledResolution.bridge$getScaledHeight() - f5, 0.0f);
        if (ilIlIIlllIIIIIlIlIlIIIllI != null) {
            if (((Boolean) CheatBreaker.getInstance().getGlobalSettings().showOffScreenMarker.getValue())) {
                this.drawOffscreenMarker(ilIlllIlIlIIllllIlllIlIII, ilIlIIlllIIIIIlIlIlIIIllI, 0.0f, 0.0f);
            }
        } else {
            this.drawMarker(ilIlllIlIlIIllllIlllIlIII, n2, (float)n4, (float)n3);
            if (n > 40 && ((Boolean) CheatBreaker.getInstance().getGlobalSettings().showDistance.getValue())) {
                this.minecraft.bridge$getFontRenderer().bridge$drawString("(" + n + "m)", 0, 10, -1);
            }
        }
        GL11.glPopMatrix();
    }

    private void drawOffscreenMarker(Teammate ilIlllIlIlIIllllIlllIlIII, IlIlIIlllIIIIIlIlIlIIIllI ilIlIIlllIIIIIlIlIlIIIllI, float translateX, float translateY) {
        TessellatorBridge tessellator = Ref.getTessellator();
        GL11.glEnable(3042);
        GL11.glDisable(3553);
        Ref.getGlBridge().bridge$glBlendFunc(770, 771, 1, 0);
        if (ilIlllIlIlIIllllIlllIlIII.IIIIllIIllIIIIllIllIIIlIl()) {
            GL11.glColor4f(0.0f, 0.0f, 1.0f, 3.137931f * 0.21032967f);
        } else {
            Color color = ilIlllIlIlIIllllIlllIlIII.IlIlIIIlllIIIlIlllIlIllIl();
            GL11.glColor4f((float)color.getRed() / (float)255, (float)color.getGreen() / (float)255, (float)color.getBlue() / (float)255, 0.61285716f * 1.0769231f);
        }
        float f3 = 8;
        float f4 = 10;
        GL11.glPushMatrix();
        GL11.glTranslatef(translateX, translateY, 0.0f);
        switch (ilIlIIlllIIIIIlIlIlIIIllI) {
            case lIIIIIIIIIlIllIIllIlIIlIl: {
                tessellator.bridge$startDrawingQuads();
                tessellator.bridge$addVertex(f3 / 2.0f, f4 / 2.0f, 0.0);
                tessellator.bridge$addVertex(-f3 / 2.0f, 0.0, 0.0);
                tessellator.bridge$addVertex(f3 / 2.0f, -f4 / 2.0f, 0.0);
                tessellator.bridge$addVertex(-f3 / 2.0f, 0.0, 0.0);
                tessellator.bridge$finish();
                break;
            }
            case IIIIllIlIIIllIlllIlllllIl: {
                tessellator.bridge$startDrawingQuads();
                tessellator.bridge$addVertex(-f3 / 2.0f, f4 / 2.0f, 0.0);
                tessellator.bridge$addVertex(f3 / 2.0f, 0.0, 0.0);
                tessellator.bridge$addVertex(-f3 / 2.0f, -f4 / 2.0f, 0.0);
                tessellator.bridge$addVertex(f3 / 2.0f, 0.0, 0.0);
                tessellator.bridge$finish();
                break;
            }
            case IlllIIIlIlllIllIlIIlllIlI: {
                tessellator.bridge$startDrawingQuads();
                tessellator.bridge$addVertex(-f3 / 2.0f, -f4 / 2.0f, 0.0);
                tessellator.bridge$addVertex(0.0, f4 / 2.0f, 0.0);
                tessellator.bridge$addVertex(f3 / 2.0f, -f4 / 2.0f, 0.0);
                tessellator.bridge$addVertex(0.0, f4 / 2.0f, 0.0);
                tessellator.bridge$finish();
                break;
            }
            case lIIIIlIIllIIlIIlIIIlIIllI: {
                tessellator.bridge$startDrawingQuads();
                tessellator.bridge$addVertex(-f3 / 2.0f, f4 / 2.0f, 0.0);
                tessellator.bridge$addVertex(0.0, -f4 / 2.0f, 0.0);
                tessellator.bridge$addVertex(f3 / 2.0f, f4 / 2.0f, 0.0);
                tessellator.bridge$addVertex(0.0, -f4 / 2.0f, 0.0);
                tessellator.bridge$finish();
            }
        }
        GL11.glPopMatrix();
        GL11.glEnable(3553);
        GL11.glDisable(3042);
    }

    private void drawMarker(Teammate teammate, float f, float f2, float f3) {
        TessellatorBridge tessellator = Ref.getTessellator();
        GL11.glEnable(3042);
        GL11.glDisable(3553);
        Ref.getGlBridge().bridge$glBlendFunc(770, 771, 1, 0);
        if (teammate.IIIIllIIllIIIIllIllIIIlIl()) {
            GL11.glColor4f(0.0f, 0.0f, 1.0f, 0.83837837f * 0.78723407f);
        } else {
            Color color = teammate.IlIlIIIlllIIIlIlllIlIllIl();
            GL11.glColor4f((float)color.getRed() / (float)255, (float)color.getGreen() / (float)255, (float)color.getBlue() / (float)255, 1.755102f * 0.37604654f);
        }
        GL11.glPushMatrix();
        GL11.glScalef(0.9692308f * 0.61904764f, 1.1333333f * 0.5294118f, 1.2666667f * 0.47368422f);
        GL11.glRotatef(45, 0.0f, 0.0f, 1.0f);
        GL11.glTranslatef(f * 2.0f, 0.0f, 0.0f);
        GL11.glRotatef(90, 0.0f, 0.0f, -1);
        tessellator.bridge$startDrawingQuads();
        tessellator.bridge$addVertex(-f, f2, 0.0);
        tessellator.bridge$addVertex(-f, f2 + f3 / 2.0f, 0.0);
        tessellator.bridge$addVertex(f, f2 + f3 / 2.0f, 0.0);
        tessellator.bridge$addVertex(f, f2, 0.0);
        tessellator.bridge$finish();
        GL11.glRotatef(90, 0.0f, 0.0f, -1);
        GL11.glTranslatef(f * 2.0f + 1.0f, f3 / 2.0f + 1.0f, 0.0f);
        tessellator.bridge$startDrawingQuads();
        tessellator.bridge$addVertex(-f / 2.0f + 1.0f, f2, 0.0);
        tessellator.bridge$addVertex(-f / 2.0f + 1.0f, f2 + f3 / 2.0f, 0.0);
        tessellator.bridge$addVertex(f, f2 + f3 / 2.0f, 0.0);
        tessellator.bridge$addVertex(f, f2, 0.0);
        tessellator.bridge$finish();
        GL11.glPopMatrix();
        GL11.glEnable(3553);
        GL11.glDisable(3042);
    }

    public List<Teammate> lIIIIlIIllIIlIIlIIIlIIllI() {
        return this.teammates;
    }

    private void lIIIIlIIllIIlIIlIIIlIIllI(DisconnectEvent cBDisconnectEvent) {
        this.teammates.clear();
    }

    public Teammate lIIIIlIIllIIlIIlIIIlIIllI(String string) {
        for (Teammate ilIlllIlIlIIllllIlllIlIII : this.teammates) {
            if (!ilIlllIlIlIIllllIlllIlIII.IlllIIIlIlllIllIlIIlllIlI().equals(string)) continue;
            return ilIlllIlIlIIllllIlllIlIII;
        }
        return null;
    }

    public int[] lIIIIIIIIIlIllIIllIlIIlIl() {
        return this.IIIIllIlIIIllIlllIlllllIl;
    }

    public boolean IlllIIIlIlllIllIlIIlllIlI() {
        return this.IIIIllIIllIIIIllIllIIIlIl;
    }

    public void lIIIIlIIllIIlIIlIIIlIIllI(boolean bl) {
        if (bl && !this.IIIIllIIllIIIIllIllIIIlIl) {
            this.IIIIllIIllIIIIllIllIIIlIl = true;
            CheatBreaker.getInstance().getEventBus().addEvent(GuiDrawEvent.class, this::onDraw);
            CheatBreaker.getInstance().getEventBus().addEvent(DisconnectEvent.class, this::lIIIIlIIllIIlIIlIIIlIIllI);
        } else if (!bl && this.IIIIllIIllIIIIllIllIIIlIl) {
            this.IIIIllIIllIIIIllIllIIIlIl = false;
            CheatBreaker.getInstance().getEventBus().removeEvent(GuiDrawEvent.class, this::onDraw);
            CheatBreaker.getInstance().getEventBus().removeEvent(DisconnectEvent.class, this::lIIIIlIIllIIlIIlIIIlIIllI);
        }
    }
}