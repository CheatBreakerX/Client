package com.cheatbreaker.client.util.worldborder;

import com.cheatbreaker.bridge.client.entity.EntityClientPlayerMPBridge;
import com.cheatbreaker.bridge.ref.Ref;
import com.cheatbreaker.bridge.client.MinecraftBridge;
import com.cheatbreaker.bridge.client.renderer.TessellatorBridge;
import com.cheatbreaker.bridge.util.ResourceLocationBridge;
import com.cheatbreaker.main.CheatBreaker;
import com.cheatbreaker.client.event.type.CollisionEvent;
import com.cheatbreaker.client.event.type.RenderWorldEvent;
import com.cheatbreaker.client.event.type.TickEvent;
import org.lwjgl.opengl.GL11;

import javax.vecmath.Vector2d;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class WorldBorderManager {

    private final MinecraftBridge minecraft = Ref.getMinecraft();
    private final CheatBreaker cheatbreaker = CheatBreaker.getInstance();
    private static final ResourceLocationBridge forceFieldTexture = Ref.getInstanceCreator().createResourceLocationBridge("textures/misc/forcefield.png");
    private final List<WorldBorder> borderList = new ArrayList<>();

    public WorldBorderManager() {
        CheatBreaker.getInstance().getEventBus().addEvent(TickEvent.class, this::onTick);
        CheatBreaker.getInstance().getEventBus().addEvent(RenderWorldEvent.class, this::onWorldRender);
        CheatBreaker.getInstance().getEventBus().addEvent(CollisionEvent.class, this::onCollision);
    }

    private void onCollision(CollisionEvent cbCollisionEvent) {
        for (WorldBorder border : this.borderList) {
            if (border.lIIIIlIIllIIlIIlIIIlIIllI(cbCollisionEvent.getX(), cbCollisionEvent.getZ())) continue;
            cbCollisionEvent.getBoundingBoxes().add(Ref.getInstanceCreator().createAxisAlignedBB(cbCollisionEvent.getX(), cbCollisionEvent.getY(), cbCollisionEvent.getZ(), cbCollisionEvent.getX() + 1.0, cbCollisionEvent.getY() + 1.0, cbCollisionEvent.getZ() + 1.0));
        }
    }

    private void onTick(TickEvent cBTickEvent) {
        this.borderList.forEach(WorldBorder::ting);
    }

    private void onWorldRender(RenderWorldEvent renderWorldEvent) {
        if (!this.borderList.isEmpty()) {
            EntityClientPlayerMPBridge playerMP = this.minecraft.bridge$getThePlayer();
            float f = renderWorldEvent.getPartialTicks();
            this.borderList.stream().filter(WorldBorder::worldEqualsWorld).forEach(border -> {
                TessellatorBridge tessellator = Ref.getTessellator();
                double d = this.minecraft.bridge$getGameSettings().bridge$getRenderDistanceChunks() * 16;
                if (playerMP.bridge$getPosX() >= border.IIIIllIlIIIllIlllIlllllIl() - d || playerMP.bridge$getPosX() <= border.IlIlIIIlllIIIlIlllIlIllIl() + d || playerMP.bridge$getPosZ() >= border.IIIIllIIllIIIIllIllIIIlIl() - d || playerMP.bridge$getPosZ() <= border.IIIllIllIlIlllllllIlIlIII() + d) {
                    float f2;
                    double d2;
                    double d3;
                    float f3;
                    border.lIIIIlIIllIIlIIlIIIlIIllI(playerMP);
                    double d5 = playerMP.bridge$getLastTickPosX() + (playerMP.bridge$getPosX() - playerMP.bridge$getLastTickPosX()) * (double)f;
                    double d6 = playerMP.bridge$getLastTickPosY() + (playerMP.bridge$getPosY() - playerMP.bridge$getLastTickPosY()) * (double)f;
                    double d7 = playerMP.bridge$getLastTickPosZ() + (playerMP.bridge$getPosZ() - playerMP.bridge$getLastTickPosZ()) * (double)f;
                    GL11.glEnable(3042);
                    GL11.glBlendFunc(770, 1);
                    this.minecraft.bridge$getTextureManager().bridge$bindTexture(forceFieldTexture);
                    GL11.glDepthMask(false);
                    GL11.glPushMatrix();
                    float f4 = (float)(WorldBorder.IlllIIIlIlllIllIlIIlllIlI(border).getRed() & 0xFF) / (float)255;
                    float f5 = (float)(WorldBorder.IlllIIIlIlllIllIlIIlllIlI(border).getGreen() & 0xFF) / (float)255;
                    float f6 = (float)(WorldBorder.IlllIIIlIlllIllIlIIlllIlI(border).getBlue() & 0xFF) / (float)255;
                    GL11.glPolygonOffset(-3, -3);
                    GL11.glEnable(32823);
                    GL11.glAlphaFunc(516, 0.097260274f * 1.028169f);
                    GL11.glEnable(3008);
                    GL11.glDisable(2884);
                    float f7 = (float)(Ref.getMinecraft().bridge$getSystemTime() % 3000L) / (float)3000;
                    tessellator.bridge$startDrawingQuads();
                    GL11.glTranslated(-d5, -d6, -d7);
                    tessellator.bridge$setColorRGBA_F(f4, f5, f6, 1.0f);
                    double d8 = Math.max(MathHelper$floor_double(d7 - d), border.IIIllIllIlIlllllllIlIlIII());
                    double d9 = Math.min(MathHelper$ceiling_double_int(d7 + d), border.IIIIllIIllIIIIllIllIIIlIl());
                    if (d5 > border.IIIIllIlIIIllIlllIlllllIl() - d) {
                        f3 = 0.0f;
                        d3 = d8;
                        while (d3 < d9) {
                            d2 = Math.min(1.0, d9 - d3);
                            f2 = (float)d2 * (0.04054054f * 12.333334f);
                            tessellator.bridge$addVertexWithUV(border.IIIIllIlIIIllIlllIlllllIl(), 256, d3, f7 + f3, f7 + 0.0f);
                            tessellator.bridge$addVertexWithUV(border.IIIIllIlIIIllIlllIlllllIl(), 256, d3 + d2, f7 + f2 + f3, f7 + 0.0f);
                            tessellator.bridge$addVertexWithUV(border.IIIIllIlIIIllIlllIlllllIl(), 0.0, d3 + d2, f7 + f2 + f3, f7 + (float)128);
                            tessellator.bridge$addVertexWithUV(border.IIIIllIlIIIllIlllIlllllIl(), 0.0, d3, f7 + f3, f7 + (float)128);
                            d3 += 1.0;
                            f3 += 0.16463414f * 3.0370371f;
                        }
                    }
                    if (d5 < border.IlIlIIIlllIIIlIlllIlIllIl() + d) {
                        f3 = 0.0f;
                        d3 = d8;
                        while (d3 < d9) {
                            d2 = Math.min(1.0, d9 - d3);
                            f2 = (float)d2 * (0.3611111f * 1.3846154f);
                            tessellator.bridge$addVertexWithUV(border.IlIlIIIlllIIIlIlllIlIllIl(), 256, d3, f7 + f3, f7 + 0.0f);
                            tessellator.bridge$addVertexWithUV(border.IlIlIIIlllIIIlIlllIlIllIl(), 256, d3 + d2, f7 + f2 + f3, f7 + 0.0f);
                            tessellator.bridge$addVertexWithUV(border.IlIlIIIlllIIIlIlllIlIllIl(), 0.0, d3 + d2, f7 + f2 + f3, f7 + (float)128);
                            tessellator.bridge$addVertexWithUV(border.IlIlIIIlllIIIlIlllIlIllIl(), 0.0, d3, f7 + f3, f7 + (float)128);
                            d3 += 1.0;
                            f3 += 1.25f * 0.4f;
                        }
                    }
                    d8 = Math.max(MathHelper$floor_double(d5 - d), border.IlIlIIIlllIIIlIlllIlIllIl());
                    d9 = Math.min(MathHelper$ceiling_double_int(d5 + d), border.IIIIllIlIIIllIlllIlllllIl());
                    if (d7 > border.IIIIllIIllIIIIllIllIIIlIl() - d) {
                        f3 = 0.0f;
                        d3 = d8;
                        while (d3 < d9) {
                            d2 = Math.min(1.0, d9 - d3);
                            f2 = (float)d2 * (0.3115942f * 1.6046512f);
                            tessellator.bridge$addVertexWithUV(d3, 256, border.IIIIllIIllIIIIllIllIIIlIl(), f7 + f3, f7 + 0.0f);
                            tessellator.bridge$addVertexWithUV(d3 + d2, 256, border.IIIIllIIllIIIIllIllIIIlIl(), f7 + f2 + f3, f7 + 0.0f);
                            tessellator.bridge$addVertexWithUV(d3 + d2, 0.0, border.IIIIllIIllIIIIllIllIIIlIl(), f7 + f2 + f3, f7 + (float)128);
                            tessellator.bridge$addVertexWithUV(d3, 0.0, border.IIIIllIIllIIIIllIllIIIlIl(), f7 + f3, f7 + (float)128);
                            d3 += 1.0;
                            f3 += 1.5882353f * 0.31481484f;
                        }
                    }
                    if (d7 < border.IIIllIllIlIlllllllIlIlIII() + d) {
                        f3 = 0.0f;
                        d3 = d8;
                        while (d3 < d9) {
                            d2 = Math.min(1.0, d9 - d3);
                            f2 = (float)d2 * (1.6071428f * 0.31111112f);
                            tessellator.bridge$addVertexWithUV(d3, 256, border.IIIllIllIlIlllllllIlIlIII(), f7 + f3, f7 + 0.0f);
                            tessellator.bridge$addVertexWithUV(d3 + d2, 256, border.IIIllIllIlIlllllllIlIlIII(), f7 + f2 + f3, f7 + 0.0f);
                            tessellator.bridge$addVertexWithUV(d3 + d2, 0.0, border.IIIllIllIlIlllllllIlIlIII(), f7 + f2 + f3, f7 + (float)128);
                            tessellator.bridge$addVertexWithUV(d3, 0.0, border.IIIllIllIlIlllllllIlIlIII(), f7 + f3, f7 + (float)128);
                            d3 += 1.0;
                            f3 += 2.2820513f * 0.21910112f;
                        }
                    }
                    tessellator.bridge$finish();
                    GL11.glTranslated(0.0, 0.0, 0.0);
                    GL11.glEnable(2884);
                    GL11.glDisable(3008);
                    GL11.glPolygonOffset(0.0f, 0.0f);
                    GL11.glDisable(32823);
                    GL11.glEnable(3008);
                    GL11.glDisable(3042);
                    GL11.glPopMatrix();
                    GL11.glDepthMask(true);
                }
            });
        }
    }

    private static int MathHelper$ceiling_double_int(double par0) {
        int var2 = (int)par0;
        return par0 > (double)var2 ? var2 + 1 : var2;
    }

    private static int MathHelper$floor_double(double par0) {
        int var2 = (int)par0;
        return par0 < (double)var2 ? var2 - 1 : var2;
    }

    public void lIIIIlIIllIIlIIlIIIlIIllI(String string, String string2, int n, double d, double d2, double d3, double d4, boolean bl, boolean bl2) {
        this.borderList.add(new WorldBorder(this, string, string2, n, d, d2, d3, d4, bl, bl2));
    }

    public void lIIIIlIIllIIlIIlIIIlIIllI(String string, double d, double d2, double d3, double d4, int n) {
        this.borderList.stream().filter(iIIlIllIIIlllIIlIIllIlIII -> Objects.equals(WorldBorder.getPlayer(iIIlIllIIIlllIIlIIllIlIII), string) && WorldBorder.lIIIIIIIIIlIllIIllIlIIlIl(iIIlIllIIIlllIIlIIllIlIII)).findFirst().ifPresent(iIIlIllIIIlllIIlIIllIlIII -> {
            WorldBorder.lIIIIlIIllIIlIIlIIIlIIllI(iIIlIllIIIlllIIlIIllIlIII, new Vector2d(d, d2));
            WorldBorder.lIIIIIIIIIlIllIIllIlIIlIl(iIIlIllIIIlllIIlIIllIlIII, new Vector2d(d3, d4));
            WorldBorder.lIIIIlIIllIIlIIlIIIlIIllI(iIIlIllIIIlllIIlIIllIlIII, 0);
            WorldBorder.lIIIIIIIIIlIllIIllIlIIlIl(iIIlIllIIIlllIIlIIllIlIII, n);
        });
    }

    public void lIIIIlIIllIIlIIlIIIlIIllI(String string) {
        this.borderList.removeIf(border -> Objects.equals(WorldBorder.getPlayer(border), string));
    }

    public void lIIIIlIIllIIlIIlIIIlIIllI() {
        this.borderList.clear();
    }

    public List<WorldBorder> lIIIIIIIIIlIllIIllIlIIlIl() {
        return this.borderList;
    }

    static CheatBreaker lIIIIlIIllIIlIIlIIIlIIllI(WorldBorderManager cBWorldBorder) {
        return cBWorldBorder.cheatbreaker;
    }
}
 