package com.cheatbreaker.bridge.ext;

import lombok.Getter;

public class GLColor {
    @Getter
    private float red;
    @Getter
    private float green;
    @Getter
    private float blue;
    @Getter
    private float alpha;

    public GLColor setValues(float red, float green, float blue, float alpha) {
        this.red = red;
        this.green = green;
        this.blue = blue;
        this.alpha = alpha;
        return this;
    }

    public static GLColor glsmCurrentColor = new GLColor().setValues(1f, 1f, 1f, 1f);
}
