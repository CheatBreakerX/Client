package com.cheatbreaker.client.event.type;

import com.cheatbreaker.bridge.client.gui.ScaledResolutionBridge;
import com.cheatbreaker.client.event.EventBus;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class GuiDrawEvent extends EventBus.Event {
    private final ScaledResolutionBridge resolution;
}
