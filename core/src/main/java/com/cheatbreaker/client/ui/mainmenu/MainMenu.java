package com.cheatbreaker.client.ui.mainmenu;

import com.cheatbreaker.bridge.client.MinecraftBridge;
import com.cheatbreaker.bridge.ref.Ref;
import com.cheatbreaker.bridge.util.ResourceLocationBridge;
import com.cheatbreaker.main.CheatBreaker;
import com.cheatbreaker.client.ui.fading.CosineFade;
import com.cheatbreaker.client.ui.fading.MinMaxFade;
import com.cheatbreaker.client.ui.util.RenderUtil;
import org.lwjgl.opengl.GL11;

import java.awt.*;

public class MainMenu extends MainMenuBase {
    private final ResourceLocationBridge outerLogo = Ref.getInstanceCreator().createResourceLocation("client/logo_255_outer.png");
    private final ResourceLocationBridge innerLogo = Ref.getInstanceCreator().createResourceLocation("client/logo_108_inner.png");
    private GradientTextButton singleplayerButton = new GradientTextButton("SINGLEPLAYER");
    private GradientTextButton multiplayerButton = new GradientTextButton("MULTIPLAYER");
    private final MinMaxFade logoPositionFade = new MinMaxFade(750L);
    private final CosineFade logoTurnAmount;
    private final MinMaxFade loadingScreenBackgroundFade = new MinMaxFade(400L);
    private static int loadCount;

    public MainMenu() {
        this.logoTurnAmount = new CosineFade(4000L);
    }

    @Override
    public void updateScreen() {
        super.updateScreen();
        if (this.isFirstOpened() && !this.logoPositionFade.hasStartTime()) {
            this.logoPositionFade.reset();
        }
        if (!(this.isFirstOpened() && !this.logoPositionFade.IIIIllIIllIIIIllIllIIIlIl() || this.logoTurnAmount.hasStartTime())) {
            this.loadingScreenBackgroundFade.reset();
            this.logoTurnAmount.reset();
            this.logoTurnAmount.enableShouldResetOnceCalled();
        }
    }

    @Override
    public void initGui() {
        super.initGui();
        this.singleplayerButton.setElementSize(this.getScaledWidth() / 2.0f - (float)50, this.getScaledHeight() / 2.0f + (float)5, (float)100, 12);
        this.multiplayerButton.setElementSize(this.getScaledWidth() / 2.0f - (float)50, this.getScaledHeight() / 2.0f + (float)24, (float)100, 12);
        ++loadCount;
    }

    @Override
    public void drawMenu(float mouseX, float mouseY) {
        float logoY = this.isFirstOpened() ? this.logoPositionFade.getCurrentValue() : 1.0f;
        super.drawMenu(mouseX, mouseY);
        GL11.glColor4f(1.0f, 1.0f, 1.0f, 1.0f);
        this.singleplayerButton.drawElement(mouseX, mouseY, true);
        this.multiplayerButton.drawElement(mouseX, mouseY, true);

        Ref.modified$drawRect(this.singleplayerButton.getX() - (float) 20, this.getScaledHeight() / 2.0f - (float) 80, this.singleplayerButton.getX() + this.singleplayerButton.getWidth() + (float) 20, this.multiplayerButton.getY() + this.multiplayerButton.getHeight() + (float) 14, 0x2F000000);
        if (this.isFirstOpened() && this.loadingScreenBackgroundFade.getCurrentValue() != 1f) {
            Ref.modified$drawRect(0.0f, 0.0f, this.getScaledWidth(), this.getScaledHeight(), new Color(1f, 1f, 1f, 1f - this.loadingScreenBackgroundFade.getCurrentValue()).getRGB());
        }

        this.drawCheatBreakerLogo(this.getScaledWidth(), this.getScaledHeight(), logoY);

        float f5 = this.getScaledWidth() / 2.0f - (float)80;
        float f6 = this.getScaledHeight() - (float)40;
        //RenderUtil.drawTexturedModalRect(f5, f6, f5 + 160f, f6 + 10f, 8, new Color(218, 66, 83, (int)((float)255 * (1.0f - logoY))).getRGB());

        if (CheatBreaker.getInstance().isInDebugMode()) {
            this.wrapped$fontRendererObj.bridge$drawString("[p] " + RenderUtil.getTimeAccurateFrameRate() + " FPS (" + RenderUtil.getFrameTimeAsMs() + "ms/frame) ", 5, 55, -1);
            this.wrapped$fontRendererObj.bridge$drawString("[p] Min/Max FPS: " + RenderUtil.minFps + "/" + RenderUtil.maxFps, 5, 65, -1);
            this.wrapped$fontRendererObj.bridge$drawString("[i] Press \u00a7cF9 \u00a7fto reset the Min/Max values.", 5, 75, -1);
        }
    }

    private void drawCheatBreakerLogo(double dispWidth, double dispHeight, float f) {
        float halfSize = 27;
        double x = dispWidth / (double)2 - (double)halfSize;
        double y = dispHeight / (double)2 - (double)halfSize - (double)((float)35 * f);
        GL11.glPushMatrix();
        GL11.glColor4f(1.0f, 1.0f, 1.0f, 1.0f);
        GL11.glTranslatef((float)x, (float)y, 1.0f);
        GL11.glTranslatef(halfSize, halfSize, halfSize);
        GL11.glRotatef((float)180 * this.logoTurnAmount.getCurrentValue(), 0.0f, 0.0f, 1.0f);
        GL11.glTranslatef(-halfSize, -halfSize, -halfSize);
        RenderUtil.drawIcon(this.outerLogo, halfSize, 0.0f, 0.0f);
        GL11.glPopMatrix();
        RenderUtil.drawIcon(this.innerLogo, halfSize, (float)x, (float)y);
    }

    @Override
    public void onMouseClicked(float mouseX, float mouseY, int button) {
        super.onMouseClicked(mouseX, mouseY, button);
        this.singleplayerButton.handleElementMouseClicked(mouseX, mouseY, button, true);
        this.multiplayerButton.handleElementMouseClicked(mouseX, mouseY, button, true);
        if (this.singleplayerButton.isMouseInside(mouseX, mouseY)) {
            this.wrapped$mc.bridge$getSoundHandler().bridge$playSound(Ref.getInstanceCreator().createSoundFromPSR(Ref.getInstanceCreator().createResourceLocation("gui.button.press"), 1.0f));
            this.wrapped$mc.bridge$displayInternalGuiScreen(MinecraftBridge.InternalScreen.SINGLEPLAYER, this);
        } else if (this.multiplayerButton.isMouseInside(mouseX, mouseY)) {
            this.wrapped$mc.bridge$getSoundHandler().bridge$playSound(Ref.getInstanceCreator().createSoundFromPSR(Ref.getInstanceCreator().createResourceLocation("gui.button.press"), 1.0f));
            this.wrapped$mc.bridge$displayInternalGuiScreen(MinecraftBridge.InternalScreen.MULTIPLAYER, this);
        }
    }

    public boolean isFirstOpened() {
        return loadCount == 1;
    }
}
