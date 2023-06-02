package com.cheatbreaker.mixin.client.gui;

import com.cheatbreaker.bridge.client.gui.GuiTextFieldBridge;
import net.minecraft.client.gui.GuiTextField;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(GuiTextField.class)
public abstract class MixinGuiTextField implements GuiTextFieldBridge {
    @Shadow public abstract void setText(String p_146180_1_);
    @Shadow public abstract void setFocused(boolean p_146195_1_);
    @Shadow public abstract void drawTextBox();
    @Shadow public abstract String getText();
    @Shadow public abstract boolean textboxKeyTyped(char p_146201_1_, int p_146201_2_);
    @Shadow public abstract void updateCursorCounter();
    @Shadow public abstract boolean mouseClicked(int par1, int par2, int par3);

    public void bridge$setText(String text) {
        this.setText(text);
    }

    public void bridge$setFocused(boolean focused) {
        this.setFocused(focused);
    }

    public void bridge$drawTextBox() {
        this.drawTextBox();
    }

    public String bridge$getText() {
        return this.getText();
    }

    public void bridge$textboxKeyTyped(char c, int n) {
        this.textboxKeyTyped(c, n);
    }

    public void bridge$mouseClicked(int mouseX, int mouseY, int button) {
        this.mouseClicked(mouseX, mouseY, button);
    }

    public void bridge$updateCursorCounter() {
        this.updateCursorCounter();
    }
}
