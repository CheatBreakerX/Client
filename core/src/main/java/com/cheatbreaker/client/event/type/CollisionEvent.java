package com.cheatbreaker.client.event.type;

import com.cheatbreaker.bridge.util.AxisAlignedBBBridge;
import com.cheatbreaker.client.event.EventBus;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class CollisionEvent extends EventBus.Event {
    private final List<AxisAlignedBBBridge> boundingBoxes;
    private final int x, y, z;
}
