package com.cheatbreaker.mixin.client.multiplayer;

import com.cheatbreaker.bridge.client.network.NetHandlerPlayClientBridge;
import com.cheatbreaker.bridge.network.PacketBridge;
import net.minecraft.client.multiplayer.ClientPacketListener;
import net.minecraft.network.Connection;
import net.minecraft.network.protocol.Packet;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(ClientPacketListener.class)
public class MixinClientPacketListener implements NetHandlerPlayClientBridge {
    @Shadow @Final private Connection connection;

    public void bridge$addToSendQueue(PacketBridge packet) {
        this.connection.send((Packet<?>) packet);
    }
}
