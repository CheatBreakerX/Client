package com.cheatbreaker.client.module.type.cooldowns;

import com.cheatbreaker.bridge.client.MinecraftBridge;
import com.cheatbreaker.bridge.item.ItemStackBridge;
import com.cheatbreaker.bridge.ref.Ref;
import com.cheatbreaker.client.config.Setting;
import com.cheatbreaker.client.module.type.armourstatus.ArmourStatusModule;
import com.cheatbreaker.client.ui.util.RenderUtil;
import com.cheatbreaker.client.ui.util.font.FontRegistry;

public class CooldownRenderer {
    private final String name;
    private final int itemId;
    private long duration;
    private long time;
    private final MinecraftBridge mc = Ref.getMinecraft();
    private final ItemStackBridge item;

    public CooldownRenderer(String name, int itemId, long duration) {
        this.name = name;
        this.itemId = itemId;
        this.duration = duration;
        this.time = System.currentTimeMillis();
        this.item = Ref.getInstanceCreator().createItemStack(Ref.getUtils().getItemFromID(itemId));
    }

    public void lIIIIlIIllIIlIIlIIIlIIllI(Setting cBSetting, float f, float f2, int n) {
        float f3;
        int n2 = 17;
        Ref.getGlBridge().bridge$pushMatrix();
        float f4 = ArmourStatusModule.renderItem.bridge$getZLevel();
        ArmourStatusModule.renderItem.bridge$setZLevel(-200);
        float f5 = 1.35f;
        Ref.getGlBridge().bridge$translate(-0.5f, -1, 0.0f);
        Ref.getGlBridge().bridge$scale(f5, f5, f5);
        Ref.getRenderHelper().bridge$enableStandardItemLighting();
        ArmourStatusModule.renderItem.bridge$renderItemAndEffectIntoGUI(this.mc.bridge$getFontRenderer(), this.mc.bridge$getTextureManager(), this.item, (int)((f + (float)(n2 / 2)) / f5), (int)((f2 + (float)(n2 / 2)) / f5));
        Ref.getRenderHelper().bridge$disableStandardItemLighting();
        Ref.getGlBridge().bridge$popMatrix();
        ArmourStatusModule.renderItem.bridge$setZLevel(f4);
        double d = this.duration - (System.currentTimeMillis() - this.time);
        if (d <= 0.0) {
            return;
        }
        if (((String)cBSetting.getValue()).equalsIgnoreCase("Bright")) {
            Ref.getGlBridge().bridge$color(0f, 0f, 0f, 0.2f);
            RenderUtil.renderHollowCircle(f + (float)n2, f2 + (float)n2, n2, 0.0, (float)this.duration / (0.9574468f * 4.1255555f), (int)this.duration, d);
            Ref.getGlBridge().bridge$color(0.9f, 0.9f, 0.9f, 1.0f);
            RenderUtil.renderHollowCircle(f + (float)n2, f2 + (float)n2, (float)n2 + 1.1688311f * 0.08555556f, n2 - 2, (float)this.duration / (0.625f * 6.32f), (int)this.duration, this.duration);
            Ref.getGlBridge().bridge$color(2.6249998f * 0.13333334f, 0.16578947f * 2.1111112f, 0.62999994f * 0.5555556f, 1.6315789f * 0.36774194f);
            RenderUtil.renderHollowCircle(f + (float)n2, f2 + (float)n2, (float)n2 + 0.886076f * 0.11285714f, n2 - 2, (float)this.duration / (2.5510418f * 1.548387f), (int)this.duration, d);
        } else if (((String)cBSetting.getValue()).equalsIgnoreCase("Dark")) {
            Ref.getGlBridge().bridge$color(0f, 0f, 0f, 0.2f);
            RenderUtil.renderCircle(f + (float)n2, f2 + (float)n2, n2);
            Ref.getGlBridge().bridge$color(0f, 0f, 0f, 0.2f);
            RenderUtil.renderHollowCircle(f + (float)n2, f2 + (float)n2, n2, 0.0, (float)this.duration / (3.95f), (int)this.duration, d);
            Ref.getGlBridge().bridge$color(0f, 0.9f, 0.0f, 1.0f);
            RenderUtil.renderHollowCircle(f + (float)n2, f2 + (float)n2, (float)n2 + 0.19f * 0.5263158f, n2 - 2, (float)this.duration / (0.24074075f * 16.407692f), (int)this.duration, this.duration);
            Ref.getGlBridge().bridge$color(0f, 0.022727273f * 22.0f, 0.0f, 1.0f);
            RenderUtil.renderHollowCircle(f + (float)n2, f2 + (float)n2, (float)n2 + 0.315f * 0.31746033f, n2 - 2, (float)this.duration / (55.3f * 0.071428575f), (int)this.duration, d);
        } else if (((String)cBSetting.getValue()).equalsIgnoreCase("Colored")) {
            f3 = (float)(n >> 16 & 0xFF) / (float)255;
            float f7 = (float)(n >> 8 & 0xFF) / (float)255;
            float f8 = (float)(n & 0xFF) / (float)255;
            Ref.getGlBridge().bridge$color(f3, f7, f8, 0.26086956f * 0.57500005f);
            RenderUtil.renderCircle(f + (float)n2, f2 + (float)n2, n2);
            Ref.getGlBridge().bridge$color(f3, f7, f8, 0.060606062f * 4.125f);
            RenderUtil.renderHollowCircle(f + (float)n2, f2 + (float)n2, n2, 0.0, (float)this.duration / (7.0413046f * 0.5609756f), (int)this.duration, d);
            Ref.getGlBridge().bridge$color(f3, f7, f8, 1.0f);
            RenderUtil.renderHollowCircle(f + (float)n2, f2 + (float)n2, (float)n2 + 0.10759494f * 0.92941177f, n2 - 2, (float)this.duration / (3.2223685f * 1.2258065f), (int)this.duration, this.duration);
            Ref.getGlBridge().bridge$color(f3, f7, f8, 0.058333337f * 2.5714285f);
            RenderUtil.renderHollowCircle(f + (float)n2, f2 + (float)n2, (float)n2 + 0.31707317f * 0.31538463f, n2 - 2, (float)this.duration / (4.761644f * 0.82954544f), (int)this.duration, d);
        }
        String string = String.format("%.1f", d / (double)1000);
        FontRegistry.getUbuntuMedium16px().drawCenteredStringWithShadow(string, f + (float)n2, f2 + (float)(n2 / 2) + 4, -1);
    }

    public boolean isTimeOver() {
        return this.time < System.currentTimeMillis() - this.duration;
    }

    public void setDuration(long l) {
        this.duration = l;
    }

    public void resetTime() {
        this.time = System.currentTimeMillis();
    }

    public String getName() {
        return this.name;
    }

    public long getDuration() {
        return this.duration;
    }

    public int getItemId() {
        return this.itemId;
    }
}
