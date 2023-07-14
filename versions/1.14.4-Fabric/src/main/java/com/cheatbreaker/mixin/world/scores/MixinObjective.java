package com.cheatbreaker.mixin.world.scores;

import com.cheatbreaker.bridge.scoreboard.ScoreObjectiveBridge;
import com.cheatbreaker.bridge.scoreboard.ScoreboardBridge;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.world.scores.Objective;
import net.minecraft.world.scores.Scoreboard;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(Objective.class)
public abstract class MixinObjective implements ScoreObjectiveBridge {
    @Shadow public abstract void setDisplayName(Component arg);
    @Shadow public abstract Component getDisplayName();
    @Shadow public abstract Scoreboard getScoreboard();

    public void bridge$setDisplayName(String name) {
        this.setDisplayName(new TextComponent(name));
    }

    public String bridge$getDisplayName() {
        return this.getDisplayName().getContents();
    }

    public ScoreboardBridge bridge$getScoreboard() {
        return (ScoreboardBridge) this.getScoreboard();
    }
}
