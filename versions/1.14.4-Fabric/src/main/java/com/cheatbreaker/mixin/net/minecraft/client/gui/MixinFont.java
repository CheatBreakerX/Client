package com.cheatbreaker.mixin.net.minecraft.client.gui;

import com.cheatbreaker.bridge.client.gui.FontRendererBridge;
import com.mojang.blaze3d.platform.GlStateManager;
import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.Font;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(Font.class)
public abstract class MixinFont implements FontRendererBridge {
    @Shadow protected abstract float renderText(String string, float f, float g, int i, boolean bl);
    @Shadow public abstract int width(String string);

    public int bridge$getColorForID(int id) {
        ChatFormatting format = ChatFormatting.getById(id);
        if (format != null) {
            Integer color = format.getColor();
            return color == null ? 0 : color;
        }

        return 0;
    }

    public void bridge$drawString(String string, float x, float y, int color) {
        GlStateManager.enableAlphaTest();
        this.renderText(string, x, y, color, false);
    }

    public int bridge$getStringWidth(String string) {
        return this.width(string);
    }

    public int bridge$drawStringWithShadow(String message, float x, float y, int color) {
        GlStateManager.enableAlphaTest();
        float f;

        f = this.renderText(message, x + 1.0F, y + 1.0F, color, true);
        f = Math.max(f, this.renderText(message, x, y, color, false));

        return (int) f;
    }
}
