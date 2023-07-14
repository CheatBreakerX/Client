package com.cheatbreaker.client.ui.mainmenu;

import com.cheatbreaker.bridge.client.MinecraftBridge;
import com.cheatbreaker.bridge.ref.Ref;
import com.cheatbreaker.bridge.util.ResourceLocationBridge;
import com.cheatbreaker.main.CheatBreaker;
import com.cheatbreaker.client.ui.fading.CosineFade;
import com.cheatbreaker.client.ui.fading.MinMaxFade;
import com.cheatbreaker.client.ui.util.RenderUtil;

import java.awt.*;

public class MainMenu extends MainMenuBase {
    private final ResourceLocationBridge outerLogo = Ref.getInstanceCreator().createResourceLocation("client/logo_255_outer_remake.png");
    private final ResourceLocationBridge innerLogo = Ref.getInstanceCreator().createResourceLocation("client/logo_108_inner_remake.png");
    private final GradientTextButton singleplayerButton = new GradientTextButton("SINGLEPLAYER");
    private final GradientTextButton multiplayerButton = new GradientTextButton("MULTIPLAYER");
    private final GradientTextButton realmsButton = new GradientTextButton("REALMS");
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
        if (!(this.isFirstOpened() && !this.logoPositionFade.isExpired() || this.logoTurnAmount.hasStartTime())) {
            this.loadingScreenBackgroundFade.reset();
            this.logoTurnAmount.reset();
            this.logoTurnAmount.enableShouldResetOnceCalled();
        }
    }

    @Override
    public void initGui() {
        super.initGui();
        this.singleplayerButton.setElementDimensions(this.getScaledWidth() / 2f - 50f, this.getScaledHeight() / 2f + 5f, 100f, 12f);
        this.singleplayerButton.setClickAction(() -> {
            this.mc.bridge$getSoundHandler().bridge$playSound(Ref.getInstanceCreator().createSoundFromPSR(Ref.getInstanceCreator().createResourceLocation("gui.button.press"), 1.0f));
            this.mc.bridge$displayInternalGuiScreen(MinecraftBridge.InternalScreen.SINGLEPLAYER, this);
        });

        this.multiplayerButton.setElementDimensions(this.getScaledWidth() / 2f - 50f, this.getScaledHeight() / 2f + 24f, 100f, 12f);
        this.multiplayerButton.setClickAction(() -> {
            this.mc.bridge$getSoundHandler().bridge$playSound(Ref.getInstanceCreator().createSoundFromPSR(Ref.getInstanceCreator().createResourceLocation("gui.button.press"), 1.0f));
            this.mc.bridge$displayInternalGuiScreen(MinecraftBridge.InternalScreen.MULTIPLAYER, this);
        });

        if (Ref.getMinecraftVersion().isLatestVersion()) {
            this.realmsButton.setElementDimensions(this.getScaledWidth() / 2f - 50f, this.getScaledHeight() / 2f + 43f, 100f, 12f);
            this.realmsButton.setClickAction(() -> {
                this.mc.bridge$getSoundHandler().bridge$playSound(Ref.getInstanceCreator().createSoundFromPSR(Ref.getInstanceCreator().createResourceLocation("gui.button.press"), 1.0f));
                this.mc.bridge$displayInternalGuiScreen(MinecraftBridge.InternalScreen.REALMS, this);
            });
        }
        ++loadCount;
    }

    @Override
    public void drawMenu(float mouseX, float mouseY) {
        float logoY = this.isFirstOpened() ? this.logoPositionFade.getCurrentValue() : 1.0f;
        super.drawMenu(mouseX, mouseY);
        Ref.getGlBridge().bridge$color(1.0f, 1.0f, 1.0f, 1.0f);
        this.singleplayerButton.drawElement(mouseX, mouseY, true);
        this.multiplayerButton.drawElement(mouseX, mouseY, true);
        if (Ref.getMinecraftVersion().isLatestVersion()) {
            this.realmsButton.drawElement(mouseX, mouseY, true);
        }

        GradientTextButton topButton = this.singleplayerButton;
        GradientTextButton bottomButton = Ref.getMinecraftVersion().isLatestVersion() ? this.realmsButton : this.multiplayerButton;
        Ref.modified$drawRect(topButton.getX() - (float) 20, this.getScaledHeight() / 2.0f - (float) 80, topButton.getX() + topButton.getWidth() + (float) 20, bottomButton.getY() + bottomButton.getHeight() + (float) 14, 0x2F000000);

        if (this.isFirstOpened() && this.loadingScreenBackgroundFade.getCurrentValue() != 1f) {
            Ref.modified$drawRect(0f, 0f, this.getScaledWidth(), this.getScaledHeight(), new Color(1f, 1f, 1f, 1f - this.loadingScreenBackgroundFade.getCurrentValue()).getRGB());
        }

        this.drawCheatBreakerLogo(this.getScaledWidth(), this.getScaledHeight(), logoY);

        if (CheatBreaker.getInstance().isInDebugMode()) {
            this.fontRendererObj.bridge$drawString("[p] " + RenderUtil.getTimeAccurateFrameRate() + " FPS (" + RenderUtil.getFrameTimeAsMs() + "ms/frame) ", 5, 55, -1);
            this.fontRendererObj.bridge$drawString("[p] Min/Max FPS: " + RenderUtil.minFps + "/" + RenderUtil.maxFps, 5, 65, -1);
            this.fontRendererObj.bridge$drawString("[i] Press §cF9 §fto reset the Min/Max values.", 5, 75, -1);
        }
    }

    private void drawCheatBreakerLogo(double dispWidth, double dispHeight, float f) {
        float halfSize = 27;
        double x = dispWidth / (double)2 - (double)halfSize;
        double y = dispHeight / (double)2 - (double)halfSize - (double)((float)35 * f);
        Ref.getGlBridge().bridge$pushMatrix();
        Ref.getGlBridge().bridge$color(1.0f, 1.0f, 1.0f, 1.0f);
        Ref.getGlBridge().bridge$translate((float)x, (float)y, 1.0f);
        Ref.getGlBridge().bridge$translate(halfSize, halfSize, halfSize);
        Ref.getGlBridge().bridge$rotate((float)180 * this.logoTurnAmount.getCurrentValue(), 0.0f, 0.0f, 1.0f);
        Ref.getGlBridge().bridge$translate(-halfSize, -halfSize, -halfSize);
        RenderUtil.drawIcon(this.outerLogo, halfSize, 0f, 0f);
        Ref.getGlBridge().bridge$popMatrix();
        RenderUtil.drawIcon(this.innerLogo, halfSize, (float)x, (float)y);
    }

    @Override
    public void onMouseClicked(float mouseX, float mouseY, int button) {
        super.onMouseClicked(mouseX, mouseY, button);
        this.singleplayerButton.handleElementMouseClicked(mouseX, mouseY, button, true);
        this.multiplayerButton.handleElementMouseClicked(mouseX, mouseY, button, true);
        if (Ref.getMinecraftVersion().isLatestVersion()) {
            this.realmsButton.handleElementMouseClicked(mouseX, mouseY, button, true);
        }
    }

    public boolean isFirstOpened() {
        return loadCount == 1;
    }
}
