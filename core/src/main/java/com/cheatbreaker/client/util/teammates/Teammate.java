package com.cheatbreaker.client.util.teammates;

import com.cheatbreaker.bridge.ref.Ref;
import com.cheatbreaker.bridge.util.Vec3Bridge;
import lombok.Getter;
import lombok.Setter;

import java.awt.*;

@Getter
public class Teammate {
    private String uuid;
    private boolean leader = false;
    private Vec3Bridge vector3D;
    private long lastUpdated;
    @Setter private Color color;
    private long waitTime;

    public Teammate(String string, boolean bl) {
        this.uuid = string;
        this.leader = bl;
        this.lastUpdated = System.currentTimeMillis();
    }

    public void reset(double x, double y, double z, long waitTime) {
        this.vector3D = Ref.getInstanceCreator().createVec3(x, y, z);
        this.lastUpdated = System.currentTimeMillis();
        this.waitTime = waitTime;
    }
}
 