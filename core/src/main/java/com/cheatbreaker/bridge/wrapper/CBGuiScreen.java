package com.cheatbreaker.bridge.wrapper;

import com.cheatbreaker.bridge.client.MinecraftBridge;
import com.cheatbreaker.bridge.client.gui.FontRendererBridge;
import com.cheatbreaker.bridge.item.ItemStackBridge;
import com.cheatbreaker.bridge.ref.Ref;
import com.cheatbreaker.main.CheatBreaker;
import com.cheatbreaker.client.ui.overlay.OverlayGui;
import org.lwjgl.input.Keyboard;

import java.awt.*;
import java.awt.datatransfer.ClipboardOwner;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CBGuiScreen {
    protected MinecraftBridge mc;
    public int width;
    public int height;
    protected List buttonList = new ArrayList();
    protected List labelList = new ArrayList();
    public boolean field_146291_p;
    protected FontRendererBridge fontRendererObj;
    private int eventButton;
    private long lastMouseEvent;
    private int field_146298_h;


    public boolean super$drawScreen = false;
    public void drawScreen(int p_73863_1_, int p_73863_2_, float p_73863_3_) {
        this.super$drawScreen = true;
    }

    protected void keyTyped(char p_73869_1_, int p_73869_2_) {
        if (Keyboard.isKeyDown(42) && p_73869_2_ == 15) {
            this.mc.bridge$displayGuiScreen(OverlayGui.createInstance(this.mc.bridge$getCurrentScreen()));
        }
        if (p_73869_2_ == 1) {
            if ((Boolean) CheatBreaker.getInstance().getGlobalSettings().guiBlur.getValue()) {
                this.mc.bridge$getEntityRenderer().bridge$stopUseShader();
            }
            this.mc.bridge$displayGuiScreen(null);
            this.mc.bridge$setIngameFocus();
        }
    }

    public static String getClipboardString() {
        try {
            Transferable var0 = Toolkit.getDefaultToolkit().getSystemClipboard().getContents((Object)null);

            if (var0 != null && var0.isDataFlavorSupported(DataFlavor.stringFlavor)) {
                return (String)var0.getTransferData(DataFlavor.stringFlavor);
            }
        }
        catch (Exception var1) {
            ;
        }

        return "";
    }

    public static void setClipboardString(String p_146275_0_) {
        try
        {
            StringSelection var1 = new StringSelection(p_146275_0_);
            Toolkit.getDefaultToolkit().getSystemClipboard().setContents(var1, (ClipboardOwner)null);
        }
        catch (Exception var2)
        {
            ;
        }
    }

    public boolean super$func_146285_a = false;
    protected void func_146285_a(ItemStackBridge p_146285_1_, int p_146285_2_, int p_146285_3_) {
        this.super$func_146285_a = true;
    }

    protected void func_146279_a(String p_146279_1_, int p_146279_2_, int p_146279_3_) {
        this.func_146283_a(Collections.singletonList(p_146279_1_), p_146279_2_, p_146279_3_);
    }

    public boolean super$func_146283_a = false;
    protected void func_146283_a(List p_146283_1_, int p_146283_2_, int p_146283_3_) {
        this.super$func_146283_a = true;
    }

    public boolean super$mouseClicked = false;
    protected void mouseClicked(int p_73864_1_, int p_73864_2_, int p_73864_3_) {
        this.super$mouseClicked = true;
    }

    public boolean super$mouseMovedOrUp = false;
    protected void mouseMovedOrUp(int p_146286_1_, int p_146286_2_, int p_146286_3_) {
        this.super$mouseMovedOrUp = true;
    }

    protected void mouseClickMove(int p_146273_1_, int p_146273_2_, int p_146273_3_, long p_146273_4_) {}

    public boolean super$setWorldAndResolution = false;
    public void setWorldAndResolution(MinecraftBridge p_146280_1_, int p_146280_2_, int p_146280_3_) {
        this.super$setWorldAndResolution = true;
    }

    public void initGui() {}

    public boolean super$handleInput = false;
    public void handleInput() {
        this.super$handleInput = true;
    }

    public boolean super$handleMouseInput = false;
    public void handleMouseInput() {
        this.super$handleMouseInput = true;
    }

    public boolean super$handleKeyboardInput = false;
    public void handleKeyboardInput()
    {
        this.super$handleKeyboardInput = true;
    }

    public void updateScreen() {}

    public void onGuiClosed() {}

    public boolean super$drawDefaultBackground = false;
    public void drawDefaultBackground() {
        this.super$drawDefaultBackground = true;
    }

    public boolean super$func_146270_b = false;
    public void func_146270_b(int p_146270_1_) {
        this.super$func_146270_b = true;
    }

    public boolean super$func_146278_c = false;
    public void func_146278_c(int p_146278_1_) {
        this.super$func_146278_c = true;
    }

    public boolean doesGuiPauseGame() {
        return true;
    }

    public void confirmClicked(boolean p_73878_1_, int p_73878_2_) {}

    public static boolean isCtrlKeyDown() {
        return Ref.getMinecraft().bridge$isRunningOnMac() ? Keyboard.isKeyDown(219) || Keyboard.isKeyDown(220) : Keyboard.isKeyDown(29) || Keyboard.isKeyDown(157);
    }

    public static boolean isShiftKeyDown() {
        return Keyboard.isKeyDown(42) || Keyboard.isKeyDown(54);
    }
}
