package com.cheatbreaker.client.ui.overlay;

import com.cheatbreaker.bridge.ref.Ref;
import com.cheatbreaker.client.ui.fading.AbstractFade;
import com.cheatbreaker.client.ui.fading.FloatFade;
import com.cheatbreaker.client.ui.util.font.FontRegistry;

public class Alert {
    private final AbstractFade fade = new FloatFade(275L);
    private static final int ELEMENT_WIDTH = 140;
    private static final int ELEMENT_HEIGHT = 55;
    private boolean disableTitle;
    private float x;
    private float y;
    private float targetY;
    private final String title;
    private final String[] lines;
    private final long createdTime;

    public Alert(String title, String[] lines, float x, float y) {
        this.title = title;
        this.lines = lines;
        this.x = x;
        this.targetY = y;
        this.y = y;
        this.createdTime = System.currentTimeMillis();
    }

    public void drawAlert() {
        float y = this.y - (this.y - this.targetY) * this.fade.getCurrentValue();
        if (this.disableTitle) {
            Ref.modified$drawGradientRect(this.x, y, this.x + getElementWidth(), y + getElementHeight(), -819057106, -822083584);
            for (int i = 0; i < this.lines.length && i <= 3; ++i) {
                FontRegistry.getPlayRegular16px().drawString(this.lines[i], this.x + 4f, y + 4f + (i * 10f), -1);
            }
        } else {
            Ref.modified$drawGradientRect(this.x, y, this.x + getElementWidth(), y + getElementHeight(), -819057106, -822083584);
            FontRegistry.getPlayRegular16px().drawString(this.title, this.x + 4f, y + 4f, -1);
            Ref.modified$drawRect(this.x + 4f, y + 14.5f, this.x + getElementWidth() - 5f, y + 15f, 0x2E5E5E5E);
            for (int i = 0; i < this.lines.length && i <= 2; ++i) {
                FontRegistry.getPlayRegular16px().drawString(this.lines[i], this.x + 4f, y + 17f + (i * 10f), -1);
            }
        }
        if (!(Ref.getMinecraft().bridge$getCurrentScreen() instanceof OverlayGui)) {
            FontRegistry.getPlayRegular16px().drawString("Press Shift + Tab", this.x + 4f, y + getElementHeight() - 12f, 0x6FFFFFFF);
        }
    }

    public void safeSetY(float f) {
        this.y = this.targetY;
        this.targetY = f;
        this.fade.reset();
    }

    public boolean hasFadeFinished() {
        return !this.fade.hasStartTime() || this.fade.isExpired();
    }

    public boolean alertExpired() {
        return System.currentTimeMillis() - this.createdTime > 3500L;
    }

    public static void displayMessage(String string, String string2) {
        OverlayGui.getInstance().queueAlert(string, string2);
    }

    public static void setSection(String string) {
        OverlayGui.getInstance().setSection(string);
    }

    public static int getElementWidth() {
        return ELEMENT_WIDTH;
    }

    public static int getElementHeight() {
        return ELEMENT_HEIGHT;
    }

    public void setDisableTitle(boolean bl) {
        this.disableTitle = bl;
    }

    public float getX() {
        return this.x;
    }

    public float getY() {
        return this.y;
    }

    public float getTargetY() {
        return this.targetY;
    }

    public void setX(float f) {
        this.x = f;
    }

    public void setY(float f) {
        this.y = f;
    }

    public void setTargetY(float f) {
        this.targetY = f;
    }
}
