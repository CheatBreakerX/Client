package com.cheatbreaker.mixin.util;

import com.cheatbreaker.bridge.util.ChatStyleBridge;
import com.cheatbreaker.bridge.util.IChatComponentBridge;
import net.minecraft.util.ChatStyle;
import net.minecraft.util.IChatComponent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(IChatComponent.class)
public abstract class MixinIChatComponent implements IChatComponentBridge {
    @Shadow public abstract String getFormattedText();
    @Shadow public abstract IChatComponent appendSibling(IChatComponent component);
    @Shadow public abstract ChatStyle getChatStyle();

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
