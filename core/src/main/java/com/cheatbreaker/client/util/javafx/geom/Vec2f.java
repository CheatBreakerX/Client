package com.cheatbreaker.client.util.javafx.geom;

public class Vec2f {
    public float x;
    public float y;

    public Vec2f() {
    }

    public Vec2f(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public Vec2f(Vec2f v) {
        this.x = v.x;
        this.y = v.y;
    }

    public void set(Vec2f v) {
        this.x = v.x;
        this.y = v.y;
    }

    public void set(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public static float distanceSq(float x1, float y1, float x2, float y2) {
        x1 -= x2;
        y1 -= y2;
        return x1 * x1 + y1 * y1;
    }

    public static float distance(float x1, float y1, float x2, float y2) {
        x1 -= x2;
        y1 -= y2;
        return (float)Math.sqrt((double)(x1 * x1 + y1 * y1));
    }

    public float distanceSq(float vx, float vy) {
        vx -= this.x;
        vy -= this.y;
        return vx * vx + vy * vy;
    }

    public float distanceSq(Vec2f v) {
        float vx = v.x - this.x;
        float vy = v.y - this.y;
        return vx * vx + vy * vy;
    }

    public float distance(float vx, float vy) {
        vx -= this.x;
        vy -= this.y;
        return (float)Math.sqrt((double)(vx * vx + vy * vy));
    }

    public float distance(Vec2f v) {
        float vx = v.x - this.x;
        float vy = v.y - this.y;
        return (float)Math.sqrt((double)(vx * vx + vy * vy));
    }

    public int hashCode() {
        int bits = 7;
        bits = 31 * bits + Float.floatToIntBits(this.x);
        bits = 31 * bits + Float.floatToIntBits(this.y);
        return bits;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        } else if (!(obj instanceof Vec2f)) {
            return false;
        } else {
            Vec2f v = (Vec2f)obj;
            return this.x == v.x && this.y == v.y;
        }
    }

    public String toString() {
        return "Vec2f[" + this.x + ", " + this.y + "]";
    }
}
