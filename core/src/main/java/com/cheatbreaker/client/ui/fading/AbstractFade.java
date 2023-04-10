package com.cheatbreaker.client.ui.fading;

public abstract class AbstractFade {
    protected long startTime;
    protected long lIIIIIIIIIlIllIIllIlIIlIl;
    protected long duration;
    protected boolean IIIIllIlIIIllIlllIlllllIl = true;
    protected float IIIIllIIllIIIIllIllIIIlIl;
    protected long timeElapsed;
    protected final float IIIllIllIlIlllllllIlIlIII;
    private boolean shouldResetOnceCalled;
    private int lIIIIllIIlIlIllIIIlIllIlI = 1;
    private int IlllIllIlIIIIlIIlIIllIIIl = 1;
    private long IlIlllIIIIllIllllIllIIlIl;
    private boolean currentlyInverted;

    public AbstractFade(long duration, float f) {
        this.duration = duration;
        this.IIIllIllIlIlllllllIlIlIII = f;
    }

    protected abstract float getValue();

    public void reset() {
        this.startTime = System.currentTimeMillis();
        this.IIIIllIlIIIllIlllIlllllIl = true;
        this.IlIlllIIIIllIllllIllIIlIl = 0L;
    }

    public void lIIIIlIIllIIlIIlIIIlIIllI(float f) {
        this.startTime = System.currentTimeMillis();
        this.IlIlllIIIIllIllllIllIIlIl = f == 0.0f ? 0L : (long)((float)this.duration * (1.0f - f));
        this.IIIIllIlIIIllIlllIlllllIl = true;
    }

    public void enableShouldResetOnceCalled() {
        this.shouldResetOnceCalled = true;
    }

    public boolean hasStartTime() {
        return this.startTime != 0L;
    }

    public boolean IIIIllIIllIIIIllIllIIIlIl() {
        return this.llIIlllIIIIlllIllIlIlllIl() <= 0L && this.IIIIllIlIIIllIlllIlllllIl;
    }

    public void IlIlIIIlllIIIlIlllIlIllIl() {
        this.startTime = 0L;
        this.lIIIIllIIlIlIllIIIlIllIlI = 1;
    }

    public float lIIIIlIIllIIlIIlIIIlIIllI(boolean bl) {
        if (bl && !this.currentlyInverted) {
            this.currentlyInverted = true;
            this.lIIIIlIIllIIlIIlIIIlIIllI(this.IlIIlIIIIlIIIIllllIIlIllI());
        } else if (this.currentlyInverted && !bl) {
            this.currentlyInverted = false;
            this.lIIIIlIIllIIlIIlIIIlIIllI(this.IlIIlIIIIlIIIIllllIIlIllI());
        }
        if (this.startTime == 0L) {
            return 0.0f;
        }
        float f = this.IlIIlIIIIlIIIIllllIIlIllI();
        return this.currentlyInverted ? f : 1.0f - f;
    }

    public boolean IIIllIllIlIlllllllIlIlIII() {
        return this.startTime != 0L && this.llIIlllIIIIlllIllIlIlllIl() > 0L;
    }

    private float IlIIlIIIIlIIIIllllIIlIllI() {
        if (this.startTime == 0L) {
            return 0.0f;
        }
        if (this.llIIlllIIIIlllIllIlIlllIl() <= 0L) {
            return 1.0f;
        }
        return this.getValue();
    }

    public float getCurrentValue() {
        if (this.startTime == 0L) {
            return 0.0f;
        }
        if (this.IIIIllIIllIIIIllIllIIIlIl()) {
            if (this.shouldResetOnceCalled || this.IlllIllIlIIIIlIIlIIllIIIl >= 1 && this.lIIIIllIIlIlIllIIIlIllIlI < this.IlllIllIlIIIIlIIlIIllIIIl) {
                this.reset();
                ++this.lIIIIllIIlIlIllIIIlIllIlI;
            }
            return this.IIIllIllIlIlllllllIlIlIII;
        }
        if (this.IIIIllIlIIIllIlllIlllllIl) {
            return this.getValue();
        }
        return this.IIIIllIIllIIIIllIllIIIlIl;
    }

    public void lIIIIllIIlIlIllIIIlIllIlI() {
        this.IIIIllIlIIIllIlllIlllllIl = false;
        this.IIIIllIIllIIIIllIllIIIlIl = this.getValue();
        this.timeElapsed = System.currentTimeMillis() - this.startTime;
    }

    public void IlllIllIlIIIIlIIlIIllIIIl() {
        this.startTime = System.currentTimeMillis() - this.timeElapsed;
        this.IIIIllIlIIIllIlllIlllllIl = true;
    }

    public long IlIlllIIIIllIllllIllIIlIl() {
        long l = this.IIIIllIlIIIllIlllIlllllIl ? this.llIIlllIIIIlllIllIlIlllIl() : System.currentTimeMillis() - this.timeElapsed + this.duration - System.currentTimeMillis();
        return Math.min(this.duration, Math.max(0L, l));
    }

    protected long llIIlllIIIIlllIllIlIlllIl() {
        return this.startTime + this.duration - this.IlIlllIIIIllIllllIllIIlIl - System.currentTimeMillis();
    }

    public long getStartTime() {
        return this.startTime;
    }

    public long IIIlllIIIllIllIlIIIIIIlII() {
        return this.lIIIIIIIIIlIllIIllIlIIlIl;
    }

    public long getDuration() {
        return this.duration;
    }

    public boolean IIIlIIllllIIllllllIlIIIll() {
        return this.IIIIllIlIIIllIlllIlllllIl;
    }

    public float lllIIIIIlIllIlIIIllllllII() {
        return this.IIIIllIIllIIIIllIllIIIlIl;
    }

    public long getTimeElapsed() {
        return this.timeElapsed;
    }

    public float IIIIIIlIlIlIllllllIlllIlI() {
        return this.IIIllIllIlIlllllllIlIlIII;
    }

    public void lIIIIlIIllIIlIIlIIIlIIllI(int n) {
        this.IlllIllIlIIIIlIIlIIllIIIl = n;
    }

    public boolean isCurrentlyInverted() {
        return this.currentlyInverted;
    }
}
