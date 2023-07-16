package com.cheatbreaker.client.ui.overlay.element;

import com.cheatbreaker.bridge.ref.Ref;
import com.cheatbreaker.client.ui.mainmenu.AbstractElement;
import com.cheatbreaker.client.ui.util.font.FontRegistry;
import lombok.Getter;
import lombok.Setter;

public class FlatButtonElement extends AbstractElement {
    @Getter
    @Setter
    private String text;

    public FlatButtonElement(String text) {
        this.text = text;
    }

    public void handleElementDraw(float mouseX, float mouseY, boolean enableMouse) {
        this.render(this.text, mouseX, mouseY, enableMouse);
    }

    public void render(String text, float mouseX, float mouseY, boolean enableMouseInput) {
        Ref.modified$drawRect(this.x, this.y, this.x + this.width, this.y + this.height,
                enableMouseInput && this.isMouseInside(mouseX, mouseY) ? -16747106 : -13158601);
        FontRegistry.getPlayRegular14px().drawCenteredString(text, this.x + this.width / 2f,
                this.y + this.height / 2f - 5f, -1);
    }
}
