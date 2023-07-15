package org.lwjgl.input;

import net.minecraft.client.Minecraft;
import org.lwjgl.glfw.GLFW;

/**
 * Keyboard (CheatBreaker) - ported version of old LWJGL Keyboard class for
 *  modern versions of Minecraft.
 */
public class Keyboard {
    public static boolean isKeyDown(int key) {
        return GLFW.glfwGetKey(Minecraft.getInstance().window.getWindow(), key) == GLFW.GLFW_PRESS;
    }

    public static void enableRepeatEvents(boolean enable) {

    }

    public static String getKeyName(int key) {
        return GLFW.glfwGetKeyName(key, GLFW.glfwGetKeyScancode(key));
    }
}
