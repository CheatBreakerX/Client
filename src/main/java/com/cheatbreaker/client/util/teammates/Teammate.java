package com.cheatbreaker.client.util.teammates;

import com.cheatbreaker.bridge.ref.Ref;
import com.cheatbreaker.bridge.util.Vec3Bridge;

import java.awt.*;

public class Teammate {
    private String lIIIIlIIllIIlIIlIIIlIIllI;
    private boolean lIIIIIIIIIlIllIIllIlIIlIl = false;
    private Vec3Bridge vector3D;
    private long lastUpdated;
    private Color IIIIllIIllIIIIllIllIIIlIl;
    private long waitTime;

    public void lIIIIlIIllIIlIIlIIIlIIllI(Color color) {
        this.IIIIllIIllIIIIllIllIIIlIl = color;
    }

    public Teammate(String string, boolean bl) {
        this.lIIIIlIIllIIlIIlIIIlIIllI = string;
        this.lIIIIIIIIIlIllIIllIlIIlIl = bl;
        this.lastUpdated = System.currentTimeMillis();
    }

    public void reset(double x, double y, double z, long waitTime) {
        this.vector3D = Ref.getInstanceCreator().createVec3(x, y, z);
        this.lastUpdated = System.currentTimeMillis();
        this.waitTime = waitTime;
    }

    public Vec3Bridge getVector3D() {
        return this.vector3D;
    }

    public long lIIIIIIIIIlIllIIllIlIIlIl() {
        return this.lastUpdated;
    }

    public String IlllIIIlIlllIllIlIIlllIlI() {
        return this.lIIIIlIIllIIlIIlIIIlIIllI;
    }

    public long IIIIllIlIIIllIlllIlllllIl() {
        return this.waitTime;
    }

    public boolean IIIIllIIllIIIIllIllIIIlIl() {
        return this.lIIIIIIIIIlIllIIllIlIIlIl;
    }

    public Color IlIlIIIlllIIIlIlllIlIllIl() {
        return this.IIIIllIIllIIIIllIllIIIlIl;
    }
}
 