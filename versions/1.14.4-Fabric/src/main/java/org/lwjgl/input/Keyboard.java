package org.lwjgl.input;

import com.mojang.blaze3d.platform.InputConstants;
import net.minecraft.client.Minecraft;
import org.lwjgl.glfw.GLFW;

/**
 * Keyboard (CheatBreaker) - ported version of old LWJGL Keyboard class for
 *  modern versions of Minecraft.
 */
public class Keyboard {
    public static boolean isKeyDown(int key) {
        return InputConstants.isKeyDown(Minecraft.getInstance().window.getWindow(), key);
    }

    public static void enableRepeatEvents(boolean enable) {
        Minecraft.getInstance().keyboardHandler.setSendRepeatsToGui(enable);
    }

    public static String getKeyName(int key) {
        return GLFW.glfwGetKeyName(key, GLFW.glfwGetKeyScancode(key));
    }
}
