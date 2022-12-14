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
                new Color(-14277082),
                new Color(-11493284)
        );

        this.upperBackgroundFade = new ColorFade(
                new Color(-13487566),
                new Color(-10176146)
        );

        this.lowerBackgroundFade = new ColorFade(
                new Color(-14013910),
                new Color(-11164318)
        );
    }

    public void IllIIIIIIIlIlIllllIIllIII() {
        this.setColors(new int[] {
                -11119018, -11493284,
                -10329502, -10176146,
                -11579569, -11164318
        });
    }

    public void lIIIIllIIlIlIllIIIlIllIlI() {
        this.setColors(new int[] {
                -11493284, -11493284,
                -10176146, -10176146,
                -11164318, -11164318
        });
    }

    public void setDefaultColors() {
        this.setColors(new int[] {
                -14277082, -11493284,
                -13487566, -10176146,
                -14013910, -11164318
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