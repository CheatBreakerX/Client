package com.cheatbreaker.mixin.util;

import com.cheatbreaker.bridge.util.ChatComponentTextBridge;
import com.cheatbreaker.bridge.util.ChatStyleBridge;
import com.cheatbreaker.bridge.util.IChatComponentBridge;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentBase;
import net.minecraft.util.text.TextComponentString;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(TextComponentString.class)
public abstract class MixinChatComponentText extends TextComponentBase implements ChatComponentTextBridge {
    public String bridge$getFormattedText() {
        return this.getFormattedText();
    }

    public void bridge$appendSibling(IChatComponentBridge component) {
        this.appendSibling((ITextComponent) component);
    }

    public ChatStyleBridge bridge$getChatStyle() {
        return (ChatStyleBridge) this.getStyle();
    }
}
