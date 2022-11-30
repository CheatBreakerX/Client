package com.cheatbreaker.mixin.client.gui;

import com.cheatbreaker.bridge.client.gui.GuiScreenBridge;
import com.cheatbreaker.client.ui.overlay.OverlayGui;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiScreen;
import org.lwjgl.input.Mouse;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.awt.*;
import java.io.IOException;

@Mixin(GuiScreen.class)
public abstract class MixinGuiScreen extends Gui implements GuiScreenBridge {
    @Shadow public Minecraft mc;
    @Shadow public int width;
    @Shadow public int height;

    @Inject(method = "drawWorldBackground", at = @At("HEAD"), cancellable = true)
    public void drawWorldBackground(int p_146270_1_, CallbackInfo callbackInfo) {
        if (this.mc.theWorld != null || OverlayGui.getInstance() != null) {
            drawGradientRect(0, 0, this.width, this.height, new Color(0xA6101010, true).getRGB(), new Color(0x32101010, true).getRGB());
            callbackInfo.cancel();
        }
    }

    @Shadow private int touchValue;
    @Shadow private int eventButton;
    @Shadow private long lastMouseEvent;
    @Shadow protected abstract void mouseClicked(int mouseX, int mouseY, int mouseButton) throws IOException;
    @Shadow protected abstract void mouseReleased(int mouseX, int mouseY, int state);
    @Shadow protected abstract void mouseClickMove(int mouseX, int mouseY, int clickedMouseButton, long timeSinceLastClick);

    /**
     * @author iAmSpace
     * @reason Debugging
     */
    @Overwrite
    public void handleMouseInput() throws IOException {
        System.out.println("1");
        System.out.println("Mouse.getEventX() = " +  Mouse.getEventX());
        System.out.println("width = " +  this.width);
        System.out.println("displayWidth = " + (this.mc == null ? "mc = null" : this.mc.displayWidth));
        int i = Mouse.getEventX() * this.width / this.mc.displayWidth;
        System.out.println("2");
        int j = this.height - Mouse.getEventY() * this.height / this.mc.displayHeight - 1;
        System.out.println("3");
        int k = Mouse.getEventButton();

        System.out.println("4");
        if (Mouse.getEventButtonState()) {
            System.out.println("4.1");
            if (this.mc.gameSettings.touchscreen && this.touchValue++ > 0) {
                return;
            }

            System.out.println("4.2");
            this.eventButton = k;
            System.out.println("4.3");
            this.lastMouseEvent = Minecraft.getSystemTime();
            System.out.println("4.4");
            this.mouseClicked(i, j, this.eventButton);
        }
        else if (k != -1) {
            System.out.println("4.5");
            if (this.mc.gameSettings.touchscreen && --this.touchValue > 0) {
                return;
            }

            System.out.println("4.6");
            this.eventButton = -1;
            System.out.println("4.7");
            this.mouseReleased(i, j, k);
        }
        else if (this.eventButton != -1 && this.lastMouseEvent > 0L) {
            System.out.println("4.8");
            long l = Minecraft.getSystemTime() - this.lastMouseEvent;
            System.out.println("4.9");
            this.mouseClickMove(i, j, this.eventButton, l);
        }
    }
}
