package com.cheatbreaker.client.ui.mainmenu.element;

import com.cheatbreaker.client.ui.fading.ColorFade;
import com.cheatbreaker.client.ui.mainmenu.AbstractElement;
import com.cheatbreaker.client.ui.util.font.FontRegistry;

public class TextButtonElement extends AbstractElement {
    private final String text;
    private final ColorFade textColorFade;

    public TextButtonElement(String string) {
        this.text = string;
        this.textColorFade = new ColorFade(-1879048193, -1);
    }

    @Override
    protected void handleElementDraw(float mouseX, float mouseY, boolean enableMouse) {
        FontRegistry.getRobotoBold14px().drawString(this.text, this.x + 6f, this.y + 6f, this.textColorFade.get(this.isMouseInside(mouseX, mouseY) && enableMouse).getRGB());
    }

    public float setElementDimensions(float x, float y) {
        this.x = x;
        this.y = y;
        this.width = FontRegistry.getRobotoBold14px().getStringWidth(this.text) + 12f;
        this.height = 20f;

        return this.width + 1f;
    }
}