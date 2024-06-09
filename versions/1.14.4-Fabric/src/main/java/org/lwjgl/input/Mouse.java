package org.lwjgl.input;

import com.cheatbreaker.bridge.client.MouseHandlerBridge;
import net.minecraft.client.Minecraft;

/**
 * Mouse (CheatBreaker) - ported version of old LWJGL Mouse class for
 *  modern versions of Minecraft.
 */
public class Mouse {
    public static boolean isButtonDown(int button) {
        if (Minecraft.getInstance() == null || Minecraft.getInstance().mouseHandler == null) {
            return false;
        }

        switch (button) {
            case 0:
                return Minecraft.getInstance().mouseHandler.isLeftPressed();
            case 1:
                return Minecraft.getInstance().mouseHandler.isRightPressed();
            case 2:
                return ((MouseHandlerBridge) Minecraft.getInstance().mouseHandler).bridge$isMiddlePressed();
        }

        return false;
    }

    public static int getEventDWheel() {
        return 0;
    }
}
