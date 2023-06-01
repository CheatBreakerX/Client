package com.cheatbreaker.client.ui.mainmenu;

import com.cheatbreaker.client.ui.fading.ColorFade;
import com.cheatbreaker.client.ui.util.RenderUtil;
import com.cheatbreaker.client.ui.util.font.FontRegistry;
import java.awt.Color;

public class GradientTextButton extends AbstractElement {
    private String text;
    private final ColorFade outlineFade;
    private final ColorFade upperBackgroundFade;
    private final ColorFade lowerBackgroundFade;
    private int[] colors;

    public GradientTextButton(String string) {
        this.text = string;
        this.outlineFade = new ColorFade(
                new Color(0xFF262626),
                new Color(0xFF50A05C)
        );

        this.upperBackgroundFade = new ColorFade(
                new Color(0xFF323232),
                new Color(0xFF64B96E)
        );

        this.lowerBackgroundFade = new ColorFade(
                new Color(0xFF2A2A2A),
                new Color(0xFF55A562)
        );
    }

    public void applyLighterColorState() {
        this.setColors(new int[] {
                new Color(0xFF565656).getRGB(),
                new Color(0xFF50A05C).getRGB(),

                new Color(0xFF626262).getRGB(),
                new Color(0xFF64B96E).getRGB(),

                new Color(0xFF4F4F4F).getRGB(),
                new Color(0xFF55A562).getRGB()
        });
    }

    public void applySelectedColorState() {
        this.setColors(new int[] {
                new Color(0xFF50A05C).getRGB(),
                new Color(0xFF50A05C).getRGB(),

                new Color(0xFF64B96E).getRGB(),
                new Color(0xFF64B96E).getRGB(),

                new Color(0xFF55A562).getRGB(),
                new Color(0xFF55A562).getRGB()
        });
    }

    public void applyDefaultColorState() {
        this.setColors(new int[] {
                new Color(0xFF262626).getRGB(),
                new Color(0xFF50A05C).getRGB(),

                new Color(0xFF323232).getRGB(),
                new Color(0xFF64B96E).getRGB(),

                new Color(0xFF2A2A2A).getRGB(),
                new Color(0xFF55A562).getRGB()
        });
    }

    private void setColors(int[] arrn) {
        this.colors = arrn;
    }

    @Override
    protected void handleElementDraw(float f, float f2, boolean bl) {
        boolean bl2 = bl && this.isMouseInside(f, f2);
        if (this.colors != null && this.outlineFade.IIIIllIIllIIIIllIllIIIlIl()) {
            this.outlineFade.setStartColor(this.colors[0]);
            this.outlineFade.setEndColor(this.colors[1]);
            this.upperBackgroundFade.setStartColor(this.colors[2]);
            this.upperBackgroundFade.setEndColor(this.colors[3]);
            this.lowerBackgroundFade.setStartColor(this.colors[4]);
            this.lowerBackgroundFade.setEndColor(this.colors[5]);
            this.colors = null;
        }
        RenderUtil.drawCorneredGradientRectWithOutline(this.x, this.y, this.x + this.width, this.y + this.height, this.outlineFade.get(bl2).getRGB(), this.upperBackgroundFade.get(bl2).getRGB(), this.lowerBackgroundFade.get(bl2).getRGB());
        FontRegistry.getRobotoRegular13px().drawCenteredString(this.text, this.x + this.width / 2.0f, this.y + 2.0f, -1);
    }

    public void publicDraw(float mouseX, float mouseY, boolean enabled) {
        this.handleElementDraw(mouseX, mouseY, enabled);
    }

    @Override
    public boolean handleElementMouseClicked(float mouseX, float mouseY, int button, boolean enabled) {
        if (!enabled) {
            return false;
        }
        if (this.isMouseInside(mouseX, mouseY)) {
            this.clickAction.run();
            return true;
        }
        return false;
    }

    public String getText() {
        return this.text;
    }

    public void setText(String string) {
        this.text = string;
    }
}