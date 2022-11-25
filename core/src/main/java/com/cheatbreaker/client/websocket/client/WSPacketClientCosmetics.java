package com.cheatbreaker.client.websocket.client;

import com.cheatbreaker.client.nethandler.ByteBufWrapper;
import com.cheatbreaker.client.util.cosmetic.Cosmetic;
import com.cheatbreaker.client.websocket.AssetsWebSocket;
import com.cheatbreaker.client.websocket.WSPacket;

import java.io.IOException;
import java.util.List;

public class WSPacketClientCosmetics
        extends WSPacket {
    private List<Cosmetic> cosmetics;

    public WSPacketClientCosmetics() {
    }

    public WSPacketClientCosmetics(List<Cosmetic> list) {
        this.cosmetics = list;
    }

    @Override
    public void write(ByteBufWrapper buf) {
        buf.buf().writeInt(this.cosmetics.size());
        for (Cosmetic cosmetic : this.cosmetics) {
            buf.buf().writeLong(Long.MAX_VALUE);
            buf.buf().writeBoolean(cosmetic.isEquipped());
            buf.writeString(cosmetic.getType());
            buf.writeString(cosmetic.getName());
            buf.buf().writeFloat(cosmetic.getScale());
            buf.writeString(cosmetic.getLocation().toString().replaceFirst("minecraft:", ""));
        }
    }

    @Override
    public void read(ByteBufWrapper buf) throws IOException {

    }

    @Override
    public void handle(AssetsWebSocket handler) {
    }

    public List<Cosmetic> getCosmetics() {
        return this.cosmetics;
    }
}
