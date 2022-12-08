package com.cheatbreaker.client.ui.module;

import com.cheatbreaker.bridge.client.gui.GuiTextFieldBridge;
import com.cheatbreaker.bridge.ref.Ref;
import com.cheatbreaker.bridge.util.EnumChatFormattingBridge;
import com.cheatbreaker.bridge.wrapper.CBGuiScreen;
import com.cheatbreaker.main.CheatBreaker;
import com.cheatbreaker.client.config.Profile;
import com.cheatbreaker.client.ui.element.profile.ProfileElement;
import com.cheatbreaker.client.ui.element.profile.ProfilesListElement;
import com.cheatbreaker.client.ui.util.font.FontRegistry;
import org.lwjgl.input.Keyboard;

import java.io.File;
import java.nio.file.Files;

public class CBProfileCreateGui extends CBGuiScreen {
    private final CBGuiScreen guiScreen;
    private final float IlllIIIlIlllIllIlIIlllIlI;
    private final int IIIIllIlIIIllIlllIlllllIl;
    private final ProfilesListElement parent;
    private GuiTextFieldBridge textField = null;
    private String IlIlIIIlllIIIlIlllIlIllIl = "";
    private boolean IIIllIllIlIlllllllIlIlIII = false;
    private Profile profile;

    public CBProfileCreateGui(Profile profile, CBGuiScreen guiScreen, ProfilesListElement parent, int n, float f) {
        this(guiScreen, parent, n, f);
        this.profile = profile;
    }

    public CBProfileCreateGui(CBGuiScreen guiScreen, ProfilesListElement parent, int n, float f) {
        this.guiScreen = guiScreen;
        this.IlllIIIlIlllIllIlIIlllIlI = f;
        this.parent = parent;
        this.IIIIllIlIIIllIlllIlllllIl = n;
        this.IIIllIllIlIlllllllIlIlIII = true;
    }

    @Override
    public void updateScreen() {
        this.textField.bridge$updateCursorCounter();
    }

    @Override
    public void onGuiClosed() {
        Keyboard.enableRepeatEvents(false);
    }

    @Override
    public void initGui() {
        Keyboard.enableRepeatEvents(true);

        // setWorldAndResolution stuff
        this.mc = Ref.getMinecraft();
        this.fontRendererObj = this.mc.bridge$getFontRenderer();
        this.width = (int) Ref.getInstanceCreator().createScaledResolution().bridge$getScaledWidth();
        this.height = (int) Ref.getInstanceCreator().createScaledResolution().bridge$getScaledHeight();
        this.buttonList.clear();
        this.setExternalValues();

        if (!this.IIIllIllIlIlllllllIlIlIII) {
            this.mc.bridge$displayGuiScreen(this.guiScreen);
            ((CBModulesGui) this.guiScreen).currentScrollableElement = ((CBModulesGui) this.guiScreen).profilesElement;
        } else {
            this.IIIllIllIlIlllllllIlIlIII = false;
            this.textField = Ref.getInstanceCreator().createTextField(this.width / 2 - 70, this.height / 2 - 6, 140, 10);
            if (this.profile != null) {
                this.textField.bridge$setText(this.profile.getName());
            }
            this.textField.bridge$setFocused(true);
        }
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float delta) {
        this.guiScreen.drawScreen(mouseX, mouseY, delta);
        this.drawDefaultBackground();
        Ref.modified$drawRect(this.width / 2f - 73, this.height / 2f - 19, this.width / 2f + 73, this.height / 2f + 8, -11250604);
        Ref.modified$drawRect(this.width / 2f - 72, this.height / 2f - 18, this.width / 2f + 72, this.height / 2f + 7, -3881788);
        Ref.getGlBridge().bridge$pushMatrix();
        Ref.getGlBridge().bridge$scale(this.IlllIIIlIlllIllIlIIlllIlI, this.IlllIIIlIlllIllIlIIlllIlI, this.IlllIIIlIlllIllIlIIlllIlI);
        int n3 = (int) ((float) this.width / this.IlllIIIlIlllIllIlIIlllIlI);
        int n4 = (int) ((float) this.height / this.IlllIIIlIlllIllIlIIlllIlI);
        FontRegistry.getUbuntuMedium16px().drawString("Profile Name: ", (float) (n3 / 2) - (float) 70 / this.IlllIIIlIlllIllIlIIlllIlI, (float) (n4 / 2) - (float) 17 / this.IlllIIIlIlllIllIlIIlllIlI, 0x6F000000);
        FontRegistry.getUbuntuMedium16px().drawString(this.IlIlIIIlllIIIlIlllIlIllIl, (float) (n3 / 2) - (float) 72 / this.IlllIIIlIlllIllIlIIlllIlI, (float) (n4 / 2) + (float) 8 / this.IlllIIIlIlllIllIlIIlllIlI, -1358954496);
        Ref.getGlBridge().bridge$popMatrix();
        this.textField.bridge$drawTextBox();
    }

    @Override
    public void mouseClicked(int mouseX, int mouseY, int mouseButton) {
        super.mouseClicked(mouseX, mouseY, mouseButton);
        this.textField.bridge$mouseClicked(mouseX, mouseY, mouseButton);
    }

    @Override
    public void keyTyped(char c, int n) {
        switch (n) {
            case 1: {
                this.mc.bridge$displayGuiScreen(this.guiScreen);
                ((CBModulesGui) this.guiScreen).currentScrollableElement = ((CBModulesGui) this.guiScreen).profilesElement;
                break;
            }
            case 28: {
                if (this.textField.bridge$getText().length() < 3) {
                    this.IlIlIIIlllIIIlIlllIlIllIl = EnumChatFormattingBridge.RED + "Name must be at least 3 characters long.";
                    break;
                }
                if (this.textField.bridge$getText().equalsIgnoreCase("default")) {
                    this.IlIlIIIlllIIIlIlllIlIllIl = EnumChatFormattingBridge.RED + "That name is already in use.";
                    break;
                }
                if (!this.textField.bridge$getText().matches("([a-zA-Z0-9-_ \\]\\[]+)")) {
                    this.IlIlIIIlllIIIlIlllIlIllIl = EnumChatFormattingBridge.RED + "Illegal characters in name.";
                    break;
                }
                if (this.profile != null && this.profile.isEditable()) {
                    File file = new File(Ref.getMinecraft().bridge$getMcDataDir(), "config" + File.separator + "client" + File.separator + "profiles" + File.separator + this.profile.getName() + ".cfg");
                    File file2 = new File(Ref.getMinecraft().bridge$getMcDataDir(), "config" + File.separator + "client" + File.separator + "profiles" + File.separator + this.textField.bridge$getText() + ".cfg");
                    if (!file.exists()) break;
                    try {
                        Files.copy(file.toPath(), file2.toPath());
                        Files.delete(file.toPath());
                        this.profile.setName(this.textField.bridge$getText());
                        this.mc.bridge$displayGuiScreen(this.guiScreen);
                        ((CBModulesGui) this.guiScreen).currentScrollableElement = ((CBModulesGui) this.guiScreen).profilesElement;
                    } catch (Exception exception) {
                        this.IlIlIIIlllIIIlIlllIlIllIl = EnumChatFormattingBridge.RED + "Could not save profile.";
                        exception.printStackTrace();
                    }
                    break;
                }
                Profile ilIIlIIlIIlllIlIIIlIllIIl = null;
                for (Profile ilIIlIIlIIlllIlIIIlIllIIl2 : CheatBreaker.getInstance().profiles) {
                    if (!ilIIlIIlIIlllIlIIIlIllIIl2.getName().toLowerCase().equalsIgnoreCase(this.textField.bridge$getText()))
                        continue;
                    ilIIlIIlIIlllIlIIIlIllIIl = ilIIlIIlIIlllIlIIIlIllIIl2;
                    break;
                }
                if (ilIIlIIlIIlllIlIIIlIllIIl == null) {
                    CheatBreaker.getInstance().configManager.writeProfile(CheatBreaker.getInstance().activeProfile.getName());
                    Profile ilIIlIIlIIlllIlIIIlIllIIl3 = new Profile(this.textField.bridge$getText(), true);
                    CheatBreaker.getInstance().profiles.add(ilIIlIIlIIlllIlIIIlIllIIl3);
                    CheatBreaker.getInstance().activeProfile = ilIIlIIlIIlllIlIIIlIllIIl3;
                    this.parent.lIIIIlIIllIIlIIlIIIlIIllI.add(new ProfileElement(this.parent, this.IIIIllIlIIIllIlllIlllllIl, ilIIlIIlIIlllIlIIIlIllIIl3, this.IlllIIIlIlllIllIlIIlllIlI));
                    CheatBreaker.getInstance().configManager.writeProfile(CheatBreaker.getInstance().activeProfile.getName());
                    this.mc.bridge$displayGuiScreen(this.guiScreen);
                    ((CBModulesGui) this.guiScreen).currentScrollableElement = ((CBModulesGui) this.guiScreen).profilesElement;
                    break;
                }
                this.IlIlIIIlllIIIlIlllIlIllIl = EnumChatFormattingBridge.RED + "That name is already in use.";
                break;
            }
            default: {
                this.textField.bridge$textboxKeyTyped(c, n);
            }
        }
    }
}
