package com.cheatbreaker.client.ui.fading;

public class FloatFade extends AbstractFade {
    public FloatFade(long duration) {
        super(duration, 1.0f);
    }

    public FloatFade(long duration, float f) {
        super(duration, f);
    }

    @Override
    protected float getValue() {
        return (float)(this.duration - this.getTimeUntilEnd()) / (float)this.duration;
    }
}

