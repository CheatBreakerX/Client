package com.cheatbreaker.mixin.scoreboard;

import com.cheatbreaker.bridge.scoreboard.TeamBridge;
import net.minecraft.scoreboard.Team;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(Team.class)
public abstract class MixinTeam implements TeamBridge {
    @Shadow public abstract String formatString(String input);

    public String bridge$formatPlayerName(String name) {
        return this.formatString(name);
    }
}
