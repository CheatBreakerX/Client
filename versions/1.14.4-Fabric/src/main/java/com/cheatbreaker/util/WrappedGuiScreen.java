package com.cheatbreaker.util;

import com.cheatbreaker.bridge.client.MinecraftBridge;
import com.cheatbreaker.bridge.wrapper.CBGuiScreen;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.TextComponent;

import java.io.IOException;

public class WrappedGuiScreen extends Screen {
    private final CBGuiScreen cbScreen;
    public WrappedGuiScreen(CBGuiScreen cbScreen) {
        super(new TextComponent("Wrapped screen"));
        this.cbScreen = cbScreen;

        this.cbScreen.externalValues$execute = () -> {
            this.minecraft = Minecraft.getInstance();
            this.font = (Font) this.cbScreen.fontRendererObj;
            this.width = this.cbScreen.width;
            this.height = this.cbScreen.height;
        };
    }

    private boolean setDrawScreen = false;
    public void render(int mouseX, int mouseY, float delta) {
        if (!this.setDrawScreen) {
            this.cbScreen.super$drawScreen = () -> super.render(mouseX, mouseY, delta);
            this.setDrawScreen = true;
        }
        this.cbScreen.drawScreen(mouseX, mouseY, delta);
    }

    public boolean charTyped(char c, int n) {
        this.cbScreen.keyTyped(c, n);
        return super.charTyped(c, n);
    }

    private boolean setMouseClicked = false;
    public boolean mouseClicked(double mouseX, double mouseY, int mouseButton) {
        if (!this.setMouseClicked) {
            this.cbScreen.super$mouseClicked = () -> super.mouseClicked(mouseX, mouseY, mouseButton);
            this.setMouseClicked = true;
        }
        this.cbScreen.mouseClicked((int) mouseX, (int) mouseY, mouseButton);
        return super.mouseClicked(mouseX, mouseY, mouseButton);
    }

    // 1.7.10 - mouseMovedOrUp
    // 1.8+   - mouseReleased
    private boolean setMouseMovedOrUp = false;
    public boolean mouseReleased(double mouseX, double mouseY, int state) {
        if (!this.setMouseMovedOrUp) {
            this.cbScreen.super$mouseMovedOrUp = () -> super.mouseReleased(mouseX, mouseY, state);
            this.setMouseMovedOrUp = true;
        }
        this.cbScreen.mouseMovedOrUp((int) mouseX, (int) mouseY, state);
        this.handleMouseInput();
        return super.mouseReleased(mouseX, mouseY, state);
    }

    private boolean setSetWorldAndResolution = false;
    public void init(Minecraft mc, int width, int height) {
        if (!this.setSetWorldAndResolution) {
            this.cbScreen.super$setWorldAndResolution = () -> {
                super.init(mc, width, height);
            };
            this.setSetWorldAndResolution = true;
        }
        this.cbScreen.setWorldAndResolution((MinecraftBridge) mc, width, height);
    }

    public void init() {
        this.cbScreen.initGui();
    }

    private boolean setHandleMouseInput = false;
    public void handleMouseInput() {
        //if (!this.setHandleMouseInput)
        /*{
            this.cbScreen.super$handleMouseInput = () -> {
                try {
                    super.handleMouseInput();
                } catch (IOException e) {
                    e.printStackTrace();
                    throw new RuntimeException(e);
                }
            };
            this.setHandleMouseInput = true;
        }*/
        this.cbScreen.handleMouseInput();
    }

    public void tick() {
        this.cbScreen.updateScreen();
    }

    public void removed() {
        this.cbScreen.onGuiClosed();
    }

    private boolean setDrawDefaultBackground = false;
    public void renderBackground() {
        if (!this.setDrawDefaultBackground) {
            this.cbScreen.super$drawDefaultBackground = super::renderBackground;
            this.setDrawDefaultBackground = true;
        }
        this.cbScreen.drawDefaultBackground();
    }

    public boolean isPauseScreen() {
        return this.cbScreen.doesGuiPauseGame();
    }
}
