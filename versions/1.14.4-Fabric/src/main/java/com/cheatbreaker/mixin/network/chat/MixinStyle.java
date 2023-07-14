package com.cheatbreaker.mixin.network.chat;

import com.cheatbreaker.bridge.event.HoverEventBridge;
import com.cheatbreaker.bridge.util.ChatStyleBridge;
import net.minecraft.network.chat.HoverEvent;
import net.minecraft.network.chat.Style;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(Style.class)
public abstract class MixinStyle implements ChatStyleBridge {
    @Shadow public abstract Style setHoverEvent(HoverEvent arg);

    public void bridge$setChatHoverEvent(HoverEventBridge hoverEvent) {
        this.setHoverEvent((HoverEvent) hoverEvent);
    }
}
