package com.cheatbreaker.client.ui.mainmenu.element;

import com.cheatbreaker.bridge.ref.Ref;
import com.cheatbreaker.bridge.util.ResourceLocationBridge;
import com.cheatbreaker.client.ui.fading.ColorFade;
import com.cheatbreaker.client.ui.mainmenu.AbstractElement;
import com.cheatbreaker.client.ui.util.RenderUtil;
import com.cheatbreaker.client.ui.util.font.FontRegistry;
import lombok.Setter;

public class IconButtonElement extends AbstractElement {
    @Setter
    private ResourceLocationBridge icon;
    @Setter
    private String text;
    private boolean usesText;
    private final ColorFade outlineFade;
    private final ColorFade upperBackgroundFade;
    private final ColorFade lowerBackgroundFade;
    private float iconSize = 4;

    public IconButtonElement(ResourceLocationBridge icon) {
        this.icon = icon;
        this.outlineFade = new ColorFade(0x4FFFFFFF, 0xAF50A05C);
        this.upperBackgroundFade = new ColorFade(0x1A858585, 0x3F64B96E);
        this.lowerBackgroundFade = new ColorFade(0x1A858585, 0x3F55A562);
    }

    public IconButtonElement(float iconSize, ResourceLocationBridge icon) {
        this.icon = icon;
        this.iconSize = iconSize;
        this.outlineFade = new ColorFade(0x4FFFFFFF, 0xAF50A05C);
        this.upperBackgroundFade = new ColorFade(0x1A858585, 0x3F64B96E);
        this.lowerBackgroundFade = new ColorFade(0x1A858585, 0x3F55A562);
    }

    public IconButtonElement(String string) {
        this.text = string;
        this.usesText = true;
        this.outlineFade = new ColorFade(0x4FFFFFFF, 0xAF50A05C);
        this.upperBackgroundFade = new ColorFade(0x1A858585, 0x3F64B96E);
        this.lowerBackgroundFade = new ColorFade(0x1A858585, 0x3F55A562);
    }

    @Override
    protected void handleElementDraw(float mouseX, float mouseY, boolean enableMouse) {
        boolean useSecondary = enableMouse && this.isMouseInside(mouseX, mouseY);
        RenderUtil.drawCorneredGradientRectWithOutline(this.x, this.y,
                this.x + this.width, this.y + this.height,
                this.outlineFade.get(useSecondary).getRGB(),
                this.upperBackgroundFade.get(useSecondary).getRGB(),
                this.lowerBackgroundFade.get(useSecondary).getRGB());
        if (this.usesText) {
            FontRegistry.getRobotoRegular13px().drawString(this.text,
                    this.x + this.width / 2f, this.y + 2f, -1);
        } else {
            Ref.getGlBridge().bridge$color(1f, 1f, 1f, .8f);
            RenderUtil.drawIcon(this.icon, this.iconSize,
                    this.x + this.width / 2f - this.iconSize, this.y + this.height / 2f - this.iconSize);
        }
    }

    @Override
    public boolean handleElementMouseClicked(float mouseX, float mouseY, int mouseButton, boolean enableMouse) {
        return false;
    }
}