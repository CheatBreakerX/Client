package com.cheatbreaker.bridge.client.renderer.entity;

import com.cheatbreaker.bridge.client.gui.FontRendererBridge;
import com.cheatbreaker.bridge.client.renderer.texture.TextureManagerBridge;
import com.cheatbreaker.bridge.item.ItemStackBridge;

public interface RenderItemBridge {
    float bridge$getZLevel();
    void bridge$setZLevel(float zLevel);
    void bridge$renderItemAndEffectIntoGUI(FontRendererBridge p_82406_1_, TextureManagerBridge p_82406_2_, final ItemStackBridge stack, int x, int y);
    void bridge$renderItemIntoGUI(FontRendererBridge fontRenderer, TextureManagerBridge textureManager, ItemStackBridge stack, int x, int y);
}
