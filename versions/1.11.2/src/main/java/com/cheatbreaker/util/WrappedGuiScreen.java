package com.cheatbreaker.util;

import com.cheatbreaker.bridge.client.MinecraftBridge;
import com.cheatbreaker.bridge.wrapper.CBGuiScreen;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiScreen;

import java.io.IOException;

public class WrappedGuiScreen extends GuiScreen {
    private final CBGuiScreen cbScreen;
    public WrappedGuiScreen(CBGuiScreen cbScreen) {
        this.cbScreen = cbScreen;

        this.cbScreen.externalValues$execute = () -> {
            this.mc = Minecraft.getMinecraft();
            this.fontRenderer = (FontRenderer) this.cbScreen.fontRendererObj;
            this.width = this.cbScreen.width;
            this.height = this.cbScreen.height;
        };
    }

    private boolean setDrawScreen = false;
    public void drawScreen(int mouseX, int mouseY, float delta) {
        if (!this.setDrawScreen) {
            this.cbScreen.super$drawScreen = () -> super.drawScreen(mouseX, mouseY, delta);
            this.setDrawScreen = true;
        }
        this.cbScreen.drawScreen(mouseX, mouseY, delta);
    }

    private boolean setKeyTyped = false;
    protected void keyTyped(char c, int n) {
        if (!this.setKeyTyped) {
            this.cbScreen.super$keyTyped = () -> {
                try {
                    super.keyTyped(c, n);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            };
            this.setKeyTyped = true;
        }
        this.cbScreen.keyTyped(c, n);
    }

    private boolean setMouseClicked = false;
    protected void mouseClicked(int mouseX, int mouseY, int mouseButton) {
        if (!this.setMouseClicked) {
            this.cbScreen.super$mouseClicked = () -> {
                try {
                    super.mouseClicked(mouseX, mouseY, mouseButton);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            };
            this.setMouseClicked = true;
        }
        this.cbScreen.mouseClicked(mouseX, mouseY, mouseButton);
    }

    // 1.7.10 - mouseMovedOrUp
    // 1.8+   - mouseReleased
    private boolean setMouseMovedOrUp = false;
    protected void mouseReleased(int mouseX, int mouseY, int state) {
        if (!this.setMouseMovedOrUp) {
            this.cbScreen.super$mouseMovedOrUp = () -> super.mouseReleased(mouseX, mouseY, state);
            this.setMouseMovedOrUp = true;
        }
        this.cbScreen.mouseMovedOrUp(mouseX, mouseY, state);
    }

    private boolean setSetWorldAndResolution = false;
    public void setWorldAndResolution(Minecraft mc, int width, int height) {
        if (!this.setSetWorldAndResolution) {
            this.cbScreen.super$setWorldAndResolution = () -> {
                super.setWorldAndResolution(mc, width, height);
            };
            this.setSetWorldAndResolution = true;
        }
        this.cbScreen.setWorldAndResolution((MinecraftBridge) mc, width, height);
    }

    public void initGui() {
        this.cbScreen.initGui();
    }

    private boolean setHandleMouseInput = false;
    public void handleMouseInput() {
        //if (!this.setHandleMouseInput)
        {
            this.cbScreen.super$handleMouseInput = () -> {
                try {
                    super.handleMouseInput();
                } catch (IOException e) {
                    e.printStackTrace();
                    throw new RuntimeException(e);
                }
            };
            this.setHandleMouseInput = true;
        }
        this.cbScreen.handleMouseInput();
    }

    public void updateScreen() {
        this.cbScreen.updateScreen();
    }

    public void onGuiClosed() {
        this.cbScreen.onGuiClosed();
    }

    private boolean setDrawDefaultBackground = false;
    public void drawDefaultBackground() {
        if (!this.setDrawDefaultBackground) {
            this.cbScreen.super$drawDefaultBackground = super::drawDefaultBackground;
            this.setDrawDefaultBackground = true;
        }
        this.cbScreen.drawDefaultBackground();
    }

    public boolean doesGuiPauseGame() {
        return this.cbScreen.doesGuiPauseGame();
    }
}
