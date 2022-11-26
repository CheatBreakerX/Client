package com.cheatbreaker.mixin.network.play.client;

import com.cheatbreaker.bridge.network.play.client.C17PacketCustomPayloadBridge;
import net.minecraft.network.play.client.C17PacketCustomPayload;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(C17PacketCustomPayload.class)
public class MixinC17PacketCustomPayload implements C17PacketCustomPayloadBridge {

}
