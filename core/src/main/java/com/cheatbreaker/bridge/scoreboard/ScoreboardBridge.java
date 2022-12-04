package com.cheatbreaker.bridge.scoreboard;

import java.util.Collection;

public interface ScoreboardBridge {
    void bridge$func_96529_a(String name, ScoreObjectiveBridge objective);
    ScorePlayerTeamBridge bridge$getPlayersTeam(String name);
    Collection<ScoreBridge> bridge$func_96534_i(ScoreObjectiveBridge objective);
    ScoreObjectiveBridge bridge$func_96539_a(int i);
    Collection<String> bridge$getObjectiveNames();
}
