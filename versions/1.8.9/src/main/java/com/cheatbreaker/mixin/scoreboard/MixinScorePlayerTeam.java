package com.cheatbreaker.mixin.scoreboard;

import com.cheatbreaker.bridge.scoreboard.ScorePlayerTeamBridge;
import net.minecraft.scoreboard.ScorePlayerTeam;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(ScorePlayerTeam.class)
public abstract class MixinScorePlayerTeam implements ScorePlayerTeamBridge {
    @Shadow public abstract String formatString(String input);

    public String bridge$formatPlayerName(String name) {
        return this.formatString(name);
    }
}
