package com.cheatbreaker.client.ui.mainmenu;

import com.cheatbreaker.bridge.Ref;
import com.cheatbreaker.client.ui.AbstractGui;
import com.cheatbreaker.client.ui.util.RenderUtil;
import com.cheatbreaker.client.ui.util.font.CBFontRenderer;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.shader.Framebuffer;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

import java.awt.*;

public class LoadingScreen extends AbstractGui {

    private final ResourceLocation logo = new ResourceLocation("client/logo_108.png");
    private final Minecraft mc = Minecraft.getMinecraft();
    private final ScaledResolution res;
    private final Framebuffer frameBuffer;
    private final int amountOfCalls;
    private int amountOfCallsDone;
    private String message;
    private final CBFontRenderer font = new CBFontRenderer(new ResourceLocation("client/font/Ubuntu-M.ttf"), 16);

    public LoadingScreen(int capacity) {
        this.amountOfCalls = capacity;
        this.res = new ScaledResolution(mc, mc.displayWidth, mc.displayHeight);
        int n2 = this.res.getScaleFactor();
        this.frameBuffer = new Framebuffer(res.getScaledWidth() * n2, res.getScaledHeight() * n2, true);
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
        this.frameBuffer.bindFramebuffer(false);
        GL11.glMatrixMode(GL11.GL_PROJECTION);
        GL11.glLoadIdentity();
        GL11.glOrtho(0.0, res.getScaledWidth(), res.getScaledHeight(), 0.0, 1000.0, 3000.0);
        GL11.glMatrixMode(GL11.GL_MODELVIEW);
        GL11.glLoadIdentity();
        GL11.glTranslatef(0.0f, 0.0f, -2000.0f);
        GL11.glDisable(GL11.GL_LIGHTING);
        GL11.glDisable(GL11.GL_FOG);
        GL11.glDisable(GL11.GL_DEPTH_TEST);
        GL11.glEnable(GL11.GL_TEXTURE_2D);
        GL11.glEnable(GL11.GL_ALPHA_TEST);
    }

    private void getMenuReady() {
        int n = this.res.getScaleFactor();
        this.frameBuffer.unbindFramebuffer();
        this.frameBuffer.framebufferRender(this.res.getScaledWidth() * n, this.res.getScaledHeight() * n);
        GL11.glAlphaFunc(GL11.GL_GREATER, 0.1F);
        GL11.glFlush();
    }

    @Override
    public void drawMenu(float mouseX, float mouseY) {
        clearMenu();
        double scaledWidth = res.getScaledWidth_double();
        double scaledHeight = res.getScaledHeight_double();
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
        this.mc.func_147120_f(); // resetSize
    }

    @Override
    protected void onMouseClicked(float mouseX, float mouseY, int button) {
    }

    @Override
    public void onMouseMovedOrUp(float mouseX, float mouseY, int button) {
    }


}
