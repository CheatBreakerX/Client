package com.cheatbreaker.mixin.scoreboard;

import com.cheatbreaker.bridge.scoreboard.ScoreObjectiveBridge;
import com.cheatbreaker.bridge.scoreboard.ScoreboardBridge;
import net.minecraft.scoreboard.ScoreObjective;
import net.minecraft.scoreboard.Scoreboard;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(ScoreObjective.class)
public abstract class MixinScoreObjective implements ScoreObjectiveBridge {
    @Shadow public abstract void setDisplayName(String nameIn);
    @Shadow public abstract String getDisplayName();
    @Shadow public abstract Scoreboard getScoreboard();

    public void bridge$setDisplayName(String name) {
        this.setDisplayName(name);
    }

    public String bridge$getDisplayName() {
        return this.getDisplayName();
    }

    public ScoreboardBridge bridge$getScoreboard() {
        return (ScoreboardBridge) this.getScoreboard();
    }
}
