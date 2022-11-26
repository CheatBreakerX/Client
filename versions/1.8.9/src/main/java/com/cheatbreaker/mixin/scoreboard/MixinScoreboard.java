package com.cheatbreaker.mixin.scoreboard;

import com.cheatbreaker.bridge.scoreboard.ScoreBridge;
import com.cheatbreaker.bridge.scoreboard.ScoreObjectiveBridge;
import com.cheatbreaker.bridge.scoreboard.ScoreboardBridge;
import com.cheatbreaker.bridge.scoreboard.TeamBridge;
import com.cheatbreaker.main.CheatBreaker;
import com.google.common.collect.Lists;
import net.minecraft.scoreboard.Score;
import net.minecraft.scoreboard.ScoreObjective;
import net.minecraft.scoreboard.ScorePlayerTeam;
import net.minecraft.scoreboard.Scoreboard;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

import java.util.*;

@Mixin(Scoreboard.class)
public abstract class MixinScoreboard implements ScoreboardBridge {
    @Shadow public abstract ScorePlayerTeam getPlayersTeam(String p_96509_1_);
    @Shadow public abstract ScoreObjective getObjectiveInDisplaySlot(int p_96539_1_);
    @Shadow @Final private Map<String, Map<ScoreObjective, Score>> entitiesScoreObjectives;
    @Shadow @Final private Map<String, ScorePlayerTeam> teamMemberships;
    @Shadow public abstract Score getValueFromObjective(String name, ScoreObjective objective);

    public void bridge$func_96529_a(String name, ScoreObjectiveBridge objective) {
        this.getValueFromObjective(name, (ScoreObjective) objective);
    }

    public TeamBridge bridge$getPlayersTeam(String name) {
        return (TeamBridge) this.getPlayersTeam(name);
    }

    private static final Comparator<ScoreBridge> patchedScoreComparator = (first, second) -> first.bridge$getScorePoints() > second.bridge$getScorePoints() ? 1 : (first.bridge$getScorePoints() < second.bridge$getScorePoints() ? -1 : second.bridge$getPlayerName().compareToIgnoreCase(first.bridge$getPlayerName()));
    public Collection<ScoreBridge> bridge$func_96534_i(ScoreObjectiveBridge objective) {
        List<ScoreBridge> list = Lists.newArrayList();

        for (Map<ScoreObjective, Score> map : this.entitiesScoreObjectives.values()) {
            Score score = map.get(objective);

            if (score != null) {
                list.add((ScoreBridge) score);
            }
        }

        Collections.sort(list, patchedScoreComparator);
        return list;
    }

    public ScoreObjectiveBridge bridge$func_96539_a(int i) {
        return (ScoreObjectiveBridge) this.getObjectiveInDisplaySlot(i);
    }

    /**
     * @author iAmSpace
     * @reason crash
     */
    @Overwrite
    public void removePlayerFromTeam(String playerName, ScorePlayerTeam team) {
        if (this.getPlayersTeam(playerName) != team) {
            CheatBreaker.getInstance().cbInfo("Couldn't remove " + team.getRegisteredName() + " from team.");
        } else {
            this.teamMemberships.remove(playerName);
            team.getMembershipCollection().remove(playerName);
        }
    }
}
