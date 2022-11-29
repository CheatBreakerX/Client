package com.cheatbreaker.bridge.wrapper;

import com.cheatbreaker.bridge.client.MinecraftBridge;
import com.cheatbreaker.bridge.client.gui.FontRendererBridge;
import com.cheatbreaker.bridge.ref.Ref;
import org.lwjgl.input.Keyboard;

import java.awt.*;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;
import java.util.ArrayList;
import java.util.List;

public class CBGuiScreen {
    protected MinecraftBridge wrapped$mc;
    public int wrapped$width;
    public int wrapped$height;
    protected List wrapped$buttonList = new ArrayList();
    protected List wrapped$labelList = new ArrayList();
    public boolean wrapped$field_146291_p;
    protected FontRendererBridge wrapped$fontRendererObj;
    private int wrapped$eventButton;
    private long wrapped$lastMouseEvent;
    private int wrapped$field_146298_h;

    public Runnable super$drawScreen = () -> {};
    public void drawScreen(int mouseX, int mouseY, float delta) {
        this.super$drawScreen.run();
    }

    public Runnable super$keyTyped = () -> {};
    public void keyTyped(char c, int n) {
        this.super$keyTyped.run();
    }

    public static String wrapped$getClipboardString() {
        try {
            Transferable var0 = Toolkit.getDefaultToolkit().getSystemClipboard().getContents(null);

            if (var0 != null && var0.isDataFlavorSupported(DataFlavor.stringFlavor)) {
                return (String)var0.getTransferData(DataFlavor.stringFlavor);
            }
        }
        catch (Exception ignored) {
        }

        return "";
    }

    public static void wrapped$setClipboardString(String p_146275_0_) {
        try
        {
            StringSelection var1 = new StringSelection(p_146275_0_);
            Toolkit.getDefaultToolkit().getSystemClipboard().setContents(var1, null);
        }
        catch (Exception ignored) {
        }
    }
    public Runnable super$mouseClicked = () -> {};
    public void mouseClicked(int mouseX, int mouseY, int mouseButton) {
        this.super$mouseClicked.run();
    }

    public Runnable super$mouseMovedOrUp = () -> {};
    public void mouseMovedOrUp(int p_146286_1_, int p_146286_2_, int p_146286_3_) {
        this.super$mouseMovedOrUp.run();
    }

    public Runnable super$setWorldAndResolution = () -> {};
    public void setWorldAndResolution(MinecraftBridge p_146280_1_, int p_146280_2_, int p_146280_3_) {
        this.super$setWorldAndResolution.run();
    }

    public void initGui() {}

    public Runnable super$handleMouseInput = () -> {};
    public void handleMouseInput() {
        this.super$handleMouseInput.run();
    }

    public void updateScreen() {}
    public void onGuiClosed() {}

    public Runnable super$drawDefaultBackground = () -> {};
    public void drawDefaultBackground() {
        this.super$drawDefaultBackground.run();
    }

    public boolean doesGuiPauseGame() {
        return true;
    }

    public static boolean wrapped$isCtrlKeyDown() {
        return Ref.getMinecraft().bridge$isRunningOnMac() ? Keyboard.isKeyDown(219) || Keyboard.isKeyDown(220) : Keyboard.isKeyDown(29) || Keyboard.isKeyDown(157);
    }

    public static boolean wrapped$isShiftKeyDown() {
        return Keyboard.isKeyDown(42) || Keyboard.isKeyDown(54);
    }
}
