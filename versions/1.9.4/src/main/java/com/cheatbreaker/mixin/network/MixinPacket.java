package com.cheatbreaker.mixin.network;

import com.cheatbreaker.bridge.network.PacketBridge;
import com.cheatbreaker.client.nethandler.Packet;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(Packet.class)
public class MixinPacket implements PacketBridge {
}
