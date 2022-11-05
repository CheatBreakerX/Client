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
    protected void handleElementDraw(float f, float f2, boolean bl) {
        FontRegistry.getRobotoBold14px().drawString(this.text, this.x + 6f, this.y + 6f, this.textColorFade.get(this.isMouseInside(f, f2) && bl).getRGB());
    }
}