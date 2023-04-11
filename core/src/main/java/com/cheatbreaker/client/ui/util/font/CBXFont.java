//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.cheatbreaker.client.ui.util.font;

import com.cheatbreaker.bridge.client.renderer.TessellatorBridge;
import com.cheatbreaker.bridge.ref.Ref;
import com.cheatbreaker.bridge.util.ResourceLocationBridge;

import javax.swing.*;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.InputStream;

public class CBXFont {
    private float imgSize = 1048f;
    protected CharData[] charData = new CharData[256];
    protected Font font;
    protected boolean antiAlias;
    protected boolean fractionalMetrics;
    protected int fontHeight = -1;
    protected int charOffset = 0;
    protected int tex;
    private final ResourceLocationBridge resourceLocation;
    public final float size;

    public CBXFont(ResourceLocationBridge location, float size) {
        this.resourceLocation = location;
        this.size = size;
        this.antiAlias = true;
        this.fractionalMetrics = true;
    }

    public boolean isLoaded() {
        Font var1;
        try {
            InputStream var2;
            if (Ref.getMinecraft() != null && Ref.getMinecraft().bridge$getResourceManager() != null) {
                var2 = Ref.getMinecraft().bridge$getResourceManager().bridge$getResource(this.resourceLocation).bridge$getInputStream();
            } else {
                var2 = Ref.getMinecraft().bridge$getDefaultResourcePack().bridge$getInputStream(this.resourceLocation);
            }

            var1 = Font.createFont(0, var2).deriveFont(this.size);
        } catch (Exception var3) {
            JOptionPane.showMessageDialog(null,
                    "Failed to load font \"" + this.resourceLocation.bridge$getResourceDomain() + ":" + this.resourceLocation.bridge$getResourcePath() + "\" - using fallback Arial.",
                    "CheatBreakerX", JOptionPane.ERROR_MESSAGE);
            var1 = new Font("Arial", Font.PLAIN, (int)this.size);
        }

        this.font = var1;
        this.tex = this.setupTexture(this.font, this.antiAlias, this.fractionalMetrics, this.charData);
        return true;
    }

    public String getTranslation() {
        return this.resourceLocation.bridge$getResourcePath();
    }

    protected int setupTexture(Font var1, boolean var2, boolean var3, CharData[] var4) {
        BufferedImage fontImage = this.generateFontImage(var1, var2, var3, var4);
        int textureID = Ref.getImplementations().getTextureUtil().bridge$glGenTextures();
        Ref.getImplementations().getTextureUtil().bridge$uploadTextureImage(textureID, fontImage);
        return textureID;
    }

    private BufferedImage generateFontImage(Font var1, boolean var2, boolean var3, CharData[] var4) {
        int var5 = (int)this.imgSize;
        BufferedImage var6 = new BufferedImage(var5, var5, 2);
        Graphics2D var7 = (Graphics2D)var6.getGraphics();
        var7.setFont(var1);
        var7.setColor(new Color(255, 255, 255, 0));
        var7.fillRect(0, 0, var5, var5);
        var7.setColor(Color.WHITE);
        var7.setRenderingHint(RenderingHints.KEY_FRACTIONALMETRICS, var3 ? RenderingHints.VALUE_FRACTIONALMETRICS_ON : RenderingHints.VALUE_FRACTIONALMETRICS_OFF);
        var7.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, var2 ? RenderingHints.VALUE_TEXT_ANTIALIAS_ON : RenderingHints.VALUE_TEXT_ANTIALIAS_OFF);
        var7.setRenderingHint(RenderingHints.KEY_ANTIALIASING, var2 ? RenderingHints.VALUE_ANTIALIAS_ON : RenderingHints.VALUE_ANTIALIAS_OFF);
        FontMetrics var8 = var7.getFontMetrics();
        int var9 = 0;
        int var10 = 0;
        int var11 = 1;

        for(int var12 = 0; var12 < var4.length; ++var12) {
            char var13 = (char)var12;
            CharData var14 = new CharData();
            Rectangle2D var15 = var8.getStringBounds(String.valueOf(var13), var7);
            var14.width = var15.getBounds().width + 8;
            var14.height = var15.getBounds().height;
            if (var10 + var14.width >= var5) {
                var10 = 0;
                var11 += var9;
                var9 = 0;
            }

            if (var14.height > var9) {
                var9 = var14.height;
            }

            var14.storedX = var10;
            var14.storedY = var11;
            if (var14.height > this.fontHeight) {
                this.fontHeight = var14.height;
            }

            var4[var12] = var14;
            var7.drawString(String.valueOf(var13), var10 + 2, var11 + var8.getAscent());
            var10 += var14.width;
        }

        return var6;
    }

    protected void drawChar(CharData[] var2, char var3, float var4, float var5, int var6) {
        TessellatorBridge tessellator = Ref.getTessellator().bridge$begin(4, "POSITION_TEX");

        try {
            this.drawQuad(tessellator, var4, var5, (float)var2[var3].width, (float)var2[var3].height, (float)var2[var3].storedX, (float)var2[var3].storedY, (float)var2[var3].width, (float)var2[var3].height);
        } catch (Exception var9) {
            var9.printStackTrace();
        }

        tessellator.bridge$finish();
    }

    private void drawQuad(TessellatorBridge var1, float x, float y, float width, float height, float srcX, float srcY, float srcWidth, float srcHeight) {
        float renderSrcX = srcX / this.imgSize;
        float renderSrcY = srcY / this.imgSize;
        float renderSrcWidth = srcWidth / this.imgSize;
        float renderSrcHeight = srcHeight / this.imgSize;

        var1.bridge$pos(x + width, y).bridge$tex(renderSrcX + renderSrcWidth, renderSrcY).bridge$endVertex();
        var1.bridge$pos(x, y).bridge$tex(renderSrcX, renderSrcY).bridge$endVertex();
        var1.bridge$pos(x, y + height).bridge$tex(renderSrcX, renderSrcY + renderSrcHeight).bridge$endVertex();
        var1.bridge$pos(x, y + height).bridge$tex(renderSrcX, renderSrcY + renderSrcHeight).bridge$endVertex();
        var1.bridge$pos(x + width, y + height).bridge$tex(renderSrcX + renderSrcWidth, renderSrcY + renderSrcHeight).bridge$endVertex();
        var1.bridge$pos(x + width, y).bridge$tex(renderSrcX + renderSrcWidth, renderSrcY).bridge$endVertex();
    }

    public int getHeight(String var1) {
        return this.getHeight();
    }

    public int getHeight() {
        return (this.fontHeight - 8) / 2;
    }

    public int getStringWidth(String var1) {
        int var2 = 0;
        char[] var3 = var1.toCharArray();
        int var4 = var3.length;

        for (int var5 = 0; var5 < var4; ++var5) {
            char var6 = var3[var5];
            if (var6 < this.charData.length && var6 >= 0) {
                var2 += this.charData[var6].width - 8 + this.charOffset;
            }
        }

        return var2 / 2;
    }

    public boolean isAntiAlias() {
        return this.antiAlias;
    }

    public void setAntiAlias(boolean var1) {
        if (this.antiAlias != var1) {
            this.antiAlias = var1;
            Ref.getImplementations().getTextureUtil().bridge$deleteTexture(this.tex);
            this.tex = this.setupTexture(this.font, var1, this.fractionalMetrics, this.charData);
        }
    }

    public boolean isFractionalMetrics() {
        return this.fractionalMetrics;
    }

    public void setFractionalMetrics(boolean var1) {
        if (this.fractionalMetrics != var1) {
            this.fractionalMetrics = var1;
            Ref.getImplementations().getTextureUtil().bridge$deleteTexture(this.tex);
            this.tex = this.setupTexture(this.font, this.antiAlias, var1, this.charData);
        }
    }

    public Font getFont() {
        return this.font;
    }

    public void setFont(Font var1) {
        this.font = var1;
        Ref.getImplementations().getTextureUtil().bridge$deleteTexture(this.tex);
        this.tex = this.setupTexture(var1, this.antiAlias, this.fractionalMetrics, this.charData);
    }

    public class CharData {
        public int width;
        public int height;
        public int storedX;
        public int storedY;

        protected CharData() {
        }
    }
}
