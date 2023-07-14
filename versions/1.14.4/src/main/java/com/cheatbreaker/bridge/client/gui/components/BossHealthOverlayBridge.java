package com.cheatbreaker.bridge.client.gui.components;

import net.minecraft.client.gui.components.LerpingBossEvent;

import java.util.Map;
import java.util.UUID;

public interface BossHealthOverlayBridge {
    Map<UUID, LerpingBossEvent> bridge$mapBossInfos();
}
