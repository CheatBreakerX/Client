package com.cheatbreaker.mixin.net.minecraft.client;

import com.cheatbreaker.bridge.ref.Ref;
import com.cheatbreaker.client.event.type.KeyboardEvent;
import com.cheatbreaker.client.ui.overlay.OverlayGui;
import com.cheatbreaker.main.CheatBreaker;
import net.minecraft.client.KeyboardHandler;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.Screen;
import org.lwjgl.glfw.GLFW;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(KeyboardHandler.class)
public class MixinKeyboardHandler {
    @Inject(method = "keyPress", at = @At("HEAD"))
    public void impl$keyPress(long window, int key, int scancode, int action, int modifiers,
                              CallbackInfo callbackInfo) {
        if (window == Minecraft.getInstance().window.getWindow()) {
            if (action == GLFW.GLFW_PRESS) {
                CheatBreaker.getInstance().getEventBus().callEvent(new KeyboardEvent(key));

                if (Screen.hasShiftDown() && key == GLFW.GLFW_KEY_TAB) {
                    Ref.getMinecraft().bridge$displayGuiScreen(OverlayGui.getInstance());
                }
            }
        }
    }
}
