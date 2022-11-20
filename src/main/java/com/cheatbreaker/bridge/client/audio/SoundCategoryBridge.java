package com.cheatbreaker.bridge.client.audio;

public enum SoundCategoryBridge {
    MASTER("master", 0),
    MUSIC("music", 1),
    RECORDS("record", 2),
    WEATHER("weather", 3),
    BLOCKS("block", 4),
    MOBS("hostile", 5),
    ANIMALS("neutral", 6),
    PLAYERS("player", 7),
    AMBIENT("ambient", 8);

    private final String bridge$categoryName;
    private final int bridge$categoryId;

    SoundCategoryBridge(String name, int id) {
        this.bridge$categoryName = name;
        this.bridge$categoryId = id;
    }

    public String bridge$getCategoryName() {
        return this.bridge$categoryName;
    }

    public int bridge$getCategoryId() {
        return this.bridge$categoryId;
    }
}
