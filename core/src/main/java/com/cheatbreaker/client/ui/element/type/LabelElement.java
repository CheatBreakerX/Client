package com.cheatbreaker.client.ui.element.type;

import com.cheatbreaker.bridge.ref.Ref;
import com.cheatbreaker.main.CheatBreaker;
import com.cheatbreaker.client.config.Setting;
import com.cheatbreaker.client.ui.element.AbstractModulesGuiElement;
import com.cheatbreaker.client.ui.util.font.FontRegistry;

public class LabelElement
        extends AbstractModulesGuiElement {
    private final Setting settingObject;

    public LabelElement(Setting cBSetting, float f) {
        super(f);
        this.settingObject = cBSetting;
        this.height = 12;
    }

    @Override
    public void handleDrawElement(int mouseX, int mouseY, float partialTicks) {
        FontRegistry.getUbuntuMedium16px().drawString(this.settingObject.<String>value().toUpperCase(),
                this.x + 2f, this.y + 2f,
                CheatBreaker.getInstance().globalSettings.isDarkMode() ? -1 : 0x6F000000);
        Ref.modified$drawRect(this.x + 2f, this.y + this.height - 1f, this.x + this.width / 2f - 20f,
                this.y + this.height,
                CheatBreaker.getInstance().globalSettings.isDarkMode() ? -14211288 : 0x1F2F2F2F);
    }

    @Override
    public void handleMouseClick(int mouseX, int mouseY, int button) {
    }
}