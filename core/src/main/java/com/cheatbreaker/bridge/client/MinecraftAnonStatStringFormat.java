package com.cheatbreaker.bridge.client;

import com.cheatbreaker.bridge.ref.Ref;
import com.cheatbreaker.bridge.stats.IStatStringFormatBridge;

public class MinecraftAnonStatStringFormat implements IStatStringFormatBridge {
    public String formatString(String p_74535_1_) {
        try {
            return String.format(p_74535_1_, Ref.getMinecraft().bridge$getGameSettings().bridge$getKeyDisplayString(Ref.getMinecraft().bridge$getGameSettings().bridge$getKeyBindInventory().bridge$getKeyCode()));
        }
        catch (Exception exception) {
            return "Error: " + exception.getLocalizedMessage();
        }
    }
}
