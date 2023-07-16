package com.cheatbreaker.client.ui.fading;

import lombok.Setter;

import java.awt.*;

public class ColorFade extends ExponentialFade {
    @Setter
    private int startColor;
    @Setter
    private int endColor;
    private boolean switched;
    private Color awtStartColor;
    private Color awtEndColor;

    public ColorFade(long duration, int startColor, int endColor) {
        super(duration);
        this.startColor = startColor;
        this.endColor = endColor;
    }

    public ColorFade(int startColor, int endColor) {
        this(175L, startColor, endColor);
    }

    public ColorFade(java.awt.Color startColor, java.awt.Color endColor) {
        this(175L, startColor.getRGB(), endColor.getRGB());
    }

    public Color get(boolean shouldSwitch) {
        Color color = new Color(shouldSwitch ? this.endColor : this.startColor, true);
        if (shouldSwitch && !this.switched) {
            this.switched = true;
            this.awtStartColor = new Color(this.startColor, true);
            this.awtEndColor = new Color(this.endColor, true);
            this.reset();
        } else if (this.switched && !shouldSwitch) {
            this.switched = false;
            this.awtStartColor = new Color(this.endColor, true);
            this.awtEndColor = new Color(this.startColor, true);
            this.reset();
        }
        if (this.isFadeOngoing()) {
            float f = super.getCurrentValue();
            int red = (int)Math.abs(f * (float)this.awtEndColor.getRed() + (1.0f - f) * (float)this.awtStartColor.getRed());
            int green = (int)Math.abs(f * (float)this.awtEndColor.getGreen() + (1.0f - f) * (float)this.awtStartColor.getGreen());
            int blue = (int)Math.abs(f * (float)this.awtEndColor.getBlue() + (1.0f - f) * (float)this.awtStartColor.getBlue());
            int alpha = (int)Math.abs(f * (float)this.awtEndColor.getAlpha() + (1.0f - f) * (float)this.awtStartColor.getAlpha());
            color = new Color(red, green, blue, alpha);
        }
        return color;
    }
}