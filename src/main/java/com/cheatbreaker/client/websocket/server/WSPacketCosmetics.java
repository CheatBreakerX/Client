package com.cheatbreaker.client.websocket.server;

import com.cheatbreaker.client.nethandler.ByteBufWrapper;
import com.cheatbreaker.client.util.cosmetic.Cosmetic;
import com.cheatbreaker.client.websocket.AssetsWebSocket;
import com.cheatbreaker.client.websocket.WSPacket;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class WSPacketCosmetics
        extends WSPacket {
    private List<Cosmetic> cosmetics;
    private String playerId;

    public WSPacketCosmetics() {
    }

    public WSPacketCosmetics(String string, List<Cosmetic> list) {
        this.playerId = string;
        this.cosmetics = list;
    }

    @Override
    public void write(ByteBufWrapper buf) {
        buf.buf().writeInt(this.cosmetics.size());
        for (Cosmetic cosmetic : this.cosmetics) {
            buf.writeString(cosmetic.getName());
            buf.buf().writeFloat(cosmetic.getScale());
            buf.writeString(cosmetic.getLocation().toString());
            buf.buf().writeBoolean(cosmetic.isEquipped());
        }
    }

    @Override
    public void read(ByteBufWrapper buf) throws IOException {
        this.playerId = buf.readString();
        this.cosmetics = new ArrayList<>();
        int totalCosmetics = buf.buf().readInt();
        for (int x = 0; x < totalCosmetics; x++) {
            buf.buf().readLong();
            float scale = buf.buf().readFloat();
            boolean equipped = buf.buf().readBoolean();
            String location = buf.readString();
            String name = buf.readString();
            String type = buf.readString();
            this.cosmetics.add(new Cosmetic(this.playerId, name, type, scale, equipped, location));
        }
    }

    @Override
    public void handle(AssetsWebSocket lIIlllIIlllIlIllIIlIIIIll2) {
        lIIlllIIlllIlIllIIlIIIIll2.handleCosmetics(this);
    }

    public List<Cosmetic> getCosmetics() {
        return this.cosmetics;
    }

    public String getPlayerId() {
        return this.playerId;
    }
}