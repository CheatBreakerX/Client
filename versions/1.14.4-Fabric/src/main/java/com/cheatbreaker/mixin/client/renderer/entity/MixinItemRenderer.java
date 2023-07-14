package com.cheatbreaker.mixin.client.renderer.entity;

import com.cheatbreaker.bridge.client.gui.FontRendererBridge;
import com.cheatbreaker.bridge.client.renderer.entity.RenderItemBridge;
import com.cheatbreaker.bridge.client.renderer.texture.TextureManagerBridge;
import com.cheatbreaker.bridge.item.ItemStackBridge;
import com.cheatbreaker.util.Utils;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(ItemRenderer.class)
public abstract class MixinItemRenderer implements RenderItemBridge {
    @Shadow public float blitOffset;
    @Shadow public abstract void renderAndDecorateItem(ItemStack itemStack, int i, int j);
    @Shadow public abstract void renderGuiItem(ItemStack itemStack, int i, int j);

    public float bridge$getZLevel() {
        return this.blitOffset;
    }

    public void bridge$setZLevel(float zLevel) {
        this.blitOffset = zLevel;
    }

    public void bridge$renderItemAndEffectIntoGUI(FontRendererBridge p_82406_1_, TextureManagerBridge p_82406_2_, ItemStackBridge stack, int x, int y) {
        this.renderAndDecorateItem(Utils.itemStackBridgeToItemStack(stack), x, y);
    }

    public void bridge$renderItemIntoGUI(FontRendererBridge fontRenderer, TextureManagerBridge textureManager, ItemStackBridge stack, int x, int y) {
        this.renderGuiItem(Utils.itemStackBridgeToItemStack(stack), x, y);
    }
}
