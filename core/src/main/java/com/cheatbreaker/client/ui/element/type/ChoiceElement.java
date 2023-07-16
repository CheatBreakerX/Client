package com.cheatbreaker.client.ui.element.type;

import com.cheatbreaker.bridge.ref.Ref;
import com.cheatbreaker.bridge.util.ResourceLocationBridge;
import com.cheatbreaker.main.CheatBreaker;
import com.cheatbreaker.client.config.Setting;
import com.cheatbreaker.client.ui.element.AbstractModulesGuiElement;
import com.cheatbreaker.client.ui.module.CBModulesGui;
import com.cheatbreaker.client.ui.util.RenderUtil;
import com.cheatbreaker.client.ui.util.font.FontRegistry;

public class ChoiceElement
        extends AbstractModulesGuiElement {
    private final Setting setting;
    private final ResourceLocationBridge leftIcon = Ref.getInstanceCreator().createResourceLocation("client/icons/left.png");
    private final ResourceLocationBridge rightIcon = Ref.getInstanceCreator().createResourceLocation("client/icons/right.png");
    private int IlllIllIlIIIIlIIlIIllIIIl = 0;
    private float IlIlllIIIIllIllllIllIIlIl = 0.0f;
    private String llIIlllIIIIlllIllIlIlllIl;

    public ChoiceElement(Setting cBSetting, float f) {
        super(f);
        this.setting = cBSetting;
        this.height = 12;
    }

    @Override
    public void handleDrawElement(int mouseX, int mouseY, float partialTicks) {
        boolean leftHovered = (float) mouseX > (this.x + this.width - 92f) * this.scale && (float) mouseX < (this.x + this.width - 48f) * this.scale && (float) mouseY > (float)(this.y + this.yOffset) * this.scale && (float) mouseY < (this.y + 14f + this.yOffset) * this.scale;
        boolean rightHovered = (float) mouseX > (this.x + this.width - 48f) * this.scale && (float) mouseX < (this.x + this.width - 10f) * this.scale && (float) mouseY > (float)(this.y + this.yOffset) * this.scale && (float) mouseY < (this.y + 14f + this.yOffset) * this.scale;
        FontRegistry.getUbuntuMedium16px().drawString(this.setting.getLabel().toUpperCase(), this.x + 10f, this.y + 4f, CheatBreaker.getInstance().globalSettings.isDarkMode() ? -1 : (leftHovered || rightHovered ? -1090519040 : -1895825408));
        boolean bl3 = this.setting.getLabel().toLowerCase().endsWith("color");
        String value = this.setting.value().toString();
        String[] split;
        if ((split = value.split(" ")).length > 1) {
            value = split[1].substring(0, Math.min(split[1].length(), 5)).trim() + "...";
        }
        if (!bl3) {
            if (this.IlllIllIlIIIIlIIlIIllIIIl == 0) {
                FontRegistry.getUbuntuMedium16px().drawCenteredString(value, this.x + this.width - 48, this.y + 4, CheatBreaker.getInstance().globalSettings.isDarkMode() ? -1 : -1895825408);
            } else {
                boolean bl4 = this.IlllIllIlIIIIlIIlIIllIIIl == 1;
                FontRegistry.getUbuntuMedium16px().drawCenteredString(value, (float)(this.x + this.width - 48) - (bl4 ? -this.IlIlllIIIIllIllllIllIIlIl : this.IlIlllIIIIllIllllIllIIlIl), this.y + 4, CheatBreaker.getInstance().globalSettings.isDarkMode() ? -1 : -1895825408);
                if (bl4) {
                    FontRegistry.getUbuntuMedium16px().drawCenteredString(value, (float)(this.x + this.width - 98) + this.IlIlllIIIIllIllllIllIIlIl, this.y + 4, CheatBreaker.getInstance().globalSettings.isDarkMode() ? -1 : -1895825408);
                } else {
                    FontRegistry.getUbuntuMedium16px().drawCenteredString(value, (float)(this.x + this.width + 2) - this.IlIlllIIIIllIllllIllIIlIl, this.y + 4, CheatBreaker.getInstance().globalSettings.isDarkMode() ? -1 : -1895825408);
                }
                if (this.IlIlllIIIIllIllllIllIIlIl >= (float)50) {
                    this.IlllIllIlIIIIlIIlIIllIIIl = 0;
                    this.IlIlllIIIIllIllllIllIIlIl = 0.0f;
                } else {
                    float f2 = CBModulesGui.getSmoothFloat((float)50 + this.IlIlllIIIIllIllllIllIIlIl * (float)15);
                    this.IlIlllIIIIllIllllIllIIlIl = this.IlIlllIIIIllIllllIllIIlIl + f2 >= (float)50 ? (float)50 : (this.IlIlllIIIIllIllllIllIIlIl += f2);
                }
                int thisRectColor = CheatBreaker.getInstance().globalSettings.isDarkMode() ? -13619151 : -723724;
                Ref.modified$drawRect(this.x + this.width - 130, this.y + 2, this.x + this.width - 72, this.y + 12, thisRectColor); // -723724
                Ref.modified$drawRect(this.x + this.width - 22, this.y + 2, this.x + this.width + 4, this.y + 12, thisRectColor); // -723724
            }
        } else if (this.IlllIllIlIIIIlIIlIIllIIIl == 0) {
            float f3 = FontRegistry.getUbuntuMedium16px().getStringWidth(value);
            FontRegistry.getUbuntuMedium16px().drawCenteredString(value, (float)(this.x + this.width) - 44.738373f * 1.0617284f - f3 / 2.0f, (float)this.y + 4, -16777216);
            FontRegistry.getUbuntuMedium16px().drawCenteredString("\u00a7" + value + value, (float)(this.x + this.width - 48) - f3 / 2.0f, (float)(this.y + 4), -16777216);
        } else {
            boolean bl5 = this.IlllIllIlIIIIlIIlIIllIIIl == 1;
            FontRegistry.getUbuntuMedium16px().drawCenteredString(this.llIIlllIIIIlllIllIlIlllIl, (float)(this.x + this.width - 48) - (bl5 ? -this.IlIlllIIIIllIllllIllIIlIl : this.IlIlllIIIIllIllllIllIIlIl), this.y + 4, -1895825408);
            float f4 = FontRegistry.getUbuntuMedium16px().getStringWidth(value);
            if (bl5) {
                FontRegistry.getUbuntuMedium16px().drawCenteredString(value, (float)(this.x + this.width) - 110.21739f * 0.88461536f - f4 / 2.0f + this.IlIlllIIIIllIllllIllIIlIl, (float)this.y + 4, -16777216);
                FontRegistry.getUbuntuMedium16px().drawCenteredString("\u00a7" + value + value, (float)(this.x + this.width - 98) - f4 / 2.0f + this.IlIlllIIIIllIllllIllIIlIl, (float)(this.y + 4), -16777216);
            } else {
                FontRegistry.getUbuntuMedium16px().drawCenteredString(value, (float)(this.x + this.width) - 2.6296296f * 0.57042253f - f4 / 2.0f - this.IlIlllIIIIllIllllIllIIlIl, (float)this.y + 4, -16777216);
                FontRegistry.getUbuntuMedium16px().drawCenteredString("\u00a7" + value + value, (float)(this.x + this.width - 2) - f4 / 2.0f - this.IlIlllIIIIllIllllIllIIlIl, (float)(this.y + 4), -16777216);
            }
            if (this.IlIlllIIIIllIllllIllIIlIl >= (float)50) {
                this.IlllIllIlIIIIlIIlIIllIIIl = 0;
                this.IlIlllIIIIllIllllIllIIlIl = 0.0f;
            } else {
                float f5 = CBModulesGui.getSmoothFloat((float)50 + this.IlIlllIIIIllIllllIllIIlIl * (float)15);
                this.IlIlllIIIIllIllllIllIIlIl = this.IlIlllIIIIllIllllIllIIlIl + f5 >= (float)50 ? (float)50 : (this.IlIlllIIIIllIllllIllIIlIl += f5);
            }
            int thisRectColor = CheatBreaker.getInstance().globalSettings.isDarkMode() ? -13619151 : -723724;
            Ref.modified$drawRect(this.x + this.width - 130, this.y + 2, this.x + this.width - 72, this.y + 12, thisRectColor);
            Ref.modified$drawRect(this.x + this.width - 22, this.y + 2, this.x + this.width + 4, this.y + 12, thisRectColor);
        }
        boolean dark = CheatBreaker.getInstance().getGlobalSettings().isDarkMode();
        float alphaLeft = dark ? (leftHovered ? 0.7174193f : 1.0f) : (leftHovered ? 0.74000007f * 1.081081f : 0.288f * 1.5625f);
        float alphaRight = dark ? (rightHovered ? 0.7174193f : 1.0f) : (rightHovered ? 0.74000007f * 1.081081f : 0.288f * 1.5625f);
        Ref.getGlBridge().bridge$color(dark ? 1f : 0f, dark ? 1f : 0f, dark ? 1f : 0f, alphaLeft);
        RenderUtil.drawIcon(this.leftIcon, (float)4, (float)(this.x + this.width - 82), (float)(this.y + 4));
        Ref.getGlBridge().bridge$color(dark ? 1f : 0f, dark ? 1f : 0f, dark ? 1f : 0f, alphaRight);
        RenderUtil.drawIcon(this.rightIcon, (float)4, (float)(this.x + this.width - 22), (float)(this.y + 4));
    }

    @Override
    public void handleMouseClick(int mouseX, int mouseY, int button) {
        boolean leftHovered = (float) mouseX > (float) (this.x + this.width - 92) * this.scale && (float) mouseX < (float) (this.x + this.width - 48) * this.scale && (float) mouseY > (float) (this.y + this.yOffset) * this.scale && (float) mouseY < (float) (this.y + 14 + this.yOffset) * this.scale;
        boolean rightHovered = (float) mouseX > (float)(this.x + this.width - 48) * this.scale && (float) mouseX < (float)(this.x + this.width - 10) * this.scale && (float) mouseY > (float)(this.y + this.yOffset) * this.scale && (float) mouseY < (float)(this.y + 14 + this.yOffset) * this.scale;
        if ((leftHovered || rightHovered) && this.IlllIllIlIIIIlIIlIIllIIIl == 0) {
            Ref.getMinecraft().bridge$getSoundHandler().bridge$playSound(Ref.getInstanceCreator().createSoundFromPSR(Ref.getInstanceCreator().createResourceLocation("gui.button.press"), 1.0f));
            for (int i = 0; i < this.setting.getAcceptedValues().length; ++i) {
                if (!((String[])this.setting.getAcceptedValues())[i].toLowerCase()
                        .equalsIgnoreCase(this.setting.value())) {
                    continue;
                }
                this.llIIlllIIIIlllIllIlIlllIl = this.setting.value();
                if (rightHovered) {
                    if (i + 1 >= this.setting.getAcceptedValues().length) {
                        this.IlllIllIlIIIIlIIlIIllIIIl = 2;
                        this.setting.setValue(((String[])this.setting.getAcceptedValues())[0]);
                        break;
                    }
                    this.IlllIllIlIIIIlIIlIIllIIIl = 2;
                    this.setting.setValue(((String[])this.setting.getAcceptedValues())[i + 1]);
                    break;
                }
                if (i - 1 < 0) {
                    this.IlllIllIlIIIIlIIlIIllIIIl = 1;
                    this.setting.setValue(((String[])this.setting.getAcceptedValues())[this.setting.getAcceptedValues().length - 1]);
                    break;
                }
                this.IlllIllIlIIIIlIIlIIllIIIl = 1;
                this.setting.setValue(((String[])this.setting.getAcceptedValues())[i - 1]);
                break;
            }
            if (this.setting == CheatBreaker.getInstance().globalSettings.clearGlass) {
                Ref.getMinecraft().bridge$getRenderGlobal().bridge$loadRenderers();
            }
        }
    }
}