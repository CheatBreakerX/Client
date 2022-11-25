package com.cheatbreaker.client.websocket.server;

import com.cheatbreaker.bridge.util.CryptManagerBridge;
import com.cheatbreaker.client.nethandler.ByteBufWrapper;
import com.cheatbreaker.client.websocket.AssetsWebSocket;
import com.cheatbreaker.client.websocket.WSPacket;

import java.security.PublicKey;

public class WSPacketJoinServer
        extends WSPacket {
    private PublicKey publicKey;
    private byte[] encryptedPublicKey;

    @Override
    public void write(ByteBufWrapper buf) {
    }

    @Override
    public void read(ByteBufWrapper buf) {
        this.encryptedPublicKey = this.readKey(buf.buf());
        this.publicKey = CryptManagerBridge.decodePublicKey(this.encryptedPublicKey);
    }

    @Override
    public void handle(AssetsWebSocket websocket) {
        websocket.handleJoinServer(this);
    }

    public PublicKey getPublicKey() {
        return this.publicKey;
    }

    public byte[] getEncryptedPublicKey() {
        return this.encryptedPublicKey;
    }
}

