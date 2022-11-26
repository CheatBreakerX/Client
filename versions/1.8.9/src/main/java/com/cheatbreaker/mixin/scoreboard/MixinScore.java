package com.cheatbreaker.mixin.scoreboard;

import com.cheatbreaker.bridge.scoreboard.ScoreBridge;
import net.minecraft.scoreboard.Score;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(Score.class)
public abstract class MixinScore implements ScoreBridge {
    @Shadow public abstract String getPlayerName();
    @Shadow private int scorePoints;

    public String bridge$getPlayerName() {
        return this.getPlayerName();
    }

    public int bridge$getScorePoints() {
        return this.scorePoints;
    }
}
