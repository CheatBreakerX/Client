package com.cheatbreaker.bridge.util;

public interface IChatComponentBridge {
    String bridge$getFormattedText();
    void bridge$appendSibling(IChatComponentBridge component);
    ChatStyleBridge bridge$getChatStyle();
}
