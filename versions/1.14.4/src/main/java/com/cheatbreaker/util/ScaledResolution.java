package com.cheatbreaker.util;

import com.cheatbreaker.bridge.client.gui.ScaledResolutionBridge;
import com.mojang.blaze3d.platform.Window;
import net.minecraft.client.Minecraft;

public class ScaledResolution implements ScaledResolutionBridge {
    private final Window window;

    public ScaledResolution() {
        this.window = Minecraft.getInstance().window;
    }

    public float bridge$getScaledWidth() {
        return this.window.getGuiScaledWidth();
    }

    public float bridge$getScaledHeight() {
        return this.window.getGuiScaledHeight();
    }

    public double bridge$getScaledWidth_double() {
        return this.window.getGuiScaledWidth();
    }

    public double bridge$getScaledHeight_double() {
        return this.window.getGuiScaledHeight();
    }

    public int bridge$getScaleFactor() {
        // looking into this further, there was no need to use the 'double' type as it is only set as an integer in
        //   minecraft's code...
        return (int) this.window.getGuiScale();
    }
}
