package com.cheatbreaker.mixin.util;

import com.cheatbreaker.bridge.util.ChatComponentTextBridge;
import com.cheatbreaker.bridge.util.ChatStyleBridge;
import com.cheatbreaker.bridge.util.IChatComponentBridge;
import net.minecraft.util.ChatComponentStyle;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.IChatComponent;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(ChatComponentText.class)
public abstract class MixinChatComponentText extends ChatComponentStyle implements ChatComponentTextBridge {
    public String bridge$getFormattedText() {
        return this.getFormattedText();
    }

    public void bridge$appendSibling(IChatComponentBridge component) {
        this.appendSibling((IChatComponent) component);
    }

    public ChatStyleBridge bridge$getChatStyle() {
        return (ChatStyleBridge) this.getChatStyle();
    }
}
