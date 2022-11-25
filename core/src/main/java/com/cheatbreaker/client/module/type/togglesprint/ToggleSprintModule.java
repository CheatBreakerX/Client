package com.cheatbreaker.client.module.type.togglesprint;

import com.cheatbreaker.bridge.client.gui.GuiChatBridge;
import com.cheatbreaker.bridge.ref.Ref;
import com.cheatbreaker.client.config.Setting;
import com.cheatbreaker.client.event.type.GuiDrawEvent;
import com.cheatbreaker.client.module.AbstractModule;
import com.cheatbreaker.client.ui.module.CBGuiAnchor;
import org.lwjgl.opengl.GL11;

public class ToggleSprintModule extends AbstractModule {

    public static Setting toggleSprint;
    public static Setting toggleSneak;
    public static Setting showHudText;
    private Setting textColor;
    public static Setting doubleTap;
    public static Setting flyBoost;
    public static Setting showWhileTyping;
    private Setting flyBoostLabel;
    public static Setting flyBoostAmount;
    public Setting flyBoostString;
    public Setting flyString;

    public Setting ridingString;
    public Setting decendString;
    public Setting dismountString;
    public Setting sneakHeldString;
    public Setting sprintHeldString;
    public Setting sprintVanillaString;
    public Setting sprintToggledString;
    public Setting sneakToggledString;

    public static boolean buggedSprint;

    public ToggleSprintModule() {
        super("ToggleSprint");
        this.setDefaultAnchor(CBGuiAnchor.LEFT_TOP);
        this.setDefaultTranslations(0.0f, 10);
        this.setDefaultState(false);
        this.isEditable = false;
        toggleSprint = new Setting(this, "Toggle Sprint").setValue(true);
        toggleSneak = new Setting(this, "Toggle Sneak").setValue(false);
        showHudText = new Setting(this, "Show HUD Text").setValue(true);
        this.textColor = new Setting(this, "Text Color").setValue(-1).setMinMax(Integer.MIN_VALUE, Integer.MAX_VALUE);
        doubleTap = new Setting(this, "Double Tap").setValue(false);
        showWhileTyping = new Setting(this, "Show While Typing").setValue(true);
        this.flyBoostLabel = new Setting(this, "label").setValue("Fly Boost");
        flyBoost = new Setting(this, "Fly Boost").setValue(true);
        flyBoostAmount = new Setting(this, "Fly Boost Amount").setValue(4f).setMinMax(2f, 8f).setParent(flyBoost);

        this.flyBoostString = new Setting(this, "Fly Boost String").setValue("[Flying (%BOOST%x boost)]");
        this.flyString = new Setting(this, "Fly String").setValue("[Flying]");
        this.ridingString = new Setting(this, "Riding String").setValue("[Riding]");
        this.decendString = new Setting(this, "Descend String").setValue("[Descending]");
        this.dismountString = new Setting(this, "Dismount String").setValue("[Dismounting]");
        this.sneakHeldString = new Setting(this, "Sneaking String").setValue("[Sneaking (Key Held)]");
        this.sprintHeldString = new Setting(this, "Sprinting Held String").setValue("[Sprinting (Key Held)]");
        this.sprintVanillaString = new Setting(this, "Sprinting Vanilla String").setValue("[Sprinting (Vanilla)]");
        this.sprintToggledString = new Setting(this, "Sprinting Toggle String").setValue("[Sprinting (Toggled)]");
        this.sneakToggledString = new Setting(this, "Sneaking Toggle String").setValue("[Sneaking (Toggled)]");
        this.setPreviewLabel("[Sprinting (Toggled)]", 1.0f);
        this.addEvent(GuiDrawEvent.class, this::renderReal);
    }

    private void renderReal(GuiDrawEvent guiDrawEvent) {
        if (!this.isRenderHud()) {
            return;
        }
        if ((Boolean) showHudText.getValue() && ((Boolean) showWhileTyping.getValue() || !(this.minecraft.bridge$getCurrentScreen() instanceof GuiChatBridge))) {
            GL11.glPushMatrix();
            int n = this.minecraft.bridge$getFontRenderer().bridge$getStringWidth(Ref.getUtils().getToggleSprintInputHelper().getToggleSprintString());
            this.setDimensions(n, 18);
            this.scaleAndTranslate(guiDrawEvent.getResolution());
            this.minecraft.bridge$getFontRenderer().bridge$drawStringWithShadow(Ref.getUtils().getToggleSprintInputHelper().getToggleSprintString(), 0, 0, this.textColor.getColorValue());
            GL11.glPopMatrix();
        } else {
            this.setDimensions(50, 18);
        }
    }

    static {
        buggedSprint = false;
    }
    
}
