package com.cheatbreaker.bridge.client.multiplayer;

import com.cheatbreaker.bridge.entity.player.EntityPlayerBridge;
import com.cheatbreaker.bridge.scoreboard.ScoreboardBridge;

import java.util.List;

public interface WorldClientBridge {
    EntityPlayerBridge bridge$getPlayerEntityByName(String name);
    List<EntityPlayerBridge> bridge$getPlayerEntities();
    void bridge$setWorldTime(long time);
    ScoreboardBridge bridge$getScoreboard();
}
