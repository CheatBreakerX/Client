package com.cheatbreaker.bridge.client.network;

import com.cheatbreaker.bridge.network.PacketBridge;

public interface NetHandlerPlayClientBridge {
    void bridge$addToSendQueue(PacketBridge packet);
}
