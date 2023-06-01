package com.cheatbreaker.mixin.network.play.client;

import com.cheatbreaker.bridge.network.play.client.C17PacketCustomPayloadBridge;
import net.minecraft.network.play.client.CPacketCustomPayload;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(CPacketCustomPayload.class)
public class MixinCPacketCustomPayload implements C17PacketCustomPayloadBridge {

}
