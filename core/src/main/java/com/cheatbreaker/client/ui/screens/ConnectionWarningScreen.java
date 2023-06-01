package com.cheatbreaker.client.ui.screens;

import com.cheatbreaker.bridge.client.gui.ScaledResolutionBridge;
import com.cheatbreaker.bridge.ref.Ref;
import com.cheatbreaker.bridge.wrapper.CBGuiScreen;
import com.cheatbreaker.client.config.types.UnrecommendedServer;
import com.cheatbreaker.client.ui.mainmenu.GradientTextButton;
import com.cheatbreaker.client.ui.mainmenu.MainMenuBase;
import com.cheatbreaker.client.ui.util.RenderUtil;
import com.cheatbreaker.main.utils.Callbacks;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class ConnectionWarningScreen extends MainMenuBase {
    private final CBGuiScreen parent;

    private final String serverHost;
    private final int serverPort;

    private final Callbacks.ConnectionCallback callback;

    private final String serverName;
    private final String noticeFull;
    private final List<String> noticeLines;

    private final GradientTextButton cancelButton = new GradientTextButton("CANCEL");
    private final GradientTextButton continueButton = new GradientTextButton("CONTINUE");

    public ConnectionWarningScreen(UnrecommendedServer server, CBGuiScreen parent, String serverHost, int serverPort,
                                   Callbacks.ConnectionCallback callback) {
        this.serverName = server.displayName;
        this.noticeFull = server.reasonForWarning;
        this.noticeLines = new ArrayList<>();
        this.parent = parent;
        this.serverHost = serverHost;
        this.serverPort = serverPort;
        this.callback = callback;
        this.renderExtraButtons = false;
    }

    public void initGui() {
        super.initGui();

        ScaledResolutionBridge resolution = Ref.getInstanceCreator().createScaledResolution();
        float screenWidth = resolution.bridge$getScaledWidth();
        float screenHeight = resolution.bridge$getScaledHeight();

        this.noticeLines.clear();
        this.noticeLines.add("§4§lHOLD UP!");
        this.noticeLines.add("");
        this.noticeLines.add("§7§oYou are trying to join: " + this.serverName);
        this.noticeLines.add("");
        float noticeWidth = screenWidth / 2f;
        String[] splitBySpace = this.noticeFull.split(" ");
        String currentLine = "";
        for (int x = 0; x < splitBySpace.length; x++) {
            if (!currentLine.equals("")) {
                currentLine += " ";
            }

            boolean append = Ref.getMinecraft().bridge$getFontRenderer().bridge$getStringWidth(currentLine)
                    + Ref.getMinecraft().bridge$getFontRenderer().bridge$getStringWidth(splitBySpace[x])
                    <= noticeWidth;

            if (append) {
                currentLine += splitBySpace[x];
            } else {
                this.noticeLines.add(currentLine);
                currentLine = splitBySpace[x];
            }
        }

        if (!currentLine.equals("")) {
            this.noticeLines.add(currentLine);
            currentLine = "";
        }

        float noticeHeight = 11f * this.noticeLines.size();

        this.cancelButton.setElementDimensions(
                screenWidth / 2f - 102f,
                screenHeight / 2f + ((noticeHeight + 10f + 12f + 5f) / 2f) - 12f - 5f,
                100f,
                12f
        );
        this.cancelButton.setClickAction(() -> {
            this.mc.bridge$getSoundHandler().bridge$playSound(Ref.getInstanceCreator().createSoundFromPSR(Ref.getInstanceCreator().createResourceLocation("gui.button.press"), 1.0f));
            this.mc.bridge$displayGuiScreen(this.parent);
        });

        this.continueButton.setElementDimensions(
                screenWidth / 2f + 2f,
                screenHeight / 2f + ((noticeHeight + 10f + 12f + 5f) / 2f) - 12f - 5f,
                100f,
                12f
        );
        this.continueButton.setClickAction(() -> {
            this.mc.bridge$getSoundHandler().bridge$playSound(Ref.getInstanceCreator().createSoundFromPSR(Ref.getInstanceCreator().createResourceLocation("gui.button.press"), 1.0f));
            this.callback.run(this.serverHost, this.serverPort);
        });
    }

    public void drawScreen(int mouseX, int mouseY, float delta) {
        Ref.getGlBridge().bridge$disableAlphaTest();
        Ref.getDrawingUtils().renderSkybox(this.panoramaImages, delta, this.width, this.height, panoramaTimer, this.panoramaBackgroundLocation);
        Ref.getGlBridge().bridge$enableAlphaTest();
        final float scaleFactor = getScaleFactor();
        Ref.getGlBridge().bridge$pushMatrix();
        Ref.getGlBridge().bridge$scale(scaleFactor, scaleFactor, scaleFactor);
        drawMenu(mouseX / scaleFactor, mouseY / scaleFactor);
        Ref.getGlBridge().bridge$popMatrix();
    }

    public void drawMenu(float mouseX, float mouseY) {
        super.drawMenu(mouseX, mouseY);

        double rectLeft = this.getScaledWidth() / 2 - this.getScaledWidth() / 4 - 5f;
        double rectTop = this.getScaledHeight() / 2 - ((11.0 * this.noticeLines.size()) + 10.0 + 12.0 + 5.0) / 2;
        double rectRight = this.getScaledWidth() / 2 + this.getScaledWidth() / 4 + 5f;
        double rectBottom = this.getScaledHeight() / 2 + ((11.0 * this.noticeLines.size()) + 10.0 + 12.0 + 5.0) / 2;
        RenderUtil.drawRoundedRect(rectLeft, rectTop, rectRight, rectBottom, 4d, 0x2F000000);

        float textX = this.getScaledWidth() / 2f;
        float textY = (float) rectTop + 5f;
        for (String line : this.noticeLines) {
            this.mc.bridge$getFontRenderer().bridge$drawString(line, textX - this.mc.bridge$getFontRenderer().bridge$getStringWidth(line) / 2f, textY, 0xFFFFFFFF);
            textY += 11f;
        }

        this.cancelButton.drawElement(mouseX, mouseY, true);
        this.continueButton.drawElement(mouseX, mouseY, true);
    }

    public void onMouseClicked(float mouseX, float mouseY, int button) {
        super.onMouseClicked(mouseX, mouseY, button);

        this.cancelButton.handleElementMouseClicked(mouseX, mouseY, button, true);
        this.continueButton.handleElementMouseClicked(mouseX, mouseY, button, true);
    }
}
