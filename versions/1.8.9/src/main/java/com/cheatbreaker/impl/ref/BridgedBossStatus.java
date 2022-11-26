package com.cheatbreaker.impl.ref;

import com.cheatbreaker.bridge.entity.boss.BossStatusBridge;
import net.minecraft.entity.boss.BossStatus;

public class BridgedBossStatus implements BossStatusBridge {
    public String bridge$getBossName() {
        return BossStatus.bossName;
    }

    public int bridge$getStatusBarTime() {
        return BossStatus.statusBarTime;
    }
}
