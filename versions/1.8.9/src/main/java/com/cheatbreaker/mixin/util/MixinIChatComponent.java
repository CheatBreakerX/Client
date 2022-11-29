package com.cheatbreaker.mixin.util;

import com.cheatbreaker.bridge.util.ChatStyleBridge;
import com.cheatbreaker.bridge.util.IChatComponentBridge;
import net.minecraft.util.ChatStyle;
import net.minecraft.util.IChatComponent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(IChatComponent.class)
public interface MixinIChatComponent extends IChatComponentBridge {
    @Shadow String getFormattedText();
    @Shadow IChatComponent appendSibling(IChatComponent component);
    @Shadow ChatStyle getChatStyle();

    default String bridge$getFormattedText() {
        return this.getFormattedText();
    }

    default void bridge$appendSibling(IChatComponentBridge component) {
        this.appendSibling((IChatComponent) component);
    }

    default ChatStyleBridge bridge$getChatStyle() {
        return (ChatStyleBridge) this.getChatStyle();
    }
}
