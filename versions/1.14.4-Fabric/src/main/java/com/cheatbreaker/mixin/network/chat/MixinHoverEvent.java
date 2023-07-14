package com.cheatbreaker.mixin.network.chat;

import com.cheatbreaker.bridge.event.HoverEventBridge;
import net.minecraft.network.chat.HoverEvent;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(HoverEvent.class)
public class MixinHoverEvent implements HoverEventBridge {
}
