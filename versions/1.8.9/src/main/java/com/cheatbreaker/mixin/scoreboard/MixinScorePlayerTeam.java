package com.cheatbreaker.mixin.scoreboard;

import com.cheatbreaker.bridge.scoreboard.ScorePlayerTeamBridge;
import net.minecraft.scoreboard.ScorePlayerTeam;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(ScorePlayerTeam.class)
public abstract class MixinScorePlayerTeam extends MixinTeam implements ScorePlayerTeamBridge {

}
