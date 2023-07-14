package com.cheatbreaker.impl.ref;

import com.cheatbreaker.bridge.client.gui.components.BossHealthOverlayBridge;
import com.cheatbreaker.bridge.entity.boss.BossStatusBridge;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.components.LerpingBossEvent;

import java.util.Map;
import java.util.UUID;

public class BridgedBossStatus implements BossStatusBridge {
    private Map<UUID, LerpingBossEvent> getMapBossInfos() {
        return ((BossHealthOverlayBridge) Minecraft.getInstance().gui.getBossOverlay()).bridge$mapBossInfos();
    }

    public String bridge$getBossName() {
        if (!this.getMapBossInfos().isEmpty()) {
            for (LerpingBossEvent inf : ((BossHealthOverlayBridge) Minecraft.getInstance().gui.getBossOverlay()).bridge$mapBossInfos().values()) {
                return inf.getName().getColoredString();
            }
        }
        return null;
    }

    public int bridge$getStatusBarTime() {
        if (!this.getMapBossInfos().isEmpty()) {
            for (LerpingBossEvent inf : ((BossHealthOverlayBridge) Minecraft.getInstance().gui.getBossOverlay()).bridge$mapBossInfos().values()) {
                return (int) (inf.getPercent() * 100);
            }
        }
        return -1;
    }
}
