package com.cheatbreaker.mixin.client.gui;

import com.cheatbreaker.bridge.client.gui.FontRendererBridge;
import net.minecraft.client.gui.FontRenderer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(FontRenderer.class)
public abstract class MixinFontRenderer implements FontRendererBridge {
    @Shadow private int[] colorCode;
    @Shadow protected abstract void enableAlpha();
    @Shadow protected abstract void resetStyles();
    @Shadow public abstract int getStringWidth(String text);
    @Shadow private boolean bidiFlag;
    @Shadow protected abstract String bidiReorder(String text);
    @Shadow private float red;
    @Shadow private float blue;
    @Shadow private float green;
    @Shadow private float alpha;
    @Shadow protected abstract void setColor(float r, float g, float b, float a);
    @Shadow protected float posX;
    @Shadow protected float posY;
    @Shadow protected abstract void renderStringAtPos(String text, boolean shadow);

    public int[] bridge$getColorCode() {
        return this.colorCode;
    }

    public void bridge$drawString(String string, float x, float y, int color) {
        this.enableAlpha();
        this.resetStyles();

        this.renderString_float(string, x, y, color, false);
    }

    public int bridge$getStringWidth(String string) {
        return this.getStringWidth(string);
    }

    public int bridge$drawStringWithShadow(String text, float x, float y, int color) {
        enableAlpha();
        this.resetStyles();
        int i;

        i = this.renderString_float(text, x + 1.0F, y + 1.0F, color, true);
        i = Math.max(i, this.renderString_float(text, x, y, color, false));

        return i;
    }

    private int renderString_float(String string, float x, float y, int color, boolean shadow)
    {
        if (string == null) {
            return 0;
        } else {
            if (this.bidiFlag)
                string = this.bidiReorder(string);

            if ((color & -67108864) == 0)
                color |= -16777216;

            if (shadow)
                color = (color & 16579836) >> 2 | color & -16777216;

            this.red = (float)(color >> 16 & 255) / 255.0F;
            this.blue = (float)(color >> 8 & 255) / 255.0F;
            this.green = (float)(color & 255) / 255.0F;
            this.alpha = (float)(color >> 24 & 255) / 255.0F;
            this.setColor(this.red, this.blue, this.green, this.alpha);
            this.posX = x;
            this.posY = y;
            this.renderStringAtPos(string, shadow);
            return (int)this.posX;
        }
    }
}
