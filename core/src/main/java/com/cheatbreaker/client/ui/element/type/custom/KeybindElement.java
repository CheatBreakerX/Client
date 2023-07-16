package com.cheatbreaker.client.ui.element.type.custom;

import com.cheatbreaker.bridge.ref.Ref;
import com.cheatbreaker.client.config.Setting;
import com.cheatbreaker.client.ui.element.AbstractModulesGuiElement;
import com.cheatbreaker.client.ui.element.module.ModulesGuiButtonElement;
import com.cheatbreaker.client.ui.util.font.FontRegistry;
import org.lwjgl.input.Keyboard;

public class KeybindElement
        extends AbstractModulesGuiElement {
    private Setting lIIIIlIIllIIlIIlIIIlIIllI;
    private ModulesGuiButtonElement IllIIIIIIIlIlIllllIIllIII;
    private boolean lIIIIllIIlIlIllIIIlIllIlI = false;

    public KeybindElement(Setting cBSetting, float f) {
        super(f);
        this.lIIIIlIIllIIlIIlIIIlIIllI = cBSetting;
        this.height = 12;
        this.IllIIIIIIIlIlIllllIIllIII = new ModulesGuiButtonElement(FontRegistry.getPlayBold18px(), null,
                Keyboard.getKeyName(cBSetting.<Integer>value()), this.x + this.width - 100, this.y, 96,
                18, -9442858, f);
    }

    @Override
    public void handleDrawElement(int mouseX, int mouseY, float partialTicks) {
        boolean bl = (float) mouseX > (this.x + this.width - 48f) * this.scale
                && (float) mouseX < (this.x + this.width - 10f) * this.scale
                && (float) mouseY > (float)(this.y + this.yOffset) * this.scale
                && (float) mouseY < (this.y + 10f + this.yOffset) * this.scale;

        boolean bl2 = (float) mouseX > (this.x + this.width - 92f) * this.scale
                && (float) mouseX < (this.x + this.width - 48f) * this.scale
                && (float) mouseY > (float)(this.y + this.yOffset) * this.scale
                && (float) mouseY < (this.y + 10f + this.yOffset) * this.scale;

        FontRegistry.getUbuntuMedium16px().drawString(this.lIIIIlIIllIIlIIlIIIlIIllI.getLabel().toUpperCase(), this.x + 10f, this.y + 4f, bl2 || bl ? -1090519040 : -1895825408);
        if (this.lIIIIllIIlIlIllIIIlIllIlI && Keyboard.getEventKeyState()) {
            Ref.getMinecraft().bridge$getSoundHandler().bridge$playSound(Ref.getInstanceCreator().createSoundFromPSR(Ref.getInstanceCreator().createResourceLocation("gui.button.press"), 1.0f));
            this.lIIIIlIIllIIlIIlIIIlIIllI.setValue(Keyboard.getEventKey());
            this.IllIIIIIIIlIlIllllIIllIII.text = Keyboard.getKeyName(this.lIIIIlIIllIIlIIlIIIlIIllI.<Integer>value());
            this.lIIIIllIIlIlIllIIIlIllIlI = false;
        }
        this.IllIIIIIIIlIlIllllIIllIII.yOffset = this.yOffset;
        this.IllIIIIIIIlIlIllllIIllIII.setDimensions(this.x + this.width - 100, this.y, 96, 18);
        this.IllIIIIIIIlIlIllllIIllIII.handleDrawElement(mouseX, mouseY, partialTicks);
    }

    @Override
    public void handleMouseClick(int mouseX, int mouseY, int button) {
        if (this.IllIIIIIIIlIlIllllIIllIII.isMouseInside(mouseX, mouseY)) {
            Ref.getMinecraft().bridge$getSoundHandler().bridge$playSound(Ref.getInstanceCreator().createSoundFromPSR(Ref.getInstanceCreator().createResourceLocation("gui.button.press"), 1.0f));
            this.lIIIIllIIlIlIllIIIlIllIlI = true;
            this.IllIIIIIIIlIlIllllIIllIII.text = "<PRESS ANY KEY>";
        }
    }
}
