package com.cheatbreaker.impl.ref;

import com.cheatbreaker.bridge.client.renderer.RenderHelperBridge;
import net.minecraft.client.renderer.RenderHelper;

public class BridgedRenderHelper implements RenderHelperBridge {
    public void bridge$enableStandardItemLighting() {
        RenderHelper.enableStandardItemLighting();
    }

    public void bridge$disableStandardItemLighting() {
        RenderHelper.disableStandardItemLighting();
    }

    public void bridge$enableGUIStandardItemLighting() {
        RenderHelper.enableGUIStandardItemLighting();
    }
}
