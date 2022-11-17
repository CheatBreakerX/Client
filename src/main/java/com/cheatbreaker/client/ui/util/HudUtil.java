package com.cheatbreaker.client.ui.util;

import com.cheatbreaker.bridge.ref.Ref;
import com.cheatbreaker.bridge.client.renderer.TessellatorBridge;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import org.lwjgl.opengl.GL11;

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
    public static void renderItemOverlayIntoGUI(FontRenderer fontRenderer, ItemStack itemStack, int x, int y,
                                                boolean showDamageBar, boolean showCount) {
        if (itemStack != null && (showDamageBar || showCount)) {
            if (itemStack.isItemDamaged() && showDamageBar) {
                int var11 = (int) Math
                        .round(13.0D - itemStack.getItemDamageForDisplay() * 13.0D / itemStack.getMaxDamage());
                int var7 = (int) Math
                        .round(255.0D - itemStack.getItemDamageForDisplay() * 255.0D / itemStack.getMaxDamage());
                GL11.glDisable(GL11.GL_LIGHTING);
                GL11.glDisable(GL11.GL_DEPTH_TEST);
                GL11.glDisable(GL11.GL_TEXTURE_2D);
                TessellatorBridge tessellator = Ref.getTessellator();
                int var9 = 255 - var7 << 16 | var7 << 8;
                int var10 = (255 - var7) / 4 << 16 | 16128;
                renderQuad(tessellator, x + 2, y + 13, 13, 2, 0);
                renderQuad(tessellator, x + 2, y + 13, 12, 1, var10);
                renderQuad(tessellator, x + 2, y + 13, var11, 1, var9);
                GL11.glEnable(GL11.GL_TEXTURE_2D);
                GL11.glEnable(GL11.GL_LIGHTING);
                GL11.glEnable(GL11.GL_DEPTH_TEST);
                GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
            }

            if (showCount) {
                int count = 0;

                if (itemStack.getMaxStackSize() > 1) {
                    count = HudUtil.countInInventory(Ref.getMinecraft().bridge$getThePlayer(), itemStack.getItem(),
                            itemStack.getItemDamage());
                } else if (itemStack.getItem().equals(Items.bow)) {
                    count = HudUtil.countInInventory(Ref.getMinecraft().bridge$getThePlayer(), Items.arrow);
                }

                if (count > 1) {
                    String var6 = "" + count;
                    GL11.glDisable(GL11.GL_LIGHTING);
                    GL11.glDisable(GL11.GL_DEPTH_TEST);
                    fontRenderer.drawStringWithShadow(var6, x + 19 - 2 - fontRenderer.getStringWidth(var6), y + 6 + 3,
                            16777215);
                    GL11.glEnable(GL11.GL_LIGHTING);
                    GL11.glEnable(GL11.GL_DEPTH_TEST);
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

    public static int countInInventory(EntityPlayer player, Item item) {
        return countInInventory(player, item, -1);
    }

    public static int countInInventory(EntityPlayer player, Item item, int md) {
        int count = 0;
        for (int i = 0; i < player.inventory.mainInventory.length; i++) {
            if (player.inventory.mainInventory[i] != null && item.equals(player.inventory.mainInventory[i].getItem()) &&
                    (md == -1 || player.inventory.mainInventory[i].getItemDamage() == md)) {
                count += player.inventory.mainInventory[i].stackSize;
            }
        }
        return count;
    }

}