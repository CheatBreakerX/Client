package com.cheatbreaker.client.event.type;

import com.cheatbreaker.bridge.util.AxisAlignedBBBridge;
import com.cheatbreaker.client.event.EventBus;

import java.util.List;

public class CollisionEvent extends EventBus.Event {

    private final List<AxisAlignedBBBridge> boundingBoxes;
    private final int x, y, z;

    public CollisionEvent(List<AxisAlignedBBBridge> boundingBoxes, int x, int y, int z) {
        this.boundingBoxes = boundingBoxes;
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public List<AxisAlignedBBBridge> getBoundingBoxes() {
        return boundingBoxes;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getZ() {
        return z;
    }
}
