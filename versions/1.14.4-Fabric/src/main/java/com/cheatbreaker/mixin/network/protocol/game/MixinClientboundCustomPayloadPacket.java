package com.cheatbreaker.mixin.network.protocol.game;

import com.cheatbreaker.bridge.network.play.client.C17PacketCustomPayloadBridge;
import net.minecraft.network.protocol.game.ClientboundCustomPayloadPacket;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(ClientboundCustomPayloadPacket.class)
public class MixinClientboundCustomPayloadPacket implements C17PacketCustomPayloadBridge {
}
