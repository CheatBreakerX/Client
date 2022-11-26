package com.cheatbreaker.mixin.util;

import com.cheatbreaker.bridge.event.HoverEventBridge;
import com.cheatbreaker.bridge.util.ChatStyleBridge;
import net.minecraft.event.HoverEvent;
import net.minecraft.util.ChatStyle;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(ChatStyle.class)
public abstract class MixinChatStyle implements ChatStyleBridge {
    @Shadow public abstract ChatStyle setChatHoverEvent(HoverEvent event);

    public void bridge$setChatHoverEvent(HoverEventBridge hoverEvent) {
        this.setChatHoverEvent((HoverEvent) hoverEvent);
    }
}
