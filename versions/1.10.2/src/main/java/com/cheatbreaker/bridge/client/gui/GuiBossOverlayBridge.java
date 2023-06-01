package com.cheatbreaker.bridge.client.gui;

import net.minecraft.world.BossInfoLerping;

import java.util.Map;
import java.util.UUID;

public interface GuiBossOverlayBridge {
    Map<UUID, BossInfoLerping> bridge$mapBossInfos();
}
