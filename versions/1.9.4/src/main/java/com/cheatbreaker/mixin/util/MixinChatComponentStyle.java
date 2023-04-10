package com.cheatbreaker.mixin.util;

import com.cheatbreaker.bridge.util.ChatComponentStyleBridge;
import com.cheatbreaker.bridge.util.ChatStyleBridge;
import com.cheatbreaker.bridge.util.IChatComponentBridge;
import net.minecraft.util.text.Style;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentBase;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(TextComponentBase.class)
public abstract class MixinChatComponentStyle implements ChatComponentStyleBridge {
    @Shadow public abstract String getFormattedText();
    @Shadow public abstract ITextComponent appendSibling(ITextComponent component);
    @Shadow public abstract Style getStyle();

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
