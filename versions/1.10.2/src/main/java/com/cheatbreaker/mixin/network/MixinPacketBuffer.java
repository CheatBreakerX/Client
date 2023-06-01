package com.cheatbreaker.mixin.network;

import com.cheatbreaker.bridge.network.PacketBufferBridge;
import io.netty.buffer.ByteBuf;
import net.minecraft.network.PacketBuffer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(PacketBuffer.class)
public abstract class MixinPacketBuffer implements PacketBufferBridge {
    @Shadow public abstract ByteBuf writeBytes(byte[] p_writeBytes_1_);

    public void bridge$writeBytes(byte[] bytes) {
        this.writeBytes(bytes);
    }
}
