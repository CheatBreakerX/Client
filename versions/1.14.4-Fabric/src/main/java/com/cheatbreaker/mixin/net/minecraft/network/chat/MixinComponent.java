package com.cheatbreaker.mixin.net.minecraft.network.chat;

import com.cheatbreaker.bridge.util.ChatStyleBridge;
import com.cheatbreaker.bridge.util.IChatComponentBridge;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.Style;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(Component.class)
public interface MixinComponent extends IChatComponentBridge {
    @Shadow Style getStyle();
    @Shadow String getContents();
    @Shadow Component append(Component arg);

    default String bridge$getFormattedText() {
        return this.getContents();
    }

    default void bridge$appendSibling(IChatComponentBridge component) {
        this.append((Component) component);
    }

    default ChatStyleBridge bridge$getChatStyle() {
        return (ChatStyleBridge) this.getStyle();
    }
}
