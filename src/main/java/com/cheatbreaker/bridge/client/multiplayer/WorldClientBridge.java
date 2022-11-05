package com.cheatbreaker.bridge.client.multiplayer;

import com.cheatbreaker.bridge.entity.player.EntityPlayerBridge;

public interface WorldClientBridge {
    EntityPlayerBridge bridge$getPlayerEntityByName(String name);
}
