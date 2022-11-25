package com.cheatbreaker.client.event.type;

import com.cheatbreaker.bridge.client.gui.ScaledResolutionBridge;
import com.cheatbreaker.client.event.EventBus;

public class GuiDrawEvent extends EventBus.Event {

    private final ScaledResolutionBridge resolution;

    public GuiDrawEvent(ScaledResolutionBridge resolution) {
        this.resolution = resolution;
    }

    public ScaledResolutionBridge getResolution() {
        return resolution;
    }
}
