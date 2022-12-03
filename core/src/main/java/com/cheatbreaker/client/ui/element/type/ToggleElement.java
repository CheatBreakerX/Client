package com.cheatbreaker.client.ui.element.type;

import com.cheatbreaker.bridge.ref.Ref;
import com.cheatbreaker.bridge.util.ResourceLocationBridge;
import com.cheatbreaker.main.CheatBreaker;
import com.cheatbreaker.client.config.Setting;
import com.cheatbreaker.client.ui.element.AbstractModulesGuiElement;
import com.cheatbreaker.client.ui.module.CBModulesGui;
import com.cheatbreaker.client.ui.util.RenderUtil;
import com.cheatbreaker.client.ui.util.font.FontRegistry;

public class ToggleElement
        extends AbstractModulesGuiElement {
    private Setting setting;
    private ResourceLocationBridge IllIIIIIIIlIlIllllIIllIII = Ref.getInstanceCreator().createResourceLocation("client/icons/left.png");
    private ResourceLocationBridge lIIIIllIIlIlIllIIIlIllIlI = Ref.getInstanceCreator().createResourceLocation("client/icons/right.png");
    private int IlllIllIlIIIIlIIlIIllIIIl = 0;
    private float IlIlllIIIIllIllllIllIIlIl = 0.0f;
    private String displayString;

    public ToggleElement(Setting cBSetting, float f) {
        super(f);
        this.setting = cBSetting;
        this.height = 12;
    }

    @Override
    public void handleDrawElement(int mouseX, int mouseY, float partialTicks) {
        boolean bl = (float) mouseX > (float) (this.x + this.width - 48) * this.scale && (float) mouseX < (float) (this.x + this.width - 10) * this.scale && (float) mouseY > (float) (this.y + this.yOffset) * this.scale && (float) mouseY < (float) (this.y + 10 + this.yOffset) * this.scale;
        boolean bl2 = (float) mouseX > (float) (this.x + this.width - 92) * this.scale && (float) mouseX < (float) (this.x + this.width - 48) * this.scale && (float) mouseY > (float) (this.y + this.yOffset) * this.scale && (float) mouseY < (float) (this.y + 10 + this.yOffset) * this.scale;
        boolean dark = CheatBreaker.getInstance().globalSettings.isDarkMode();
        FontRegistry.getUbuntuMedium16px().drawString(this.setting.getLabel().toUpperCase(), this.x + 10, (float) (this.y + 2), dark ? -1 : (bl2 || bl ? -1090519040 : -1895825408));
        if (this.IlllIllIlIIIIlIIlIIllIIIl == 0) {
            FontRegistry.getUbuntuMedium16px().drawCenteredString((Boolean) this.setting.getValue() ? "ON" : "OFF", this.x + this.width - 48, this.y + 2, dark ? -1 : -1895825408);
        } else {
            boolean bl3 = this.IlllIllIlIIIIlIIlIIllIIIl == 1;
            FontRegistry.getUbuntuMedium16px().drawCenteredString(this.displayString, (float) (this.x + this.width - 48) - (bl3 ? -this.IlIlllIIIIllIllllIllIIlIl : this.IlIlllIIIIllIllllIllIIlIl), this.y + 2, dark ? -1 : -1895825408);
            if (bl3) {
                FontRegistry.getUbuntuMedium16px().drawCenteredString((Boolean) this.setting.getValue() ? "ON" : "OFF", (float) (this.x + this.width - 98) + this.IlIlllIIIIllIllllIllIIlIl, this.y + 2, dark ? -1 : -1895825408);
            } else {
                FontRegistry.getUbuntuMedium16px().drawCenteredString((Boolean) this.setting.getValue() ? "ON" : "OFF", (float) (this.x + this.width + 2) - this.IlIlllIIIIllIllllIllIIlIl, this.y + 2, dark ? -1 : -1895825408);
            }
            if (this.IlIlllIIIIllIllllIllIIlIl >= (float) 50) {
                this.IlllIllIlIIIIlIIlIIllIIIl = 0;
                this.IlIlllIIIIllIllllIllIIlIl = 0.0f;
            } else {
                float f2 = CBModulesGui.getSmoothFloat((float) 50 + this.IlIlllIIIIllIllllIllIIlIl * (float) 15);
                this.IlIlllIIIIllIllllIllIIlIl = this.IlIlllIIIIllIllllIllIIlIl + f2 >= (float) 50 ? (float) 50 : (this.IlIlllIIIIllIllllIllIIlIl += f2);
            }
            Ref.modified$drawRect(this.x + this.width - 130, this.y + 2, this.x + this.width - 72, this.y + 12, dark ? -13619151 : -723724);
            Ref.modified$drawRect(this.x + this.width - 22, this.y + 2, this.x + this.width + 4, this.y + 12, dark ? -13619151 : -723724);
        }
        float alpha = dark ? (bl2 ? 0.7174193f : 1.0f) : (bl2 ? 0.74000007f * 1.081081f : 0.288f * 1.5625f);
        Ref.getGlBridge().bridge$color(dark ? 1f : 0f, dark ? 1f : 0f, dark ? 1f : 0f, alpha);
        RenderUtil.drawIcon(this.IllIIIIIIIlIlIllllIIllIII, (float) 4, (float) (this.x + this.width - 82), (float) (this.y + 3));
        Ref.getGlBridge().bridge$color(dark ? 1f : 0f, dark ? 1f : 0f, dark ? 1f : 0f, alpha);
        RenderUtil.drawIcon(this.lIIIIllIIlIlIllIIIlIllIlI, (float) 4, (float) (this.x + this.width - 22), (float) (this.y + 3));
    }

    @Override
    public void handleMouseClick(int mouseX, int mouseY, int button) {
        boolean bl;
        boolean bl2 = (float) mouseX > (float) (this.x + this.width - 48) * this.scale && (float) mouseX < (float) (this.x + this.width - 10) * this.scale && (float) mouseY > (float) (this.y + this.yOffset) * this.scale && (float) mouseY < (float) (this.y + 10 + this.yOffset) * this.scale;
        bl = (float) mouseX > (float) (this.x + this.width - 92) * this.scale && (float) mouseX < (float) (this.x + this.width - 48) * this.scale && (float) mouseY > (float) (this.y + this.yOffset) * this.scale && (float) mouseY < (float) (this.y + 10 + this.yOffset) * this.scale;
        if ((bl || bl2) && this.IlllIllIlIIIIlIIlIIllIIIl == 0) {
            this.IlllIllIlIIIIlIIlIIllIIIl = bl ? 1 : 2;
            this.IlIlllIIIIllIllllIllIIlIl = 0.0f;
            this.displayString = (Boolean) this.setting.getValue() ? "ON" : "OFF";
            Ref.getMinecraft().bridge$getSoundHandler().bridge$playSound(Ref.getInstanceCreator().createSoundFromPSR(Ref.getInstanceCreator().createResourceLocation("gui.button.press"), 1.0f));
            this.setting.setValue(!((Boolean) this.setting.getValue()));
            if (this.setting == CheatBreaker.getInstance().moduleManager.keyStrokes.replaceNamesWithArrows) {
                CheatBreaker.getInstance().moduleManager.keyStrokes.initialize();
            } else if (this.setting == CheatBreaker.getInstance().globalSettings.enableTeamView && !(Boolean) CheatBreaker.getInstance().globalSettings.enableTeamView.getValue()) {
                CheatBreaker.getInstance().globalSettings.enableTeamView.setValue(false);
            }
        }
    }
}