package com.cheatbreaker.client.ui.mainmenu;

import com.cheatbreaker.bridge.client.MinecraftBridge;
import com.cheatbreaker.bridge.client.gui.ScaledResolutionBridge;
import com.cheatbreaker.bridge.client.shader.FrameBufferBridge;
import com.cheatbreaker.bridge.ref.Ref;
import com.cheatbreaker.bridge.util.ResourceLocationBridge;
import com.cheatbreaker.client.ui.AbstractGui;
import com.cheatbreaker.client.ui.util.RenderUtil;
import com.cheatbreaker.client.ui.util.font.CBFontRenderer;

import java.awt.*;

public class LoadingScreen extends AbstractGui {

    private final ResourceLocationBridge logo = Ref.getInstanceCreator().createResourceLocation("client/logo_108.png");
    private final MinecraftBridge mc = Ref.getMinecraft();
    private final ScaledResolutionBridge res;
    private final FrameBufferBridge frameBuffer;
    private final int amountOfCalls;
    private int amountOfCallsDone;
    private String message;
    private final CBFontRenderer font = new CBFontRenderer(Ref.getInstanceCreator().createResourceLocation("client/font/Ubuntu-M.ttf"), 16);

    public LoadingScreen(int capacity) {
        this.amountOfCalls = capacity;
        this.res = Ref.getInstanceCreator().createScaledResolution();
        int n2 = this.res.bridge$getScaleFactor();
        this.frameBuffer = Ref.getInstanceCreator().createFrameBuffer(res.bridge$getScaledWidth() * n2, res.bridge$getScaledHeight() * n2, true);
    }

    public void newMessage(String string) {
        this.message = string;
        ++this.amountOfCallsDone;
        this.drawMenu(0.0f, 0.0f);
    }

    private void drawLogo(double width, double height) {
        float size = 27.0f;
        double x = width / 2.0 - (double) size;
        double y = height / 2.0 - (double) size;
        RenderUtil.drawIcon(this.logo, size, (float) x, (float) y);
    }

    private void clearMenu() {
        this.frameBuffer.bridge$bindFramebuffer(false);
        Ref.getGlBridge().bridge$matrixMode(5889);
        Ref.getGlBridge().bridge$loadIdentity();
        Ref.getGlBridge().bridge$ortho(0, res.bridge$getScaledWidth(), res.bridge$getScaledHeight(), 0, 1000, 3000);
        Ref.getGlBridge().bridge$matrixMode(5888);
        Ref.getGlBridge().bridge$loadIdentity();
        Ref.getGlBridge().bridge$translate(0f, 0f, -2000f);
        Ref.getGlBridge().bridge$disableLighting();
        Ref.getGlBridge().bridge$disableFog();
        Ref.getGlBridge().bridge$enableDepthTest();
        Ref.getGlBridge().bridge$enableTexture2D();
        Ref.getGlBridge().bridge$enableAlphaTest();
    }

    private void getMenuReady() {
        int n = this.res.bridge$getScaleFactor();
        this.frameBuffer.bridge$unbindFramebuffer();
        this.frameBuffer.bridge$framebufferRender(this.res.bridge$getScaledWidth() * n, this.res.bridge$getScaledHeight() * n);
        Ref.getGlBridge().bridge$alphaFunc(516, 0.1F);
        Ref.getGlBridge().bridge$flush();
    }

    @Override
    public void drawMenu(float mouseX, float mouseY) {
        clearMenu();
        double scaledWidth = res.bridge$getScaledWidth_double();
        double scaledHeight = res.bridge$getScaledHeight_double();
        Ref.modified$drawRect(0.0f, 0.0f, (float) scaledWidth, (float) scaledHeight, -1);
        drawLogo(scaledWidth, scaledHeight);
        float width = 160.0f; // width of the progressbar
        float height = 10.0F; // height of the progressbar
        float x = (float) scaledWidth / 2.0f - 80.0f; // x position of the progressbar
        float y = (float) scaledHeight - 40.0f; // y position of the progressbar
        RenderUtil.drawRoundedRect(x, y, x + width, y + height, 8.0, new Color(245, 245, 245, 255).getRGB());
        float loadedWidth = width * ((float) amountOfCallsDone / (float) amountOfCalls);
        if (message != null) {
            font.drawCenteredString(message.toLowerCase(), (float) scaledWidth / 2.0f, y - 11.0f, -3092272);
        }
        RenderUtil.drawRoundedRect(x, y, x + loadedWidth, y + height, 8.0, new Color(218, 66, 83, 255).getRGB());
        this.getMenuReady();
        this.mc.bridge$func_147120_f(); // resetSize
    }

    @Override
    protected void onMouseClicked(float mouseX, float mouseY, int button) {
    }

    @Override
    public void onMouseMovedOrUp(float mouseX, float mouseY, int button) {
    }


}
