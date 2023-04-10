package com.cheatbreaker.mixin.event;

import com.cheatbreaker.bridge.event.HoverEventBridge;
import net.minecraft.util.text.event.HoverEvent;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(HoverEvent.class)
public class MixinHoverEvent implements HoverEventBridge {
}
