package com.cheatbreaker.bridge.client.gui;

public interface GuiTextFieldBridge {
    void bridge$setText(String text);
    void bridge$setFocused(boolean focused);
    void bridge$drawTextBox();
    String bridge$getText();
    void bridge$textboxKeyTyped(char c, int n);
    void bridge$mouseClicked(int mouseX, int mouseY, int button);
    void bridge$updateCursorCounter();
}
