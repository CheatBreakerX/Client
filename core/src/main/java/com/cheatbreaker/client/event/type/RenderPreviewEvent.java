package com.cheatbreaker.client.event.type;

import com.cheatbreaker.bridge.client.gui.ScaledResolutionBridge;

public class RenderPreviewEvent extends GuiDrawEvent {
    public RenderPreviewEvent(ScaledResolutionBridge resolution) {
        super(resolution);
    }
}
