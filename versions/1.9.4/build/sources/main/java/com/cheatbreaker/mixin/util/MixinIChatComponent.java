package com.cheatbreaker.mixin.util;

import com.cheatbreaker.bridge.util.ChatStyleBridge;
import com.cheatbreaker.bridge.util.IChatComponentBridge;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.Style;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(ITextComponent.class)
public interface MixinIChatComponent extends IChatComponentBridge {
    @Shadow String getFormattedText();
    @Shadow ITextComponent appendSibling(ITextComponent component);
    @Shadow Style getStyle();

    default String bridge$getFormattedText() {
        return this.getFormattedText();
    }

    default void bridge$appendSibling(IChatComponentBridge component) {
        this.appendSibling((ITextComponent) component);
    }

    default ChatStyleBridge bridge$getChatStyle() {
        return (ChatStyleBridge) this.getStyle();
    }
}
