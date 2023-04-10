package com.cheatbreaker.mixin.client.gui;

import com.cheatbreaker.bridge.ref.Ref;
import com.cheatbreaker.bridge.util.ResourceLocationBridge;
import com.cheatbreaker.main.CheatBreaker;
import com.cheatbreaker.client.ui.fading.CosineFade;
import com.cheatbreaker.client.ui.mainmenu.MainMenu;
import com.cheatbreaker.client.ui.module.CBModulesGui;
import com.cheatbreaker.client.ui.util.RenderUtil;
import com.cheatbreaker.client.ui.util.font.FontRegistry;
import com.cheatbreaker.client.util.SessionServer;
import net.minecraft.client.gui.*;
import net.minecraft.client.gui.achievement.GuiAchievements;
import net.minecraft.client.gui.achievement.GuiStats;
import net.minecraft.client.resources.I18n;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

import java.awt.*;

@Mixin(GuiIngameMenu.class)
public class MixinGuiIngameMenu extends GuiScreen {
    @Shadow private int saveStep;

    /**
     * @author iAmSpace
     * @reason Custom pause menu
     */
    @Overwrite
    public void initGui()
    {
        this.saveStep = 0;
        this.buttonList.clear();
        byte var1 = -16;
        boolean var2 = true;
        this.buttonList.add(new GuiButton(1, this.width / 2 - 100, this.height / 4 + 120 + var1, I18n.format("menu.returnToMenu")));

        if (!this.mc.isIntegratedServerRunning())
            ((GuiButton)this.buttonList.get(0)).displayString = I18n.format("menu.disconnect");

        this.buttonList.add(new GuiButton(4, this.width / 2 - 100, this.height / 4 + 24 + var1, I18n.format("menu.returnToGame")));
        this.buttonList.add(new GuiButton(0, this.width / 2 - 100, this.height / 4 + 96 + var1, 98, 20, I18n.format("menu.options")));
        GuiButton var3 = new GuiButton(7, this.width / 2 + 2, this.height / 4 + 96 + var1, 98, 20, I18n.format("menu.shareToLan"));
        this.buttonList.add(new GuiButton(5, this.width / 2 - 100, this.height / 4 + 48 + var1, 98, 20, I18n.format("gui.achievements")));
        this.buttonList.add(new GuiButton(6, this.width / 2 + 2, this.height / 4 + 48 + var1, 98, 20, I18n.format("gui.stats")));
        var3.enabled = this.mc.isSingleplayer() && !this.mc.getIntegratedServer().getPublic();

        if (!var3.enabled) {
            this.buttonList.add(new GuiButton(10, this.width / 2 + 2, this.height / 4 + 96 + var1, 98, 20, "Mods"));
            this.buttonList.add(new GuiButton(16, this.width / 2 - 100, this.height / 4 + 72 + var1, 200, 20, "Server List"));
        } else {
            this.buttonList.add(var3);
            this.buttonList.add(new GuiButton(16, this.width / 2 - 100, this.height / 4 + 72 + var1, 98, 20, "Server List"));
            this.buttonList.add(new GuiButton(10, this.width / 2 + 2, this.height / 4 + 72 + var1, 98, 20, "Mods"));
        }
    }

    /**
     * @author iAmSpace
     * @reason Custom pause menu
     */
    @Overwrite
    protected void actionPerformed(GuiButton button) {
        switch (button.id) {
            case 0:
                this.mc.displayGuiScreen(new GuiOptions(this, this.mc.gameSettings));
                break;
            case 1:
                button.enabled = false;
                this.mc.theWorld.sendQuittingDisconnectingPacket();
                this.mc.loadWorld(null);
                Ref.getMinecraft().bridge$displayGuiScreen(new MainMenu());
                break;
            case 4:
                this.mc.displayGuiScreen(null);
                this.mc.setIngameFocus();
                break;
            case 5:
                this.mc.displayGuiScreen(new GuiAchievements(this, this.mc.thePlayer.getStatFileWriter()));
                break;
            case 6:
                this.mc.displayGuiScreen(new GuiStats(this, this.mc.thePlayer.getStatFileWriter()));
                break;
            case 7:
                this.mc.displayGuiScreen(new GuiShareToLan(this));
                break;
            case 10:
                Ref.getMinecraft().bridge$displayGuiScreen(new CBModulesGui());
                break;
            case 16:
                this.mc.displayGuiScreen(new GuiMultiplayer(this));
                break;
        }
    }

    /**
     * @author iAmSpace
     * @reason Custom pause menu
     */
    @Overwrite
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        this.drawDefaultBackground();
        int n3 = 600;
        int n4 = 356;
        double d = (double)Math.min(this.width, this.height) / ((double)n3 * (double)9);
        this.renderRotatingLogo(this.width, this.height);
        boolean bl = false;
        for (SessionServer server : CheatBreaker.getInstance().statusServers) {
            if (server.getStatus() != SessionServer.Status.UP) continue;
            bl = true;
        }
        if (bl) {
            if (!this.errorMsgFade.hasStartTime()) {
                this.errorMsgFade.reset();
            }
            this.errorMsgFade.enableShouldResetOnceCalled();

            Ref.modified$drawRect(this.width / 2f - 100, this.height / 4f + 128, this.width / 2f + 100, this.height / 4f + 142, 0x6F000000);
            Ref.modified$drawRect(this.width / 2f - 100, this.height / 4f + 128, this.width / 2f + 100, this.height / 4f + 142, new Color(1.0f, 0.15f, 0.15f, 0.65f * this.errorMsgFade.getCurrentValue()).getRGB());
            FontRegistry.getUbuntuMedium16px().drawCenteredString("Some login services might be offline".toUpperCase(), this.width / 2f, this.height / 4f + 130, -1);
        }
        super.drawScreen(mouseX, mouseY, partialTicks);
    }

    // Logo stuff
    private final ResourceLocationBridge outerLogo = Ref.getInstanceCreator().createResourceLocation("client/logo_255_outer.png");
    private final ResourceLocationBridge innerLogo = Ref.getInstanceCreator().createResourceLocation("client/logo_108_inner.png");
    private final CosineFade rotatingFade = new CosineFade(4000L);
    private final CosineFade errorMsgFade = new CosineFade(1500L);

    private void renderRotatingLogo(double d, double d2) {
        try {
            if (!this.rotatingFade.hasStartTime()) {
                this.rotatingFade.reset();
                this.rotatingFade.enableShouldResetOnceCalled();
            }
            float f = 18;
            double d3 = d / (double)2 - (double)f;
            double d4 = this.buttonList.size() > 2 ? (double)(((GuiButton)this.buttonList.get(1)).yPosition - f - (float)32) : (double)-100;
            Ref.getGlBridge().bridge$pushMatrix();
            Ref.getGlBridge().bridge$color(1.0f, 1.0f, 1.0f, 1.0f);
            Ref.getGlBridge().bridge$translate((float)d3, (float)d4, 1.0f);
            Ref.getGlBridge().bridge$translate(f, f, f);
            Ref.getGlBridge().bridge$rotate((float)180 * this.rotatingFade.getCurrentValue(), 0.0f, 0.0f, 1.0f);
            Ref.getGlBridge().bridge$translate(-f, -f, -f);
            RenderUtil.drawIcon(this.outerLogo, f, 0.0f, 0.0f);
            Ref.getGlBridge().bridge$popMatrix();
            RenderUtil.drawIcon(this.innerLogo, f, (float)d3, (float)d4);
        }
        catch (Exception exception) {
            // empty catch block
        }
    }
}