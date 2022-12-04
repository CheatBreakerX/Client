package com.cheatbreaker.client.ui.element.module;

import com.cheatbreaker.bridge.ref.Ref;
import com.cheatbreaker.bridge.util.EnumChatFormattingBridge;
import com.cheatbreaker.main.CheatBreaker;
import com.cheatbreaker.client.config.Setting;
import com.cheatbreaker.client.module.AbstractModule;
import com.cheatbreaker.client.module.type.cooldowns.CooldownRenderer;
import com.cheatbreaker.client.ui.element.AbstractModulesGuiElement;
import com.cheatbreaker.client.ui.element.AbstractScrollableElement;
import com.cheatbreaker.client.ui.module.CBModulePlaceGui;
import com.cheatbreaker.client.ui.module.CBModulesGui;
import com.cheatbreaker.client.ui.util.RenderUtil;
import com.cheatbreaker.client.ui.util.font.CBFontRenderer;
import com.cheatbreaker.client.ui.util.font.FontRegistry;

import java.util.Objects;

public class ModulePreviewElement extends AbstractModulesGuiElement {
    private final AbstractModule module;
    private final ModulesGuiButtonElement optionsButton;
    private final ModulesGuiButtonElement toggleOrHideFromHud;
    private final ModulesGuiButtonElement toggle;
    private final AbstractScrollableElement IlIlllIIIIllIllllIllIIlIl;

    public ModulePreviewElement(AbstractScrollableElement parent, AbstractModule module, float f) {
        super(f);
        this.module = module;
        this.IlIlllIIIIllIllllIllIIlIl = parent;
        CBFontRenderer optionsFontRenderer = FontRegistry.getPlayBold18px();
        CBFontRenderer hideOrToggleFontRenderer = FontRegistry.getPlayRegular14px();
        this.optionsButton = new ModulesGuiButtonElement(optionsFontRenderer, null, "Options", this.x + 4, this.y + this.height - 20, this.x + this.width - 4, this.y + this.height - 6, -12418828, f);
        this.toggleOrHideFromHud = new ModulesGuiButtonElement(hideOrToggleFontRenderer, null, module.getGuiAnchor() == null ? (module.isRenderHud() ? "Disable" : "Enable") : (module.isRenderHud() ? "Hide from HUD" : "Add to HUD"), this.x + 4, this.y + this.height - 38, this.x + this.width / 2 - 2, this.y + this.height - 24, module.isRenderHud() ? -5756117 : -13916106, f);
        this.toggleOrHideFromHud.lIIIIlIIllIIlIIlIIIlIIllI(module != CheatBreaker.getInstance().moduleManager.notifications);
        this.toggle = new ModulesGuiButtonElement(hideOrToggleFontRenderer, null, module.isEnabled() ? "Disable" : "Enable", this.x + this.width / 2 + 2, this.y + this.height - 38, this.x + this.width - 4, this.y + this.height - 24, module.isEnabled() ? -5756117 : -13916106, f);
    }

    @Override
    public void handleDrawElement(int mouseX, int mouseY, float partialTicks) {
        float f2;
        Object object;
        if (this.module.isEnabled()) {
            Ref.modified$drawRect(this.x, this.y, this.x + this.width, this.y + this.height, -13916106);
        } else {
            Ref.modified$drawRect(this.x, this.y, this.x + this.width, this.y + this.height, CheatBreaker.getInstance().globalSettings.isDarkMode() ? -14211289 : -1347374928);
        }
        CBFontRenderer playBold18px = FontRegistry.getPlayBold18px();
        Ref.getGlBridge().bridge$pushMatrix();
        int n3 = 0;
        int n4 = 0;
        if (this.module == CheatBreaker.getInstance().moduleManager.armourStatus) {
            n3 = -10;
            object = "329/329";
            f2 = Ref.getMinecraft().bridge$getFontRenderer().bridge$getStringWidth((String)object);
            Ref.getMinecraft().bridge$getFontRenderer().bridge$drawStringWithShadow((String)object, (int)((float)(this.x + 1 + this.width / 2) - f2 / 2.0f), this.y + this.height / 2 - 18, -1);
        } else if (this.module == CheatBreaker.getInstance().moduleManager.potionStatus) {
            n4 = -30;
            Ref.getMinecraft().bridge$getFontRenderer().bridge$drawStringWithShadow("Speed II", this.x + 8 + this.width / 2 - 20, this.y + this.height / 2 - 36, -1);
            Ref.getMinecraft().bridge$getFontRenderer().bridge$drawStringWithShadow("0:42", this.x + 8 + this.width / 2 - 20, this.y + this.height / 2 - 26, -1);
        } else if (this.module == CheatBreaker.getInstance().moduleManager.scoreboard) {
            Ref.modified$drawRect(this.x + 20, this.y + this.height / 2f - 44, this.x + this.width - 20, this.y + this.height / 2 - 6, 0x6F000000);
            Ref.getMinecraft().bridge$getFontRenderer().bridge$drawString("Score", this.x + this.width / 2, this.y + this.height / 2 - 40, -1);
            Ref.getMinecraft().bridge$getFontRenderer().bridge$drawStringWithShadow("Steve", this.x + 24, this.y + this.height / 2 - 28, -1);
            Ref.getMinecraft().bridge$getFontRenderer().bridge$drawStringWithShadow("Alex", this.x + 24, this.y + this.height / 2 - 18, -1);
            Ref.getMinecraft().bridge$getFontRenderer().bridge$drawString(EnumChatFormattingBridge.RED + "0", this.x + this.width - 26, this.y + this.height / 2 - 18, -1);
            Ref.getMinecraft().bridge$getFontRenderer().bridge$drawString(EnumChatFormattingBridge.RED + "1", this.x + this.width - 26, this.y + this.height / 2 - 28, -1);
        }
        if (this.module == CheatBreaker.getInstance().moduleManager.cooldowns) {
            object = new CooldownRenderer("EnderPearl", 368, 9000L);
            ((CooldownRenderer)object).lIIIIlIIllIIlIIlIIIlIIllI(CheatBreaker.getInstance().moduleManager.cooldowns.colorTheme, this.x + this.width / 2 - 18, this.y + this.height / 2 - 26 - 18, -1);
        } else if ((this.module.getPreviewType() == null || this.module.getPreviewType() == AbstractModule.PreviewType.LABEL) && this.module != CheatBreaker.getInstance().moduleManager.scoreboard) {
            object = "";
            if (this.module.getPreviewType() == null) {
                f2 = 2.0f;
                for (String string : this.module.getName().split(" ")) {
                    String string2 = string.substring(0, 1);
                    object = (String)object + (Objects.equals(object, "") ? string2 : string2.toLowerCase());
                }
            } else {
                f2 = this.module.getPreviewLabelSize();
                object = this.module.getPreviewLabel();
            }
            Ref.getGlBridge().bridge$scale(f2, f2, f2);
            float f3 = (float)Ref.getMinecraft().bridge$getFontRenderer().bridge$getStringWidth((String)object) * f2;
            if (this.module.getPreviewType() == null) {
                Ref.getMinecraft().bridge$getFontRenderer().bridge$drawString((String)object, (int)(((float)(this.x + 1 + this.width / 2) - f3 / 2.0f) / f2), (int)((float)(this.y + this.height / 2 - 32) / f2), -13750738);
            } else {
                Ref.getMinecraft().bridge$getFontRenderer().bridge$drawStringWithShadow((String)object, (int)(((float)(this.x + 1 + this.width / 2) - f3 / 2.0f) / f2), (int)((float)(this.y + this.height / 2 - 32) / f2), -1);
            }
        } else if (this.module.getPreviewType() == AbstractModule.PreviewType.ICON) {
            float f4 = this.module.getPreviewIconWidth();
            f2 = this.module.getPreviewIconHeight();
            Ref.getGlBridge().bridge$color(1.0f, 1.0f, 1.0f, 1.0f);
            RenderUtil.lIIIIlIIllIIlIIlIIIlIIllI(this.module.getPreviewIcon(), (float)(this.x + this.width / 2) - f4 / 2.0f + (float)n4, (float)(this.y + n3 + this.height / 2 - 26) - f2 / 2.0f, f4, f2);
        }
        Ref.getGlBridge().bridge$popMatrix();
        float moduleNameOffset = this.y + this.height / 2f;
        playBold18px.drawCenteredString(this.module.getName(), (float)(this.x + this.width / 2) - 1.0681819f * 0.46808508f, moduleNameOffset + 1, 0x5F000000);
        playBold18px.drawCenteredString(this.module.getName(), (float)(this.x + this.width / 2) - 1.125f * 1.3333334f, moduleNameOffset, -1);
        this.toggle.displayString = this.module.isEnabled() ? "Disable" : "Enable";
        this.toggle.yOffset = this.yOffset;
        int n5 = this.toggle.lIIIIlIIllIIlIIlIIIlIIllI = this.module.isEnabled() ? -5756117 : -13916106;
        this.toggleOrHideFromHud.displayString = this.module.getGuiAnchor() == null ? (this.module.isRenderHud() && this.module.isEnabled() ? "Disable" : "Enable") : (this.module.isRenderHud() && this.module.isEnabled() ? "Hide from HUD" : "Add to HUD");
        this.toggleOrHideFromHud.lIIIIlIIllIIlIIlIIIlIIllI = this.module.isRenderHud() && this.module.isEnabled() ? -5756117 : -13916106;
        this.optionsButton.setDimensions(this.x + 4, this.y + this.height - 20, this.width - 8, 16);
        this.optionsButton.yOffset = this.yOffset;
        this.optionsButton.handleDrawElement(mouseX, mouseY, partialTicks);
        this.toggleOrHideFromHud.setDimensions(this.x + 4, this.y + this.height - 38, this.module.isEditable ? this.width - 8 : this.width / 2 + 2, this.y + this.height - 24 - (this.y + this.height - 38));
        this.toggleOrHideFromHud.yOffset = this.yOffset;
        this.toggleOrHideFromHud.handleDrawElement(mouseX, mouseY, partialTicks);
        if (!this.module.isEditable) {
            this.toggle.setDimensions(this.x + this.width / 2 + 8, this.y + this.height - 38, this.width / 2 - 12, this.y + this.height - 24 - (this.y + this.height - 38));
            this.toggle.handleDrawElement(mouseX, mouseY, partialTicks);
        }
    }

    @Override
    public void handleMouseClick(int mouseX, int mouseY, int button) {
        if (this.optionsButton.isMouseInside(mouseX, mouseY)) {
            Ref.getMinecraft().bridge$getSoundHandler().bridge$playSound(Ref.getInstanceCreator().createSoundFromPSR(Ref.getInstanceCreator().createResourceLocation("gui.button.press"), 1.0f));
            ((ModuleListElement) CBModulesGui.instance.settingsElement).llIlIIIlIIIIlIlllIlIIIIll = false;
            ((ModuleListElement)CBModulesGui.instance.settingsElement).scrollable = this.IlIlllIIIIllIllllIllIIlIl;
            ((ModuleListElement)CBModulesGui.instance.settingsElement).module = this.module;
            CBModulesGui.instance.currentScrollableElement = CBModulesGui.instance.settingsElement;
        } else if (!this.module.isEditable && this.toggle.isMouseInside(mouseX, mouseY)) {
            Ref.getMinecraft().bridge$getSoundHandler().bridge$playSound(Ref.getInstanceCreator().createSoundFromPSR(Ref.getInstanceCreator().createResourceLocation("gui.button.press"), 1.0f));
            this.module.setState(!this.module.isEnabled());
            this.toggle.displayString = this.module.isEnabled() ? "Disable" : "Enable";
            int n4 = this.toggle.lIIIIlIIllIIlIIlIIIlIIllI = this.module.isEnabled() ? -5756117 : -13916106;
            if (this.module.isEnabled()) {
                this.lIIIIIIIIIlIllIIllIlIIlIl();
                this.module.setState(true);
            }
        } else if (this.toggleOrHideFromHud.IlllIllIlIIIIlIIlIIllIIIl && this.toggleOrHideFromHud.isMouseInside(mouseX, mouseY)) {
            Ref.getMinecraft().bridge$getSoundHandler().bridge$playSound(Ref.getInstanceCreator().createSoundFromPSR(Ref.getInstanceCreator().createResourceLocation("gui.button.press"), 1.0f));
            if (!this.module.isEnabled()) {
                this.module.setRenderHud(true);
                this.lIIIIIIIIIlIllIIllIlIIlIl();
                if (this.module.getGuiAnchor() == null) {
                    this.module.setState(true);
                } else {
                    Ref.getMinecraft().bridge$displayGuiScreen(new CBModulePlaceGui(CBModulesGui.instance, this.module));
                }
            } else {
                this.module.setRenderHud(!this.module.isRenderHud());
                if (this.module.isRenderHud()) {
                    this.lIIIIIIIIIlIllIIllIlIIlIl();
                    if (this.module.getGuiAnchor() == null) {
                        this.module.setState(true);
                    } else {
                        Ref.getMinecraft().bridge$displayGuiScreen(new CBModulePlaceGui(CBModulesGui.instance, this.module));
                    }
                } else if (this.module.isEditable && this.module.isEnabled()) {
                    this.module.setState(false);
                }
            }
            this.toggleOrHideFromHud.displayString = this.module.getGuiAnchor() == null ? (this.module.isRenderHud() && this.module.isEnabled() ? "Disable" : "Enable") : (this.module.isRenderHud() && this.module.isEnabled() ? "Hide from HUD" : "Add to HUD");
            this.toggleOrHideFromHud.lIIIIlIIllIIlIIlIIIlIIllI = this.module.isRenderHud() && this.module.isEnabled() ? -5756117 : -13916106;
        }
    }

    private void lIIIIIIIIIlIllIIllIlIIlIl() {
        if (this.module == CheatBreaker.getInstance().moduleManager.llIIlllIIIIlllIllIlIlllIl) {
            return;
        }
        for (Setting cBSetting : this.module.getSettingsList()) {
            if (cBSetting.getType() != Setting.Type.INTEGER || !cBSetting.getLabel().toLowerCase().contains("color") || cBSetting.getLabel().toLowerCase().contains("background") || cBSetting.getLabel().toLowerCase().contains("pressed")) continue;
            Ref.getMinecraft().bridge$getSoundHandler().bridge$playSound(Ref.getInstanceCreator().createSoundFromPSR(Ref.getInstanceCreator().createResourceLocation("gui.button.press"), 1.0f));
            cBSetting.setValue(CheatBreaker.getInstance().globalSettings.defaultColor.getValue());
        }
    }
}
