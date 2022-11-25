package com.cheatbreaker.client.event.type;

import com.cheatbreaker.bridge.client.multiplayer.WorldClientBridge;
import com.cheatbreaker.client.event.EventBus;

public class LoadWorldEvent extends EventBus.Event {

    private final WorldClientBridge world;

    public LoadWorldEvent(WorldClientBridge world) {
        this.world = world;
    }

    public WorldClientBridge getWorld() {
        return world;
    }
}
