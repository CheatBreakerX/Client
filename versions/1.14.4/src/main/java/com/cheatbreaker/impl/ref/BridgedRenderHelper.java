package com.cheatbreaker.impl.ref;

import com.cheatbreaker.bridge.client.renderer.RenderHelperBridge;
import com.mojang.blaze3d.platform.Lighting;

public class BridgedRenderHelper implements RenderHelperBridge {
    public void bridge$enableStandardItemLighting() {
        Lighting.turnOn();
    }

    public void bridge$disableStandardItemLighting() {
        Lighting.turnOff();
    }

    public void bridge$enableGUIStandardItemLighting() {
        Lighting.turnOnGui();
    }
}
