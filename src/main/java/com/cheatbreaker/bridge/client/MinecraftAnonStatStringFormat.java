package com.cheatbreaker.bridge.client;

import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.GameSettings;
import net.minecraft.stats.IStatStringFormat;

public class MinecraftAnonStatStringFormat implements IStatStringFormat {
    public String formatString(String p_74535_1_) {
        try {
            return String.format(p_74535_1_, GameSettings.getKeyDisplayString(Minecraft.getMinecraft().gameSettings.keyBindInventory.getKeyCode()));
        }
        catch (Exception exception) {
            return "Error: " + exception.getLocalizedMessage();
        }
    }
}
