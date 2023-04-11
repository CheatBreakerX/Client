package com.cheatbreaker.mixin.client.gui;

import com.cheatbreaker.bridge.client.gui.GuiScreenBridge;
import com.cheatbreaker.bridge.ref.Ref;
import com.cheatbreaker.client.ui.overlay.OverlayGui;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiScreen;
import org.lwjgl.input.Keyboard;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.awt.*;

@Mixin(GuiScreen.class)
public abstract class MixinGuiScreen extends Gui implements GuiScreenBridge {
    @Shadow public Minecraft mc;
    @Shadow public int width;
    @Shadow public int height;

    @Inject(method = "drawWorldBackground", at = @At("HEAD"), cancellable = true)
    public void impl$drawWorldBackground(int p_146270_1_, CallbackInfo callbackInfo) {
        if (this.mc.theWorld != null || OverlayGui.getInstance() != null) {
            drawGradientRect(0, 0, this.width, this.height, new Color(0xA6101010, true).getRGB(), new Color(0x32101010, true).getRGB());
            callbackInfo.cancel();
        }
    }

    @Inject(method = "keyTyped", at = @At("HEAD"))
    public void impl$keyTyped(char c, int n, CallbackInfo callbackInfo) {
        if (Keyboard.isKeyDown(42) && n == 15) {
            Ref.getMinecraft().bridge$displayGuiScreen(OverlayGui.createInstance(Ref.getMinecraft().bridge$getCurrentScreen()));
        }
    }
}
