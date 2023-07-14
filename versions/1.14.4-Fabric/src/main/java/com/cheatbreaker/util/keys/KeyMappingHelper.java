package com.cheatbreaker.util.keys;

import java.util.Objects;

import net.minecraft.client.KeyMapping;
import com.mojang.blaze3d.platform.InputConstants;

import com.cheatbreaker.mixin.client.KeyMappingAccessor;

/**
 * Helper for registering {@link KeyMapping}s.
 *
 * <pre>{@code
 * KeyBinding left = KeyBindingHelper.registerKeyBinding(new KeyBinding("key.example.left", InputUtil.Type.KEYSYM, GLFW.GLFW_KEY_P, "key.category.example"));
 * KeyBinding right = KeyBindingHelper.registerKeyBinding(new KeyBinding("key.example.right", InputUtil.Type.KEYSYM, GLFW.GLFW_KEY_U, "key.category.example"));
 * }</pre>
 *
 * @see KeyMapping
 * @see net.minecraft.client.KeyMapping
 */
public final class KeyMappingHelper {
    private KeyMappingHelper() {
    }

    /**
     * Registers the keybinding and add the keybinding category if required.
     *
     * @param keyBinding the keybinding
     * @return the keybinding itself
     * @throws IllegalArgumentException when a key binding with the same ID is already registered
     */
    public static KeyMapping registerKeyMapping(KeyMapping keyBinding) {
        Objects.requireNonNull(keyBinding, "key binding cannot be null");
        return KeyMappingRegistryImpl.registerKeyBinding(keyBinding);
    }

    /**
     * Returns the configured KeyCode bound to the KeyBinding from the player's settings.
     *
     * @param keyBinding the keybinding
     * @return configured KeyCode
     */
    public static InputConstants.Key getBoundKeyOf(KeyMapping keyBinding) {
        return ((KeyMappingAccessor) keyBinding).accessor$key();
    }
}