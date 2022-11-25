package com.cheatbreaker.bridge.client.entity;

import com.cheatbreaker.bridge.client.network.NetHandlerPlayClientBridge;

public interface EntityClientPlayerMPBridge extends EntityPlayerSPBridge {
    NetHandlerPlayClientBridge bridge$getSendQueue();
}
