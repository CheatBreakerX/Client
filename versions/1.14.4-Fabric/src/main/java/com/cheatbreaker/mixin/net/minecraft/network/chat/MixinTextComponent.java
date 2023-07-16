package com.cheatbreaker.mixin.net.minecraft.network.chat;

import com.cheatbreaker.bridge.util.ChatComponentTextBridge;
import com.cheatbreaker.bridge.util.ChatStyleBridge;
import com.cheatbreaker.bridge.util.IChatComponentBridge;
import net.minecraft.network.chat.BaseComponent;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(TextComponent.class)
public abstract class MixinTextComponent extends BaseComponent implements ChatComponentTextBridge {
    @Shadow public abstract String getContents();

    public String bridge$getFormattedText() {
        return this.getContents();
    }

    public void bridge$appendSibling(IChatComponentBridge component) {
        this.append((Component) component);
    }

    public ChatStyleBridge bridge$getChatStyle() {
        return (ChatStyleBridge) this.getStyle();
    }
}
