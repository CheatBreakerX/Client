package com.cheatbreaker.mixin.util.text;

import com.cheatbreaker.bridge.event.HoverEventBridge;
import com.cheatbreaker.bridge.util.ChatStyleBridge;
import net.minecraft.util.text.Style;
import net.minecraft.util.text.event.HoverEvent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(Style.class)
public abstract class MixinStyle implements ChatStyleBridge {
    @Shadow public abstract Style setHoverEvent(HoverEvent event);

    public void bridge$setChatHoverEvent(HoverEventBridge hoverEvent) {
        this.setHoverEvent((HoverEvent) hoverEvent);
    }
}
