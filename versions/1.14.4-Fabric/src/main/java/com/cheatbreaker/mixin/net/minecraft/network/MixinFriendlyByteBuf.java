package com.cheatbreaker.mixin.net.minecraft.network;

import com.cheatbreaker.bridge.network.PacketBufferBridge;
import io.netty.buffer.ByteBuf;
import net.minecraft.network.FriendlyByteBuf;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(FriendlyByteBuf.class)
public abstract class MixinFriendlyByteBuf implements PacketBufferBridge {
    @Shadow public abstract ByteBuf writeBytes(byte[] bs);

    public void bridge$writeBytes(byte[] bytes) {
        this.writeBytes(bytes);
    }
}
