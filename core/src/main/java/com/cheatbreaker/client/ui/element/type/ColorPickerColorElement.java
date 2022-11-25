package com.cheatbreaker.client.ui.element.type;

import com.cheatbreaker.bridge.ref.Ref;
import com.cheatbreaker.client.ui.element.AbstractModulesGuiElement;

public class ColorPickerColorElement
        extends AbstractModulesGuiElement {
    public int color;

    public ColorPickerColorElement(float f, int n) {
        super(f);
        this.color = n;
    }

    @Override
    public void handleDrawElement(int mouseX, int mouseY, float partialTicks) {
        Ref.modified$drawRect(this.x, this.y, this.x + this.width, this.y + this.height, -1358954496);
        Ref.modified$drawRect(this.x + 1, this.y + 1, this.x + this.width - 1, this.y + this.height - 1, this.color | 0xFF000000);
    }

    @Override
    public void handleMouseClick(int mouseX, int mouseY, int button) {
    }
}
