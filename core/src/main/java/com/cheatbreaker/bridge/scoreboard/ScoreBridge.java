package com.cheatbreaker.bridge.scoreboard;

public interface ScoreBridge {
    String bridge$getPlayerName();
    String bridge$getFormattedName(ScoreboardBridge scoreboard);
    int bridge$getScorePoints();
}
