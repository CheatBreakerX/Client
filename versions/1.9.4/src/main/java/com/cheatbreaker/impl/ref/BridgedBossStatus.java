package com.cheatbreaker.impl.ref;

import com.cheatbreaker.bridge.client.gui.GuiBossOverlayBridge;
import com.cheatbreaker.bridge.entity.boss.BossStatusBridge;
import net.minecraft.client.Minecraft;
import net.minecraft.world.BossInfoLerping;

public class BridgedBossStatus implements BossStatusBridge {
    public String bridge$getBossName() {
        for (BossInfoLerping inf : ((GuiBossOverlayBridge) Minecraft.getMinecraft().ingameGUI.getBossOverlay()).bridge$mapBossInfos().values()) {
            return inf.getName().getFormattedText();
        }
        return "";
    }

    public int bridge$getStatusBarTime() {
        for (BossInfoLerping inf : ((GuiBossOverlayBridge) Minecraft.getMinecraft().ingameGUI.getBossOverlay()).bridge$mapBossInfos().values()) {
            return (int) (inf.getPercent() * 100);
        }
        return 0;
    }
}
