package com.cheatbreaker.impl.ref;

import com.cheatbreaker.bridge.entity.boss.BossStatusBridge;
import com.cheatbreaker.mixin.net.minecraft.client.gui.components.BossHealthOverlayAccessor;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.components.LerpingBossEvent;

import java.util.Map;
import java.util.UUID;

public class BridgedBossStatus implements BossStatusBridge {
    private Map<UUID, LerpingBossEvent> getMapBossInfos() {
        return ((BossHealthOverlayAccessor) Minecraft.getInstance().gui.getBossOverlay()).accessor$events();
    }

    public String bridge$getBossName() {
        if (!this.getMapBossInfos().isEmpty()) {
            for (LerpingBossEvent inf : ((BossHealthOverlayAccessor) Minecraft.getInstance().gui.getBossOverlay())
                    .accessor$events().values()) {
                return inf.getName().getColoredString();
            }
        }
        return null;
    }

    public int bridge$getStatusBarTime() {
        if (!this.getMapBossInfos().isEmpty()) {
            for (LerpingBossEvent inf : ((BossHealthOverlayAccessor) Minecraft.getInstance().gui.getBossOverlay())
                    .accessor$events().values()) {
                return (int) (inf.getPercent() * 100);
            }
        }
        return -1;
    }
}
