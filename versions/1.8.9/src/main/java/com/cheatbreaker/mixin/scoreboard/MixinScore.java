package com.cheatbreaker.mixin.scoreboard;

import com.cheatbreaker.bridge.scoreboard.ScoreBridge;
import com.cheatbreaker.bridge.scoreboard.ScoreboardBridge;
import com.cheatbreaker.bridge.scoreboard.TeamBridge;
import net.minecraft.scoreboard.Score;
import net.minecraft.scoreboard.ScorePlayerTeam;
import net.minecraft.scoreboard.Scoreboard;
import net.minecraft.scoreboard.Team;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(Score.class)
public abstract class MixinScore implements ScoreBridge {
    @Shadow public abstract String getPlayerName();
    @Shadow private int scorePoints;

    public String bridge$getPlayerName() {
        return this.getPlayerName();
    }

    public String bridge$getFormattedName(ScoreboardBridge scoreboardBridge) {
        Scoreboard scoreboard = (Scoreboard) scoreboardBridge;
        return scoreboard.getPlayersTeam(this.bridge$getPlayerName()) == null ? this.bridge$getPlayerName() : ScorePlayerTeam.formatPlayerName(scoreboard.getPlayersTeam(this.bridge$getPlayerName()), this.bridge$getPlayerName());
    }

    public int bridge$getScorePoints() {
        return this.scorePoints;
    }
}
