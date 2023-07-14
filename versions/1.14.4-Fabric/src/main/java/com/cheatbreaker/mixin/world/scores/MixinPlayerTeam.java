package com.cheatbreaker.mixin.world.scores;

import com.cheatbreaker.bridge.scoreboard.ScorePlayerTeamBridge;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.world.scores.PlayerTeam;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(PlayerTeam.class)
public abstract class MixinPlayerTeam implements ScorePlayerTeamBridge {
    @Shadow public abstract Component getFormattedName(Component arg);

    public String bridge$formatPlayerName(String name) {
        return this.getFormattedName(new TextComponent(name)).getColoredString();
    }
}
