package com.cheatbreaker.client.util.javafx.geom;

public class Vec2d {
    public double x;
    public double y;

    public Vec2d() {
    }

    public Vec2d(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public Vec2d(Vec2d v) {
        this.set(v);
    }

    public Vec2d(Vec2f v) {
        this.set(v);
    }

    public void set(Vec2d v) {
        this.x = v.x;
        this.y = v.y;
    }

    public void set(Vec2f v) {
        this.x = (double)v.x;
        this.y = (double)v.y;
    }

    public void set(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public static double distanceSq(double x1, double y1, double x2, double y2) {
        x1 -= x2;
        y1 -= y2;
        return x1 * x1 + y1 * y1;
    }

    public static double distance(double x1, double y1, double x2, double y2) {
        x1 -= x2;
        y1 -= y2;
        return Math.sqrt(x1 * x1 + y1 * y1);
    }

    public double distanceSq(double vx, double vy) {
        vx -= this.x;
        vy -= this.y;
        return vx * vx + vy * vy;
    }

    public double distanceSq(Vec2d v) {
        double vx = v.x - this.x;
        double vy = v.y - this.y;
        return vx * vx + vy * vy;
    }

    public double distance(double vx, double vy) {
        vx -= this.x;
        vy -= this.y;
        return Math.sqrt(vx * vx + vy * vy);
    }

    public double distance(Vec2d v) {
        double vx = v.x - this.x;
        double vy = v.y - this.y;
        return Math.sqrt(vx * vx + vy * vy);
    }

    public int hashCode() {
        long bits = 7L;
        bits = 31L * bits + Double.doubleToLongBits(this.x);
        bits = 31L * bits + Double.doubleToLongBits(this.y);
        return (int)(bits ^ bits >> 32);
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        } else if (!(obj instanceof Vec2d)) {
            return false;
        } else {
            Vec2d v = (Vec2d)obj;
            return this.x == v.x && this.y == v.y;
        }
    }

    public String toString() {
        return "Vec2d[" + this.x + ", " + this.y + "]";
    }
}
