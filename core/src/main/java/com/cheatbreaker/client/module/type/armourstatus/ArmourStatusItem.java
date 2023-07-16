package com.cheatbreaker.client.module.type.armourstatus;

import com.cheatbreaker.bridge.client.MinecraftBridge;
import com.cheatbreaker.bridge.item.ItemStackBridge;
import com.cheatbreaker.bridge.ref.Ref;
import com.cheatbreaker.main.CheatBreaker;
import com.cheatbreaker.client.ui.module.CBAnchorHelper;
import com.cheatbreaker.client.ui.module.CBGuiAnchor;
import com.cheatbreaker.client.ui.module.CBPositionEnum;
import com.cheatbreaker.client.ui.util.HudUtil;

public class ArmourStatusItem {
    public final ItemStackBridge itemStack;
    public final int lIIIIIIIIIlIllIIllIlIIlIl;
    public final int IlllIIIlIlllIllIlIIlllIlI;
    public final int IIIIllIlIIIllIlllIlllllIl;
    private int IIIIllIIllIIIIllIllIIIlIl;
    private int IlIlIIIlllIIIlIlllIlIllIl;
    private String itemDisplayName = "";
    private int IllIIIIIIIlIlIllllIIllIII;
    private String durabilityDisplayString = "";
    private int IlllIllIlIIIIlIIlIIllIIIl;
    private final boolean IlIlllIIIIllIllllIllIIlIl;
    private final MinecraftBridge mc = Ref.getMinecraft();

    public static String removeEffectsAndColors(String string) {
        return string.replaceAll("(?i)§[0-9a-fklmnor]", "");
    }

    public ArmourStatusItem(ItemStackBridge lIlIlIlIlIllllIlllIIIlIlI2, int n, int n2, int n3, boolean bl) {
        this.itemStack = lIlIlIlIlIllllIlllIIIlIlI2;
        this.lIIIIIIIIIlIllIIllIlIIlIl = n;
        this.IlllIIIlIlllIllIlIIlllIlI = n2;
        this.IIIIllIlIIIllIlllIlllllIl = n3;
        this.IlIlllIIIIllIllllIllIIlIl = bl;
        this.IlllIIIlIlllIllIlIIlllIlI();
    }

    public int lIIIIlIIllIIlIIlIIIlIIllI() {
        return this.IIIIllIIllIIIIllIllIIIlIl;
    }

    public int lIIIIIIIIIlIllIIllIlIIlIl() {
        return this.IlIlIIIlllIIIlIlllIlIllIl;
    }

    private void IlllIIIlIlllIllIlIIlllIlI() {
        int n = this.IlIlIIIlllIIIlIlllIlIllIl = ArmourStatusModule.itemName.<Boolean>value()
                ? Math.max(18, this.IlllIIIlIlllIllIlIIlllIlI)
                : Math.max(9, this.IlllIIIlIlllIllIlIIlllIlI);

        if (this.itemStack != null) {
            int n2 = 1;
            int n3 = 1;
            if ((this.IlIlllIIIIllIllllIllIIlIl && ArmourStatusModule.showArmourDamage.<Boolean>value() || !this.IlIlllIIIIllIllllIllIIlIl && ArmourStatusModule.showItemDamage.<Boolean>value()) && this.itemStack.bridge$isItemDamaged()) {
                n3 = this.itemStack.bridge$getItemDamage() + 1;
                n2 = n3 - this.itemStack.bridge$getMaxDamage();
                if (ArmourStatusModule.damageDisplayType.<String>value().equalsIgnoreCase("value")) {
                    this.durabilityDisplayString = "§" + ArmourStatusDamageComparable.getDamageColor(ArmourStatusModule.damageColors, ArmourStatusModule.damageThreshold.<String>value().equalsIgnoreCase("percent") ? n2 * 100 / n3 : n2) + n2 + (ArmourStatusModule.showMaxDamage.<Boolean>value() ? "/" + n3 : "");
                } else if (ArmourStatusModule.damageDisplayType.<String>value().equalsIgnoreCase("percent")) {
                    this.durabilityDisplayString = "§" + ArmourStatusDamageComparable.getDamageColor(ArmourStatusModule.damageColors, ArmourStatusModule.damageThreshold.<String>value().equalsIgnoreCase("percent") ? n2 * 100 / n3 : n2) + n2 * 100 / n3 + "%";
                }
            }
            this.IlllIllIlIIIIlIIlIIllIIIl = this.mc.bridge$getFontRenderer().bridge$getStringWidth(removeEffectsAndColors(this.durabilityDisplayString));
            this.IIIIllIIllIIIIllIllIIIlIl = this.IIIIllIlIIIllIlllIlllllIl + this.lIIIIIIIIIlIllIIllIlIIlIl + this.IIIIllIlIIIllIlllIlllllIl + this.IlllIllIlIIIIlIIlIIllIIIl;
            if (ArmourStatusModule.itemName.<Boolean>value()) {
                this.itemDisplayName = this.itemStack.bridge$getDisplayName();
                this.IIIIllIIllIIIIllIllIIIlIl = this.IIIIllIlIIIllIlllIlllllIl + this.lIIIIIIIIIlIllIIllIlIIlIl + this.IIIIllIlIIIllIlllIlllllIl + Math.max(this.mc.bridge$getFontRenderer().bridge$getStringWidth(removeEffectsAndColors(this.itemDisplayName)), this.IlllIllIlIIIIlIIlIIllIIIl);
            }
            this.IllIIIIIIIlIlIllllIIllIII = this.mc.bridge$getFontRenderer().bridge$getStringWidth(removeEffectsAndColors(this.itemDisplayName));
        }
    }

    public void lIIIIlIIllIIlIIlIIIlIIllI(float f, float f2) {
        Ref.getGlBridge().bridge$color(1.0f, 1.0f, 1.0f, 1.0f);
        Ref.getGlBridge().bridge$enableRescaleNormal();
        Ref.getRenderHelper().bridge$enableStandardItemLighting();
        Ref.getRenderHelper().bridge$enableGUIStandardItemLighting();
        ArmourStatusModule.renderItem.bridge$setZLevel(-10);
        CBGuiAnchor cBGuiAnchor = CheatBreaker.getInstance().moduleManager.armourStatus.getGuiAnchor();
        boolean bl = CBAnchorHelper.getHorizontalPositionEnum(cBGuiAnchor) == CBPositionEnum.RIGHT;
        if (bl) {
            ArmourStatusModule.renderItem.bridge$renderItemIntoGUI(this.mc.bridge$getFontRenderer(), this.mc.bridge$getTextureManager(), this.itemStack, (int)(f - (float)(this.lIIIIIIIIIlIllIIllIlIIlIl + this.IIIIllIlIIIllIlllIlllllIl)), (int)f2);
            HudUtil.renderItemOverlayIntoGUI(this.mc.bridge$getFontRenderer(), this.itemStack, (int)(f - (float)(this.lIIIIIIIIIlIllIIllIlIIlIl + this.IIIIllIlIIIllIlllIlllllIl)), (int)f2, ArmourStatusModule.damageOverlay.<Boolean>value(), ArmourStatusModule.itemCount.<Boolean>value());
            Ref.getRenderHelper().bridge$disableStandardItemLighting();
            Ref.getGlBridge().bridge$disableRescaleNormal();
            Ref.getGlBridge().bridge$disableBlend();
            this.mc.bridge$getFontRenderer().bridge$drawStringWithShadow(this.itemDisplayName + "§r", (int)f - (this.IIIIllIlIIIllIlllIlllllIl + this.lIIIIIIIIIlIllIIllIlIIlIl + this.IIIIllIlIIIllIlllIlllllIl) - this.IllIIIIIIIlIlIllllIIllIII, (int)f2, 0xFFFFFF);
            this.mc.bridge$getFontRenderer().bridge$drawStringWithShadow(this.durabilityDisplayString + "§r", (int)f - (this.IIIIllIlIIIllIlllIlllllIl + this.lIIIIIIIIIlIllIIllIlIIlIl + this.IIIIllIlIIIllIlllIlllllIl) - this.IlllIllIlIIIIlIIlIIllIIIl, (int)f2 + (ArmourStatusModule.itemName.<Boolean>value() ? this.IlIlIIIlllIIIlIlllIlIllIl / 2 : this.IlIlIIIlllIIIlIlllIlIllIl / 4), 0xFFFFFF);
        } else {
            ArmourStatusModule.renderItem.bridge$renderItemIntoGUI(this.mc.bridge$getFontRenderer(), this.mc.bridge$getTextureManager(), this.itemStack, (int)f, (int)f2);
            HudUtil.renderItemOverlayIntoGUI(this.mc.bridge$getFontRenderer(), this.itemStack, (int)f, (int)f2, ArmourStatusModule.damageOverlay.<Boolean>value(), ArmourStatusModule.itemCount.<Boolean>value());
            Ref.getRenderHelper().bridge$disableStandardItemLighting();
            Ref.getGlBridge().bridge$disableRescaleNormal();
            Ref.getGlBridge().bridge$disableBlend();
            this.mc.bridge$getFontRenderer().bridge$drawStringWithShadow(this.itemDisplayName + "§r", (int)f + this.lIIIIIIIIIlIllIIllIlIIlIl + this.IIIIllIlIIIllIlllIlllllIl, (int)f2, 0xFFFFFF);
            this.mc.bridge$getFontRenderer().bridge$drawStringWithShadow(this.durabilityDisplayString + "§r", (int)f + this.lIIIIIIIIIlIllIIllIlIIlIl + this.IIIIllIlIIIllIlllIlllllIl, (int)f2 + (ArmourStatusModule.itemName.<Boolean>value() ? this.IlIlIIIlllIIIlIlllIlIllIl / 2 : this.IlIlIIIlllIIIlIlllIlIllIl / 4), 0xFFFFFF);
        }
        Ref.getGlBridge().bridge$color(1.0f, 1.0f, 1.0f, 1.0f);
    }
}
