package com.cheatbreaker.mixin.client.gui.components;

import com.cheatbreaker.bridge.client.gui.GuiTextFieldBridge;
import net.minecraft.client.gui.components.EditBox;
import net.minecraft.client.gui.screens.controls.ControlsScreen;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(EditBox.class)
public abstract class MixinEditBox implements GuiTextFieldBridge {
    @Shadow public abstract void setValue(String string);
    @Shadow public abstract void setFocus(boolean bl);
    @Shadow public abstract String getValue();
    @Shadow public abstract boolean charTyped(char c, int i);
    @Shadow public abstract boolean mouseClicked(double d, double e, int i);

    public void bridge$setText(String text) {
        this.setValue(text);
    }

    public void bridge$setFocused(boolean focused) {
        this.setFocus(focused);
    }

    public void bridge$drawTextBox() {

    }

    public String bridge$getText() {
        return this.getValue();
    }

    public void bridge$textboxKeyTyped(char c, int n) {
        this.charTyped(c, n);
    }

    public void bridge$mouseClicked(int mouseX, int mouseY, int button) {
        this.mouseClicked(mouseX, mouseY, button);
    }

    public void bridge$updateCursorCounter() {

    }
}
