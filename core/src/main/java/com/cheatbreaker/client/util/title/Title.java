package com.cheatbreaker.client.util.title;

public class Title {
    private final TitleType titleEnum;
    private final String message;
    private final float scale;
    private final long displayTimeMs;
    private final long fadeInTimeMs;
    private final long fadeOutTimeMs;
    protected final long startTime = System.currentTimeMillis();

    public Title(String message, TitleType type, float scale, long displayTimeMs, long fadeInTimeMs, long fadeOutTimeMs) {
        this.message = message;
        this.scale = scale;
        this.titleEnum = type;
        this.displayTimeMs = displayTimeMs == 0L ? 5000L : displayTimeMs;
        this.fadeInTimeMs = fadeInTimeMs;
        this.fadeOutTimeMs = fadeOutTimeMs;
    }

    public boolean isFadingIn() {
        return System.currentTimeMillis() < this.startTime + this.fadeInTimeMs;
    }

    public boolean isFullyVisible() {
        return System.currentTimeMillis() > this.startTime + this.displayTimeMs - this.fadeOutTimeMs;
    }

    public TitleType getType() {
        return this.titleEnum;
    }

    public String getMessage() {
        return this.message;
    }

    public float getScale() {
        return this.scale;
    }

    public long getDisplayTimeMs() {
        return this.displayTimeMs;
    }

    public long getFadeInTimeMs() {
        return this.fadeInTimeMs;
    }

    public long getFadeOutTimeMs() {
        return this.fadeOutTimeMs;
    }

    static TitleType getType(Title title) {
        return title.titleEnum;
    }

    static float getScale(Title title) {
        return title.scale;
    }

    static long getFadeInTime(Title title) {
        return title.fadeInTimeMs;
    }

    static long getDisplayTime(Title title) {
        return title.displayTimeMs;
    }

    static long getFadeOutTime(Title title) {
        return title.fadeOutTimeMs;
    }

    static String getMessage(Title title) {
        return title.message;
    }

    public enum TitleType {
        SUBTITLE,
        TITLE
    }
}