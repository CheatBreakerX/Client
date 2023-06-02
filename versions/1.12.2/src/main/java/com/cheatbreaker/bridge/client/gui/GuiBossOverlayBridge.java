package com.cheatbreaker.bridge.client.gui;

import net.minecraft.client.gui.BossInfoClient;

import java.util.Map;
import java.util.UUID;

public interface GuiBossOverlayBridge {
    Map<UUID, BossInfoClient> bridge$mapBossInfos();
}
