package com.cheatbreaker.mixin.client.network;

import com.cheatbreaker.bridge.client.network.NetHandlerPlayClientBridge;
import com.cheatbreaker.bridge.network.PacketBridge;
import net.minecraft.client.network.NetHandlerPlayClient;
import net.minecraft.network.Packet;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(NetHandlerPlayClient.class)
public abstract class MixinNetHandlerPlayClient implements NetHandlerPlayClientBridge {
    @Shadow public abstract void sendPacket(Packet<?> packetIn);

    public void bridge$addToSendQueue(PacketBridge packet) {
        this.sendPacket((Packet) packet);
    }
}
