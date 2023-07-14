package com.cheatbreaker.mixin.network.protocol;

import com.cheatbreaker.bridge.network.PacketBridge;
import net.minecraft.network.protocol.Packet;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(Packet.class)
public interface MixinPacket extends PacketBridge {
}
