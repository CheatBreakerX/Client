package com.cheatbreaker.mixin.net.minecraft.world.scores;

import com.cheatbreaker.bridge.scoreboard.ScoreBridge;
import com.cheatbreaker.bridge.scoreboard.ScoreObjectiveBridge;
import com.cheatbreaker.bridge.scoreboard.ScorePlayerTeamBridge;
import com.cheatbreaker.bridge.scoreboard.ScoreboardBridge;
import com.cheatbreaker.main.CheatBreaker;
import com.google.common.collect.Lists;
import net.minecraft.world.scores.Objective;
import net.minecraft.world.scores.PlayerTeam;
import net.minecraft.world.scores.Score;
import net.minecraft.world.scores.Scoreboard;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

import javax.annotation.Nullable;
import java.util.*;

@Mixin(Scoreboard.class)
public abstract class MixinScoreboard implements ScoreboardBridge {
    @Shadow public abstract Score getOrCreatePlayerScore(String string, Objective arg);
    @Shadow @Nullable public abstract PlayerTeam getPlayersTeam(String string);

    @Shadow @Final private Map<String, Map<Objective, Score>> playerScores;

    @Shadow public abstract Collection<String> getObjectiveNames();

    @Shadow @Nullable public abstract Objective getDisplayObjective(int i);

    @Shadow @Final private Map<String, PlayerTeam> teamsByPlayer;

    public void bridge$func_96529_a(String name, ScoreObjectiveBridge objective) {
        this.getOrCreatePlayerScore(name, (Objective) objective);
    }

    public ScorePlayerTeamBridge bridge$getPlayersTeam(String name) {
        return (ScorePlayerTeamBridge) this.getPlayersTeam(name);
    }

    private static final Comparator<ScoreBridge> patchedScoreComparator = (first, second) -> first.bridge$getScorePoints() > second.bridge$getScorePoints() ? 1 : (first.bridge$getScorePoints() < second.bridge$getScorePoints() ? -1 : second.bridge$getPlayerName().compareToIgnoreCase(first.bridge$getPlayerName()));
    public Collection<ScoreBridge> bridge$func_96534_i(ScoreObjectiveBridge objective) {
        List<ScoreBridge> list = Lists.newArrayList();

        for (Map<Objective, Score> map : this.playerScores.values()) {
            Score score = map.get((Objective) objective);

            if (score != null) {
                list.add((ScoreBridge) score);
            }
        }

        list.sort(patchedScoreComparator);
        return list;
    }

    public ScoreObjectiveBridge bridge$func_96539_a(int i) {
        return (ScoreObjectiveBridge) this.getDisplayObjective(i);
    }

    public Collection<String> bridge$getObjectiveNames() {
        return this.getObjectiveNames();
    }

    /**
     * @author iAmSpace
     * @reason crash
     */
    @Overwrite
    public void removePlayerFromTeam(String playerName, PlayerTeam team) {
        if (this.getPlayersTeam(playerName) != team) {
            CheatBreaker.getInstance().cbInfo("Couldn't remove " + team.getDisplayName() + " from team.");
        } else {
            this.teamsByPlayer.remove(playerName);
            team.getPlayers().remove(playerName);
        }
    }
}
