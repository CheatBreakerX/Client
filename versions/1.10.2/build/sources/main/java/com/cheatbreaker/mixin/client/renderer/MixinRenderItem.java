package com.cheatbreaker.mixin.client.renderer;

import com.cheatbreaker.bridge.client.gui.FontRendererBridge;
import com.cheatbreaker.bridge.client.renderer.entity.RenderItemBridge;
import com.cheatbreaker.bridge.client.renderer.texture.TextureManagerBridge;
import com.cheatbreaker.bridge.item.ItemStackBridge;
import com.cheatbreaker.util.Utils;
import net.minecraft.client.renderer.RenderItem;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(RenderItem.class)
public abstract class MixinRenderItem implements RenderItemBridge {
    @Shadow public float zLevel;

    @Shadow public abstract void renderItemAndEffectIntoGUI(ItemStack stack, int xPosition, int yPosition);

    @Shadow public abstract void renderItemIntoGUI(ItemStack stack, int x, int y);

    public float bridge$getZLevel() {
        return this.zLevel;
    }

    public void bridge$setZLevel(float zLevel) {
        this.zLevel = zLevel;
    }

    public void bridge$renderItemAndEffectIntoGUI(FontRendererBridge p_82406_1_, TextureManagerBridge p_82406_2_, ItemStackBridge stack, int x, int y) {
        this.renderItemAndEffectIntoGUI(Utils.itemStackBridgeToItemStack(stack), x, y);
    }

    public void bridge$renderItemIntoGUI(FontRendererBridge fontRenderer, TextureManagerBridge textureManager, ItemStackBridge stack, int x, int y) {
        this.renderItemIntoGUI(Utils.itemStackBridgeToItemStack(stack), x, y);
    }
}
