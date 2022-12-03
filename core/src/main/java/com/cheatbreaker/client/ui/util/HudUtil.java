package com.cheatbreaker.client.ui.util;

import com.cheatbreaker.bridge.client.gui.FontRendererBridge;
import com.cheatbreaker.bridge.client.renderer.TessellatorBridge;
import com.cheatbreaker.bridge.entity.player.EntityPlayerBridge;
import com.cheatbreaker.bridge.item.ItemBridge;
import com.cheatbreaker.bridge.item.ItemStackBridge;
import com.cheatbreaker.bridge.ref.Ref;

public final class HudUtil {

    public static void drawTexturedModalRect(int x, int y, int u, int v, int width, int height, float zLevel) {
        float var7 = 0.00390625F;
        float var8 = 0.00390625F;
        TessellatorBridge tessellator = Ref.getTessellator();
        tessellator.bridge$startDrawingQuads();
        tessellator.bridge$addVertexWithUV(x, y + height, zLevel, u * var7, v + height * var8);
        tessellator.bridge$addVertexWithUV(x + width, y + height, zLevel, u + width * var7, v + height * var8);
        tessellator.bridge$addVertexWithUV(x + width, y, zLevel, u + width * var7, v * var8);
        tessellator.bridge$addVertexWithUV(x, y, zLevel, u * var7, v * var8);
        tessellator.bridge$finish();
    }

    /**
     * Renders the item's overlay information. Examples being stack count or damage on top of the item's image at the
     * specified position.
     */
    public static void renderItemOverlayIntoGUI(FontRendererBridge fontRenderer, ItemStackBridge itemStack, int x, int y, boolean showDamageBar, boolean showCount) {
        if (itemStack != null && (showDamageBar || showCount)) {
            if (itemStack.bridge$isItemDamaged() && showDamageBar) {
                int var11 = (int) Math
                        .round(13.0D - itemStack.bridge$getItemDamageForDisplay() * 13.0D / itemStack.bridge$getMaxDamage());
                int var7 = (int) Math
                        .round(255.0D - itemStack.bridge$getItemDamageForDisplay() * 255.0D / itemStack.bridge$getMaxDamage());
                Ref.getGlBridge().bridge$disableLighting();
                Ref.getGlBridge().bridge$disableDepthTest();
                Ref.getGlBridge().bridge$disableTexture2D();
                TessellatorBridge tessellator = Ref.getTessellator();
                int var9 = 255 - var7 << 16 | var7 << 8;
                int var10 = (255 - var7) / 4 << 16 | 16128;
                renderQuad(tessellator, x + 2, y + 13, 13, 2, 0);
                renderQuad(tessellator, x + 2, y + 13, 12, 1, var10);
                renderQuad(tessellator, x + 2, y + 13, var11, 1, var9);
                Ref.getGlBridge().bridge$enableTexture2D();
                Ref.getGlBridge().bridge$enableLighting();
                Ref.getGlBridge().bridge$enableDepthTest();
                Ref.getGlBridge().bridge$color(1.0F, 1.0F, 1.0F, 1.0F);
            }

            if (showCount) {
                int count = 0;

                if (itemStack.bridge$getMaxStackSize() > 1) {
                    count = HudUtil.countInInventory(Ref.getMinecraft().bridge$getThePlayer(), itemStack.bridge$getItem(),
                            itemStack.bridge$getItemDamage());
                } else if (itemStack.bridge$getItem().equals(Ref.getUtils().getItemFromName("bow"))) {
                    count = HudUtil.countInInventory(Ref.getMinecraft().bridge$getThePlayer(), Ref.getUtils().getItemFromName("arrow"));
                }

                if (count > 1) {
                    String var6 = "" + count;
                    Ref.getGlBridge().bridge$disableLighting();
                    Ref.getGlBridge().bridge$disableDepthTest();
                    fontRenderer.bridge$drawStringWithShadow(var6, x + 19 - 2 - fontRenderer.bridge$getStringWidth(var6), y + 6 + 3,
                            16777215);
                    Ref.getGlBridge().bridge$enableLighting();
                    Ref.getGlBridge().bridge$enableDepthTest();
                }
            }
        }
    }

    /**
     * Adds a quad to the tesselator at the specified position with the set width and height and color. Args:
     * tesselator, x, y, width,
     * height, color
     */
    public static void renderQuad(TessellatorBridge tessellator, int x, int y, int width, int height, int color) {
        tessellator.bridge$startDrawingQuads();
        tessellator.bridge$setColorOpaque_I(color);
        tessellator.bridge$addVertex((x), (y), 0.0D);
        tessellator.bridge$addVertex((x), (y + height), 0.0D);
        tessellator.bridge$addVertex((x + width), (y + height), 0.0D);
        tessellator.bridge$addVertex((x + width), (y), 0.0D);
        tessellator.bridge$finish();
    }

    public static int countInInventory(EntityPlayerBridge player, ItemBridge item) {
        return countInInventory(player, item, -1);
    }

    public static int countInInventory(EntityPlayerBridge player, ItemBridge item, int md) {
        int count = 0;
        for (int i = 0; i < player.bridge$getInventory().bridge$getMainInventory().length; i++) {
            if (player.bridge$getInventory().bridge$getMainInventory()[i] != null && item.equals(player.bridge$getInventory().bridge$getMainInventory()[i].bridge$getItem()) &&
                    (md == -1 || player.bridge$getInventory().bridge$getMainInventory()[i].bridge$getItemDamage() == md)) {
                count += player.bridge$getInventory().bridge$getMainInventory()[i].bridge$getStackSize();
            }
        }
        return count;
    }

}